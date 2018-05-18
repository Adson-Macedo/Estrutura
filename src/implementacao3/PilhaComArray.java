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
public class PilhaComArray implements Pilha_IF {
    private int [] array;
    private int top;
    
    public PilhaComArray() {
        this.array = new int[10];
        this.top = -1;
    }

    @Override
    public void push(int element) throws Exception {
        if (isFull()) 
            throw new StackOverflowException();
        
        array[++top] = element;
    }

    @Override
    public int pop() throws Exception {
        int v = this.top();
        
        this.top--;
        
        return v;
    }

    @Override
    public int top() throws Exception {
        if (isEmpty()) throw new StackUnderflowException();
        
        return array[top];
    }

    @Override
    public boolean isEmpty() {
        return (this.top == -1);
    }

    @Override
    public boolean isFull() {
        return (this.top == this.array.length - 1);
    }
    
}
