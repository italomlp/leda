package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	protected int size;
	
	
	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<>();
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
//		return this.head.isNIL();
		return this.size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T search(T element) {
		
		SingleLinkedListNode<T> aux = this.head;
		
		while (!aux.isNIL() && !aux.getData().equals(element)) {
			aux = aux.getNext();
		}
		
		if (aux.isNIL())
			return null;
		
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;
		
		if (isEmpty())
			this.head = new SingleLinkedListNode<T>(element, null);
		else {
			
			SingleLinkedListNode<T> aux = this.head;
			
			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			
			
			aux.setNext(new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>()));
		}
		this.size++;
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			
			if (this.head.getData().equals(element)) {
				
				this.head = this.head.getNext();
				this.size--;
				
			} else {
				
				SingleLinkedListNode<T> prev = this.head;
				SingleLinkedListNode<T> aux = this.head;
				
				while (!aux.isNIL() && !aux.getData().equals(element)) {
					prev = aux;
					aux = aux.getNext();
				}
				
				if (!aux.isNIL()) {
					
					prev.setNext(aux.getNext());
					this.size--;
					
				}
			}
		}
	}
	
	@Override
	public T[] toArray(){
		
		if (isEmpty())
			return (T[]) new Object[] {};
		else {
			SingleLinkedListNode<T> aux = this.head;
			T[] array = (T[]) new Object[this.size];
			
			int i = 0;
			while (!aux.isNIL()) {
				
				array[i++] = (T) aux.getData();
				aux = aux.getNext();
				
			}
			
			return array;
		}
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

	
}
