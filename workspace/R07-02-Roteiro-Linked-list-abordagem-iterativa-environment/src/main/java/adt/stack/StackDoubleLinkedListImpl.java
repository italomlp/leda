package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

	@Override
	public T top() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

}