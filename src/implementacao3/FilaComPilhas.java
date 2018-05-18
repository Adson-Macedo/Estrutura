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
public class FilaComPilhas implements Fila_IF {
    private PilhaComArray mainStack;
    private PilhaComArray auxStack;
    
    private void transferir(PilhaComArray a, PilhaComArray b) throws Exception{
        while (!a.isEmpty())
            b.push(a.pop());
    }
    
    public FilaComPilhas() {
        this.mainStack = new PilhaComArray();
        this.auxStack = new PilhaComArray();
    }

    @Override
    public void enqueue(int element) throws Exception{
        if (mainStack.isFull())
            throw new QueueOverflowException();
        
        mainStack.push(element);
    }

    @Override
    public int dequeue() throws Exception {
        if (isEmpty())
            throw new QueueOverflowException();
        
        transferir(mainStack, auxStack);
        int v = auxStack.pop();
        transferir(auxStack, mainStack);        
        
        return v;
    }

    @Override
    public int head() throws Exception {
        if (mainStack.isEmpty())
            throw new QueueOverflowException();
        
        transferir(mainStack, auxStack);
        int v = auxStack.top();
        transferir(auxStack, mainStack);        
        
        return v;
    }

    @Override
    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return mainStack.isFull();
    }
    
}
