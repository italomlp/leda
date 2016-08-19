package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
		
	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.next == null) {
			return 0;
		}
		return 1 + this.next.size();
	}

	@Override
	public T search(T element) {
		
		if (this.isEmpty()) {
			return null;
		} else if (this.data.equals(element)) {
			return this.data;
		} else {
			return this.next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()){
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<T>());
		} else {
			this.next.insert(element);
		}
		
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty()) {
			if (this.data.equals(element)){
				this.setData(this.next.getData());
				this.setNext(this.next.getNext());
			} else {
				this.next.remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		recursiveToArray(array, this, 0);
		
		return array;
	}
	
	private void recursiveToArray(T[] array, RecursiveSingleLinkedListImpl<T> current, int index) {
		if (!current.isEmpty()) {
			array[index] = current.getData();
			index++;
			recursiveToArray(array, current.next, index);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
