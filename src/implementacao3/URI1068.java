package implementacao3;

import java.util.Scanner;

/**
 *
 * @author Adson Macêdo
 */
public class URI1068 {
    public static class Fila{
        private final int SIZE = 1000;
        private int [] array;
        private int tail;
        private int head;

        public Fila() {
            this.array = new int[SIZE + 1];
            this.tail = this.head = 0;
        }

        public void enqueue(int element) {
            this.array[tail++] = element;
            tail %= (SIZE + 1);
        }

        public int dequeue() {
            int v = this.head();
            this.head = (this.head + 1)%(SIZE+1);

            return v;
        }

        public int head() {
            return array[this.head];
        }

        public boolean isEmpty() {
            return head == tail;
        }
    }

    private static class Pilha {
        private Fila mainQueue;
        private Fila auxQueue;

        public Pilha() {
            this.mainQueue = new Fila();
            this.auxQueue = new Fila();
        }

        public void push(int element) {
            mainQueue.enqueue(element);
        }

        public int pop() {
            int v = mainQueue.dequeue();
            while (!mainQueue.isEmpty()){
                auxQueue.enqueue(v);
                v = mainQueue.dequeue();
            }

            while (!auxQueue.isEmpty())
                mainQueue.enqueue(auxQueue.dequeue());

            return v;
        }

        public boolean isEmpty() {
            return mainQueue.isEmpty();
        }

    }
    
    private static boolean checaParenteses(String expressao){
        Pilha pilha = new Pilha();

        for (int i = 0; i < expressao.length(); i++) {
            if (expressao.charAt(i) == '('){
                pilha.push(0);
            }else if (expressao.charAt(i) == ')'){
                if (pilha.isEmpty()) 
                    return false;
                else
                    pilha.pop();
            }
        }

        return (pilha.isEmpty()); 
    }
    
    public static void main(String[] args) {
        Pilha pilha = new Pilha();
        Scanner scan = new Scanner(System.in);
        
        while (scan.hasNextLine()){
            String expressao = scan.nextLine();
            if (checaParenteses(expressao))
                System.out.println("correct");
            else
                System.out.println("incorrect");
        }
    }
}
