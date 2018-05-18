package implementacao2;

/**
 *
 * @author Adson Macêdo
 */

import java.util.Random;

public class BrincandoComOrdenacao {

    static final int TESTES = 1000;     //  TESTES FEITOS E CONSIDERADOS
    static final int MARGEM = 10;       //  TESTES FEITOS E DESCONSIDERADOS

    static final int CRESCENTE = 0;
    static final int ALEATORIO = 1;
    static final int DECRESCENTE = 2;

    static final int BUBBLE = 0;
    static final int SELECTION = 1;
    static final int INSERTION = 2;
    static final int MERGE = 3;
    static final int QUICK = 4;
    static final int COUNTING = 5;

    //  MAIN
    public static void main(String[] args) {
        long[][][] table = new long[6][3][2];        //  TABELA DE RESULTADOS
        int vetores[][][] = criaVetores();    //  VETORES DE TESTE
        
        //  PARA CADA ALGORITMO i (0=BUBBLE; 1=SELECTION; 2=INSERTION; 3=MERGE; 4=QUICK; 5=COUNTING)
        for (int i = 0; i < 6; i++) {
            //  PARA CADA ORDEM j DO VETOR (0: CRESCENTE; 1: ALEATORIO; 2: DECRESCENTE)
            for (int j = 0; j < 3; j++) {
                //  PARA CADA TAMANHO DE VETOR (0: TAM=20; 1: TAM=2000)
                for (int k = 1; k >= 0; k--) {
                    for (int t = 0; t < (TESTES + MARGEM); t++) {
                        //  COPIA O VETOR DE TESTES ATUAL EM vet
                        int[] vet = vetores[k][j].clone();      
                        
                        long time = getTime(i, vet);

                        if (!isSorted(vet)) {
                            System.err.println("VETOR NAO ORDENADO!");
                            System.exit(-1);
                        }

                        //  ACUMULO O TEMPO DO t-ESIMO TESTE DO ALGORITMO i NO VETOR vet
                        if (t >= MARGEM)  table[i][j][k] += time;
                    }
                }
            }
        }

        printTable(table, 20);
        printTable(table, 2000);
    }

    //ALGORITMO BUBBLE SORT
    public static void bubbleSort(int[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length - i; j++) { 
                if (vetor[j] > vetor[j + 1]) {
                    swap(vetor, j, j+1);
                }
            }
        }
    }

    //ALGORITMO SELECTION SORT
    public static void selectionSort(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            //  Assume i-ésimo como o mínimo
            int min = i;
            
            //  Procura o menor elemento da parte não ordenada do vetor 
            for (int j = i + 1; j < vetor.length; j++)
                if (vetor[j] < vetor[min])
                    min = j;
            
            swap(vetor, i, min);
        }
    }    

    //ALGORITMO INSERTION SORT
    public static void insertionSort(int[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            int key = vetor[i];
            
            //  Empurre todos os valores vetor[j] maiores que key pra direita
            int j = i - 1; 
            while(j >= 0 && vetor[j] > key){
                vetor[j+1] = vetor[j];
                j--;
            }
            
            vetor[j+1] = key;
        }
    }

    //ALGORITMO MERGE SORT
    public static void mergeSort(int[] vetor, int l, int r) {
        //  Se (vetor.length == 1) então vetor está ordenado
        if (l == r) return;

        int meio = (l + r) / 2;

        //  Ordeno, recursivamente, a metade da esquerda e a metade da direita
        mergeSort(vetor, l, meio);
        mergeSort(vetor, meio + 1, r);

        //  Junto as partes ordenadas
        merge(vetor, l, meio, r);
    }
    
    //ALGORITMO QUICKSORT
    public static void quickSort(int[] vetor, int left, int right) {
        if (left < right) {
            int posPivot = partition(vetor, left, right);
            
            quickSort(vetor, left, posPivot - 1);
            quickSort(vetor, posPivot + 1, right);
        }
    }

    //ALGORITMO COUNTING SORT
    public static void countingSort(int[] vetor) {
        int n = vetor.length;
        int output[] = new int[n];
        int count[]  = new int[n];

        for (int i = 0; i < n; ++i) 
            ++count[vetor[i]];

        for (int i = 1; i < n; ++i) 
            count[i] += count[i - 1];

        for (int i = 0; i < n; ++i) {
            output[count[vetor[i]] - 1] = vetor[i];
            --count[vetor[i]];
        }

        for (int i = 0; i < n; ++i) 
            vetor[i] = output[i];
    }

//**************************************************
//*              MÉTODOS AUXILIARES
//**************************************************

    //  CRIA OS VETORES DE TESTES
    public static int[][][] criaVetores(){
        int vetores[][][] = new int[2][3][]; 

        int tam = 20;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) 
                vetores[i][j] = criaVetor(tam, j);
            
            tam*=100;
        }
        
        return vetores;
    }
    
    //  VERIFICA SE O VETOR ESTÁ ORDENADO
    public static boolean isSorted(int [] vetor){
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] < vetor[i-1]) 
                return false;
        }
        
        return true;
    }
    
    //  CRIA UM VETOR ESPECÍFICO
    public static int[] criaVetor(int tam, int ordem) {
        int[] vet = new int[tam];

        switch (ordem) {
            case CRESCENTE:
                for (int i = 0; i < tam; i++) 
                    vet[i] = i;
                
                break;
            case ALEATORIO:
                Random rnd = new Random();
                for (int i = 0; i < tam; i++) 
                    vet[i] = rnd.nextInt(tam);
                
                break;
            case DECRESCENTE:
                for (int i = 0; i < tam; i++) 
                    vet[i] = tam - i - 1;
                
        }

        return vet;
    }

    //  RETORNA O TEMPO DE EXECUÇÃO DO ALGORITMO SOBRE O VETOR
    public static long getTime(int algorithm, int[] vet) {
        long ini;
        switch (algorithm) {
            case BUBBLE:
                ini = System.nanoTime();
                bubbleSort(vet);
                return System.nanoTime() - ini;
            case SELECTION:
                ini = System.nanoTime();
                selectionSort(vet);
                return System.nanoTime() - ini;
            case INSERTION:
                ini = System.nanoTime();
                insertionSort(vet);
                return System.nanoTime() - ini;
            case MERGE:
                ini = System.nanoTime();
                mergeSort(vet, 0, vet.length - 1);
                return System.nanoTime() - ini;
            case QUICK:
                ini = System.nanoTime();
                quickSort(vet, 0, vet.length - 1);
                return System.nanoTime() - ini;
            case COUNTING:
                ini = System.nanoTime();
                countingSort(vet);
                return System.nanoTime() - ini;
        }

        return 0;
    }

    //  IMPRIME A TABELA DE RESULTADOS
    public static void printTable(long[][][] table, int tamanho) {
        String[] algNames = new String[]{"BUBBLE SORT", "SELECTION", "INSERTION", "MERGE SORT", "QUICK SORT", "COUNTING"};
        
        System.out.println("TAMANHO: N = " + tamanho);
        System.out.println("ALGORITMO\tCRESCENTE\tALEATORIO\tDECRESCENTE");
        
        tamanho /= 2000;
        
        for (int i = 0; i < table.length; i++) {
            System.out.print(algNames[i] + "\t");

            for (int j = 0; j < table[i].length; j++) 
                System.out.printf("%9d\t", (table[i][j][tamanho] / TESTES));

            System.out.println();
        }
        
        System.out.println();
    }
    
    //  AUXILIAR DO QUICKSORT
    public static int partition(int[] vetor, int left, int right) {
        int p = vetor[left];            //  Pivô inicial
        int i = left + 1, j = right;    //  Limites iniciais
        
        //  Procura o próximo pivô
        while (i <= j) {
            if (vetor[i] <= p) i++;
            else if (p < vetor[j]) j--;
            else swap(vetor, i++, j--);
        }
        
        //  Crava o pivô e retorna sua posição
        vetor[left] = vetor[j];
        vetor[j] = p;

        return j;
    }

    //  AUXILIAR DO MERGE SORT
    public static void merge(int vetor[], int l, int meio, int r) {
        int left [] = new int[meio-l + 1];      //  Vetor auxiliar esquerdo
        int right[] = new int[r - meio];        //  Vetor auxiliar direito

        //  Copia os elementos para o vetor auxiliar left
        for (int i = 0; i < left.length; ++i) 
            left[i] = vetor[l + i];

        //  Copia os elementos para o vetor auxiliar right
        for (int j = 0; j < right.length; ++j) 
            right[j] = vetor[meio + 1 + j];

        int i = 0, j = 0, k = l;
        
        //  Enquanto tiver elementos nos dois vetores auxiliares
        while (i < left.length && j < right.length) {
            //  Insere no vetor ordenado o min(left[i], right[j])
            if (left[i] <= right[j]) {
                vetor[k++] = left[i];
                i++;
            } else {
                vetor[k++] = right[j];
                j++;
            }
        }

        //  Enquanto ainda houver elementos em left e right 
        while (i < left.length) 
            vetor[k++] = left[i++];

        while (j < right.length) 
            vetor[k++] = right[j++];
    }

    public static void swap(int [] vetor, int i, int j){
        int aux  = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }
}
