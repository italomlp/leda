package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if (size < 0)
			throw new RuntimeException("Tamanho negativo nao permitido.");
		
		this.array = (T[]) new Object[size];
		this.top = -1;
	}

	@Override
	public T top() {
		/*
		if (this.isEmpty())
			throw new StackUnderflowException();
		*/
		
		if (this.isEmpty())
			return null;
		
		return this.array[this.top];
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return (this.top + 1) == this.array.length;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull())
			throw new StackOverflowException();
		
		this.top++;
		this.array[this.top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty())
			throw new StackUnderflowException();
		
		return this.array[this.top--];
	}

}
