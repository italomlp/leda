package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	protected int size;
	
	
	public SingleLinkedListImpl() {
//		this.head = new SingleLinkedListNode<T>();
		this.head = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.head == null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.head;
		while (aux != null && !aux.getData().equals(element)) {
			aux = aux.getNext();
		}
		if (aux == null)
			return null;
		
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if (isEmpty())
			this.head = new SingleLinkedListNode<T>(element, null);
		else {
			SingleLinkedListNode<T> aux = this.head;
			while (aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(new SingleLinkedListNode<T>(element, null));
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
				SingleLinkedListNode<T> prev = null;
				SingleLinkedListNode<T> aux = this.head;
				
				while (aux != null && !aux.getData().equals(element)) {
					prev = aux;
					aux = aux.getNext();
				}
				
				if (aux != null) {
					prev.setNext(aux.getNext());
					this.size--;
				}
			}
		}
	}
	@Override
	public T[] toArray(){
		SingleLinkedListNode<T> aux = this.head;
		T[] array = (T[]) new Object[this.size];
		int i = 0;
		while (aux != null) {
			array[i++] = (T) aux.getData();
			aux = aux.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

	
}
