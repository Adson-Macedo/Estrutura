/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacao1;

/**
 *
 * @author Adson Macêdo
 */
public class Implementacao1 {
    public static boolean buscaSeq(int [] v, int x){
        for (int i = 0; i < v.length; i++) {
            if (v[i] == x) return true;
        }
        
        return false;
    }
    
    public static boolean buscaBin(int [] v, int x){
        int ini = 0;
        int fim = v.length - 1;
        
        while (ini <= fim){
            int meio = (ini + fim)/2;
            
            if (v[meio] == x) return true;
            
            if (x < v[meio]){
                fim = meio - 1;
            }else{
                ini = meio + 1;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        final int TESTES = 1000000;  //  Aumentei o número de testes pra uma melhor aproximação
        
        int v[][] = new int[3][];
        
        v[0] = new int[100];
        v[1] = new int[1000];
        v[2] = new int[10000];
        
        System.out.printf("%s\t%s\t%s\n", "Tamanho", "Linear", "Binaria");
        
        for (int i = 0; i < 3; i++) {
            long tempoTotal = 0;

            System.out.printf("%d\t", v[i].length);
            for (int j = 0; j < TESTES; j++) {
                long ini = System.nanoTime();
                boolean found = buscaSeq(v[i], -1);
                long fim = System.nanoTime();

                tempoTotal += (fim - ini);
            }

            System.out.printf("%d\t", tempoTotal/TESTES);
            tempoTotal = 0;
            for (int j = 0; j < TESTES; j++) {
                long ini = System.nanoTime();
                boolean found = buscaBin(v[i], -1);
                long fim = System.nanoTime();

                tempoTotal += (fim - ini);
            }

            System.out.printf("%d\n", tempoTotal/TESTES);
        }
        
    }
}
