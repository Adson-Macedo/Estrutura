package implementacao4;

import java.util.Arrays;

public class BrincandoComAsEstruturas {

    private static void testaLista(ListaEncadeada lista) throws Exception {
        System.out.println("Brincando com ListaEncadeada...");
        System.out.println(lista.isEmpty());
        System.out.println(lista.size() == 0);
        lista.insert(100);
        System.out.println(lista.size() == 1);
        System.out.println(lista.toArray()[0] == 100);
        lista.insert(200);
        System.out.println(lista.size() == 2);
        System.out.println(lista.toArray()[0] == 200);
        System.out.println(lista.toArray()[1] == 100);
        lista.insert(300);
        System.out.println(lista.size() == 3);
        System.out.println(lista.toArray()[0] == 300);
        System.out.println(lista.toArray()[1] == 200);
        System.out.println(lista.toArray()[2] == 100);
        lista.remove(100);
        System.out.println(lista.size() == 2);
        System.out.println(lista.toArray()[0] == 300);
        System.out.println(lista.toArray()[1] == 200);
        lista.remove(300);
        System.out.println(lista.size() == 1);
        System.out.println(lista.toArray()[0] == 200);
        System.out.println(lista.search(200) == 200);
        lista.remove(200);
        System.out.println(lista.isEmpty());
        System.out.println(lista.size() == 0);

/*      //  Teste de inversão da lista
        lista.insert(0);
        lista.insert(1);
        lista.insert(2);
        lista.insert(4);
        lista.insert(8);
        lista.insert(16);

        lista.revert();
        lista.remove(4);
        lista.revert();
        lista.remove(16);
        lista.revert();
*/
    }

    public static void testaBST(BST_IF arvore) {
        System.out.println("Brincando com BST...");
        arvore.insert(8);
        System.out.println(arvore.isComplete());
        System.out.println(Arrays.toString(arvore.preOrder()).equals("[8]"));
        System.out.println(Arrays.toString(arvore.order()).equals("[8]"));
        System.out.println(Arrays.toString(arvore.postOrder()).equals("[8]"));
        arvore.insert(12);
        System.out.println(!arvore.isComplete());
        arvore.insert(14);
        System.out.println(!arvore.isComplete());
        arvore.insert(17);
        System.out.println(!arvore.isComplete());
        arvore.insert(4);
        System.out.println(!arvore.isComplete());
        arvore.insert(2);
        System.out.println(!arvore.isComplete());
        arvore.insert(1);
        System.out.println(!arvore.isComplete());
        arvore.insert(13);
        System.out.println(!arvore.isComplete());
        arvore.insert(3);
        System.out.println(!arvore.isComplete());
        arvore.insert(6);
        System.out.println(!arvore.isComplete());
        arvore.insert(7);
        System.out.println(!arvore.isComplete());
        arvore.insert(10);
        System.out.println(!arvore.isComplete());
        arvore.insert(9);
        System.out.println(!arvore.isComplete());
        arvore.insert(5);
        System.out.println(!arvore.isComplete());
        arvore.insert(11);
        System.out.println(arvore.isComplete());
        
        System.out.println(Arrays.toString(arvore.preOrder()).equals(Arrays.toString(new int[]{8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 17})));
        System.out.println(Arrays.toString(arvore.order()).equals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 17})));
        System.out.println(Arrays.toString(arvore.postOrder()).equals(Arrays.toString(new int[]{1, 3, 2, 5, 7, 6, 4, 9, 11, 10, 13, 17, 14, 12, 8})));

    }
    
    public static void testaTabelaHash(TabelaHash_IF tabela) throws Exception{
//        System.out.println(tabela.print());
        System.out.println(tabela.print().equals("0: \n1: \n2: \n3: \n4: \n5: \n6: \n7: "));
        
        for (int key = 1; key < 25; key++) {
            tabela.insert(key);
            System.out.println(tabela.search(key) == key);
        }
        
        System.out.println(tabela.print().equals("0: 24, 16, 8\n1: 17, 9, 1\n2: 18, 10, 2\n3: 19, 11, 3\n4: 20, 12, 4\n5: 21, 13, 5\n6: 22, 14, 6\n7: 23, 15, 7"));
//        System.out.println(tabela.print());
        
        int[] elementosARemover = new int[]{1,2,5,7,8,9,13,17,18,20,21,23};
        for (int i = 0; i < elementosARemover.length; i++) {
            tabela.remove(elementosARemover[i]);
        }
        
//        System.out.println(tabela.print());
            
        System.out.println(tabela.print().equals("0: 24, 16\n1: \n2: 10\n3: 19, 11, 3\n4: 12, 4\n5: \n6: 22, 14, 6\n7: 15"));

        try{
            tabela.remove(3);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
  //      System.out.println(tabela.print());
    }

    public static void main(String[] args) throws Exception {
        ListaEncadeada lista = new ListaEncadeada();
        testaLista(lista);
        
        BST_IF arvore = new BST();
        testaBST(arvore);
        
        System.out.println("Brincando com TabelaHash...");
        TabelaHash_IF tabela = new TabelaHash(8);
        testaTabelaHash(tabela);
		
        //OBS.: Espera-se que nenhum print mostre 'false' na tela!
        
    }

}
