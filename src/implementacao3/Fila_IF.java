package implementacao3;

public interface Fila_IF {
	public void enqueue(int element) throws Exception;
	public int dequeue() throws Exception;
	public int head() throws Exception;
	public boolean isEmpty();
	public boolean isFull();
}
