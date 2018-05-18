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
public class PilhaComFilas implements Pilha_IF {
    private FilaComArray mainQueue;
    private FilaComArray auxQueue;
    
    public PilhaComFilas() {
        this.mainQueue = new FilaComArray();
        this.auxQueue = new FilaComArray();
    }

    @Override
    public void push(int element) throws Exception {
        if (mainQueue.isFull())
            throw new StackOverflowException();
        
        mainQueue.enqueue(element);
    }

    @Override
    public int pop() throws Exception {
        if (isEmpty())
            throw new StackOverflowException();
        
        int v = mainQueue.dequeue();
        while (!mainQueue.isEmpty()){
            auxQueue.enqueue(v);
            v = mainQueue.dequeue();
        }
        
        while (!auxQueue.isEmpty())
            mainQueue.enqueue(auxQueue.dequeue());
        
        return v;
    }

    @Override
    public int top() throws Exception {
        int v = pop();
        mainQueue.enqueue(v);
        
        return v;
    }

    @Override
    public boolean isEmpty() {
        return mainQueue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return mainQueue.isFull();
    }
    
}
