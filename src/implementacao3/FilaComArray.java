/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacao3;

/**
 *
 * @author Adson Macêdo
 */
public class FilaComArray implements Fila_IF {
    private final int SIZE = 10;
    private int [] array;
    private int tail;
    private int head;
    
    public FilaComArray() {
        this.array = new int[SIZE + 1];
        this.tail = this.head = 0;
    }

    @Override
    public void enqueue(int element) throws Exception {
        if (isFull()) throw new QueueOverflowException();

        this.array[tail++] = element;
        tail %= 11;
    }

    @Override
    public int dequeue() throws Exception {
        int v = this.head();
        this.head = (this.head + 1)%(SIZE+1);
        
        return v;
    }

    @Override
    public int head() throws Exception {
        if (isEmpty()) throw new QueueUnderflowException();
        
        return array[this.head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (this.head == (this.tail + 1)%(SIZE+1));
    }
    
}
