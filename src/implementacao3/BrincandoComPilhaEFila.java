package implementacao3;

public class BrincandoComPilhaEFila {

        public static void testaPilha(Pilha_IF pilha) throws Exception{
            pilha.push(100);
            pilha.push(200);
            System.out.println(pilha.top() == 200);
            System.out.println(pilha.pop() == 200);
            System.out.println(pilha.pop() == 100);
            System.out.println(pilha.isEmpty());
            pilha.push(1);
            pilha.push(3);
            pilha.push(6);
            pilha.push(2);
            pilha.push(10);
            pilha.push(15);
            System.out.println(pilha.pop() == 15);
            System.out.println(pilha.pop() == 10);
            pilha.push(4);
            pilha.push(4);
            pilha.push(4);
            pilha.push(4);
            pilha.push(0);
            pilha.push(100);
            System.out.println(pilha.isFull());
            System.out.println(pilha.pop() == 100);
            System.out.println(pilha.pop() == 0);
            System.out.println(pilha.pop() == 4);
            System.out.println(pilha.pop() == 4);
            System.out.println(pilha.pop() == 4);
            System.out.println(pilha.pop() == 4);
            System.out.println(pilha.pop() == 2);
            System.out.println(pilha.pop() == 6);
            System.out.println(pilha.pop() == 3);
            System.out.println(pilha.pop() == 1);
            System.out.println();
        }
    
        public static void testaFila(Fila_IF fila) throws Exception{
            System.out.println(fila.isEmpty());
            fila.enqueue(100);
            fila.enqueue(200);
            System.out.println(fila.head() == 100);
            System.out.println(fila.dequeue() == 100);
            System.out.println(fila.dequeue() == 200);
            System.out.println(fila.isEmpty());
            fila.enqueue(1);
            System.out.println(fila.head() == 1);
            fila.enqueue(2);
            System.out.println(fila.head() == 1);
            
            for (int i = 3; i <= 9; i++)            //  enfileira 3..9
                fila.enqueue(i);

            System.out.println(!fila.isFull());
            fila.enqueue(10);
            System.out.println(fila.head() == 1);

            for (int i = 1; i <= 4; i++) 
                System.out.println(fila.dequeue() == i);

            fila.enqueue(7);
            
            for (int i = 5; i <= 10; i++)            //  desenfileira 5..10
                System.out.println(fila.dequeue() == i);
            
            System.out.println(!fila.isEmpty());
            System.out.println(fila.dequeue() == 7);
            
            System.out.println();
        }
        
	public static void main(String[] args) throws Exception {
		
		System.out.println("Brincando com PilhaComArray...");
		Pilha_IF minhaPilha1 = new PilhaComArray();
                testaPilha(minhaPilha1);
                
		System.out.println("Brincando com PilhaComFilas...");
		Pilha_IF minhaPilha2 = new PilhaComFilas();
                testaPilha(minhaPilha2);
                
		System.out.println("Brincando com FilaComArray...");
		Fila_IF minhaFila1 = new FilaComArray();
                testaFila(minhaFila1);
                
		System.out.println("Brincando com FilaComPilhas...");
		Fila_IF minhaFila2 = new FilaComPilhas();
                testaFila(minhaFila2);
		
		System.out.println("Brincando com checaParenteses...");
		System.out.println(checaParenteses("a+(b*c)-2-a") == true);
		System.out.println(checaParenteses("(a+b*(2-c)-2+a)*2") == true);
		System.out.println(checaParenteses("(a*b-(2+c)") == false);
		System.out.println(checaParenteses("2*(3-a))") == false);
		System.out.println(checaParenteses(")3+b*(2-c)(") == false);
		
		
		//OBS.: Espera-se que nenhum print mostre 'false' na tela! 

	}
	
	
	private static boolean checaParenteses(String expressao) throws Exception{ //resolver problema 1068 do URI usando PilhaComFilas
            PilhaComFilas pilha = new PilhaComFilas();
            
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
            
            return (pilha.isEmpty()); //TODO
	}

}
