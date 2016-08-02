package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if (size < 0)
			throw new RuntimeException("Tamanho negativo nao permitido.");
		
		this.array = (T[])new Object[size];
		this.tail = -1;
	}

	@Override
	public T head() {
		/*
		if (isEmpty())
			throw new QueueUnderflowException();
		*/
		
		if (isEmpty())
			return null;
		
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return (this.tail + 1) == this.array.length;
	}
	
	private void shiftLeft(){
		for (int i = 0; i < this.tail; i++) {
			T aux = this.array[i];
			this.array[i] = this.array[i+1];
			this.array[i+1] = aux;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull())
			throw new QueueOverflowException();
		
		this.tail++;
		this.array[this.tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty())
			throw new QueueUnderflowException();
		
		T element = this.array[0];
		this.shiftLeft();
		this.tail--;
		return element;
	}
	
}
