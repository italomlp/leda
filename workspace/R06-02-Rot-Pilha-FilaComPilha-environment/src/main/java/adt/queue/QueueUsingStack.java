package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	
	public QueueUsingStack(int size) {
		if (size < 0)
			throw new RuntimeException("Tamanho negativo nao permitido.");
		
		this.stack1 = new StackImpl<T>(size);
		this.stack2 = new StackImpl<T>(size);
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		try {
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		try {
			
			while (!this.stack1.isEmpty())
				this.stack2.push(this.stack1.pop());
			
			T element = this.stack2.pop();
			
			while (!this.stack2.isEmpty())
				this.stack1.push(this.stack2.pop());
			
			return element;
			
		} catch (Exception e) {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {

		try {
			
			while (!this.stack1.isEmpty())
				this.stack2.push(this.stack1.pop());
			
			T element = this.stack2.pop();
			
			while (!this.stack2.isEmpty())
				this.stack1.push(this.stack2.pop());
			
			return element;
			
		} catch (Exception e) {
//			throw new RuntimeException();
			return null;
		}
		
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
