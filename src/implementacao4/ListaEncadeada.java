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
public class ListaEncadeada implements ListaEncadeada_IF {

    private int data;
    private ListaEncadeada next;

    public ListaEncadeada() {
        this(-1, null);
    }

    private ListaEncadeada(int data, ListaEncadeada next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public boolean isEmpty() {
        return this.data == -1;
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }

        return 1 + next.size();
    }

    @Override
    public int search(int element) throws Exception {
        if (isEmpty()) {
            throw new Exception("Elemento não encontrado!");
        }

        if (this.data == element) {
            return this.data;
        }

        return next.search(element);
    }

    @Override
    public void insert(int element) {
        ListaEncadeada novo = new ListaEncadeada(this.data, this.next);

        this.next = novo;
        this.data = element;
    }

    @Override
    public void remove(int element) {
        if (this.data == -1) {
            return;
        }

        if (this.data == element) {
            this.data = next.data;
            this.next = this.next.next;
        } else {
            this.next.remove(element);
        }
    }

    @Override
    public int[] toArray() {
        int[] array = new int[size()];

        listToArray(array, 0);

        return array;
    }

    private void listToArray(int[] array, int idx) {
        if (isEmpty()) {
            return;
        }

        array[idx++] = this.data;

        next.listToArray(array, idx);
    }

    private ListaEncadeada reverseNode(ListaEncadeada previous) {
        if (next.isEmpty()) {
            this.next = previous;
            return this;
        }
        ListaEncadeada aux = next.reverseNode(this);
        this.next = previous;

        return aux;
    }

    //  Inverte a lista
    public void revert() {
        if (size() < 2) {
            return;
        }
        
        show("NORMAL");
        
        ListaEncadeada last = new ListaEncadeada(this.data, new ListaEncadeada());

        ListaEncadeada first = next.reverseNode(last);
        this.data = first.data;
        this.next = first.next;

        show("INVERTIDA");
    }
    
    private void show(String caption){
        int [] array = toArray();
        
        System.out.println(caption);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        
        System.out.println("\n");
    }

}
