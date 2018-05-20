/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacao4;

/**
 *
 * @author Adson Macêdo
 */
public class TabelaHash implements TabelaHash_IF{
    private ListaEncadeada_IF [] tabela;
    private int tamanho;
    
    public TabelaHash(int tamanho){
        this.tabela = new ListaEncadeada[tamanho];
        this.tamanho = tamanho;
        
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }
    
    @Override
    public void insert(int element) {
        int hash = element%tamanho;
        tabela[hash].insert(element);
    }

    @Override
    public void remove(int element) throws Exception {
        int hash = element%tamanho;
        
        search(element);
        tabela[hash].remove(element);
    }

    @Override
    public int search(int element) throws Exception {
        int hash = element%tamanho;
        
        return tabela[hash].search(element);
    }

    @Override
    public String print() {
        String result = new String();
        for (int i = 0; i < tamanho; i++) {
            
            result += i + ": ";
            
            if (!tabela[i].isEmpty()){
                int [] array = tabela[i].toArray();

                if (array.length > 0)
                    result += array[0];

                for (int j = 1; j < array.length; j++) {
                    result += (", " + array[j]);
                }
            }
            
            if (i < tamanho -1)
                result += '\n';
        }
        
        return result;
    }
    
}
