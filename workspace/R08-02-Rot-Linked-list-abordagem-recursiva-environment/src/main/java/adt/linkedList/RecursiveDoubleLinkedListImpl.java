package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> newElement = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this);
			
			this.setData(element);
			this.setNext(newElement);
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> aux1 = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
			
			this.setData(aux1.getData());
			this.setNext(aux1.getNext());

			if (aux1.next != null) {
				RecursiveDoubleLinkedListImpl<T> aux2 = (RecursiveDoubleLinkedListImpl<T>) aux1.getNext();
				aux2.setPrevious(this);
			}
		}
		
	}

	@Override
	public void removeLast() {
		if (this.next != null) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
		}
	}
	
	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.setData(element);
			this.setPrevious((RecursiveDoubleLinkedListImpl<T>) new RecursiveDoubleLinkedListImpl<>());
			this.setNext((RecursiveSingleLinkedListImpl<T>) new RecursiveDoubleLinkedListImpl<>());
		} else {
			RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.next;
			aux.insert(element, this);
		}
	}
	
	private void insert(T element, RecursiveDoubleLinkedListImpl<T> previous) {
		if (isEmpty()) {
			this.setData(element);
			this.setPrevious(previous);
			this.setNext((RecursiveSingleLinkedListImpl<T>) new RecursiveDoubleLinkedListImpl<>());
		} else {
			RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.next;
			aux.insert(element, this);
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
