package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		super();
		this.last = null;
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.head.getData().equals(element)) { // verifica se eh pra remover o primeiro elemento
				this.removeFirst();
				/*super.head = super.head.getNext();
				super.size--; */
			} else {
				DoubleLinkedListNode<T> prev = null;
				DoubleLinkedListNode<T> aux = ( DoubleLinkedListNode<T> ) super.head; // cast para usar variavel auxiliar
				
				// enquanto aux nao for null e nao corresponder ao elemento, faca
				while (aux != null && !aux.getData().equals(element)) {
					prev = aux;
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}
				
				// se achou, faca
				if (aux != null) {
					if (aux.equals(this.last)) { // se eh pra remover o ultimo, faca
						this.removeLast();
						/*this.last = prev;
						prev.setNext(null);*/
					} else {
						prev.setNext(aux.getNext());
						( (DoubleLinkedListNode<T>) aux.getNext() ).setPrevious(prev);
						super.size--;
					}
				}
			}
		}
	}
	
	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newNode;
		if (isEmpty()) { // se estiver fazia, faca
			newNode = new DoubleLinkedListNode<T>(element, null, null);
			super.head = newNode;
			this.last = newNode;
		} else {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) super.head; // variavel auxilia comecando de head
			
			while (aux.getNext() != null) {
				aux = (DoubleLinkedListNode<T>) aux.getNext();
			}
			newNode = new DoubleLinkedListNode<T>(element, null, aux);
			aux.setNext(newNode);
		}
		
		this.last = newNode;
		super.size++;
	}
	
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newNode;
		if (isEmpty()) { // se estiver vazia, faca
			newNode = new DoubleLinkedListNode<T>(element, null, null);
			super.head = newNode; // seta head
			this.last = newNode; // seta last
		} else {
			newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) super.head, null);
			((DoubleLinkedListNode<T>) super.head).setPrevious(newNode);
			super.head = newNode;
		}
		super.size++;
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) { 
			// verifica para lista de tamanho 1
			if (super.size() == 1) {
				super.head = this.last = (DoubleLinkedListNode<T>) super.head.getNext();
				super.size--;
			} else {
				( (DoubleLinkedListNode<T>) super.head.getNext() ).setPrevious(null); // altera o previous do segundo elemento pra null
				super.head = super.head.getNext(); // seta o head para o proximo elemento (segundo -> primeiro)
				super.size--;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			// verifica para lista de tamanho 1
			if (super.size() == 1) {
				super.head = this.last = (DoubleLinkedListNode<T>) super.head.getNext();
				super.size--;
			} else {
				this.last.getPrevious().setNext(null); // altera o next do penultimo elemento para null
				this.last = this.last.getPrevious(); // seta o last para o elemento anterior (penultimo -> ultimo)
				super.size--;
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return this.last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	public static void main(String[] args) {
		DoubleLinkedList<Integer> list = new DoubleLinkedListImpl<>();
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.toArray()));
		
		list.insert(3);
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.toArray()));
		
		list.removeLast();
		
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.toArray()));
		
//		list.insert(5);
//		list.insert(4);
//		list.insert(8);
//		list.insert(1);
		
		/*
		list.remove(1);
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.toArray()));
		
		list.removeLast();
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.toArray()));
		*/
		
		/*
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(list.size());
		
		list.remove(4);
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(list.size());
		
		list.insertFirst(0);
		System.out.println(Arrays.toString(list.toArray()));
		
		list.removeFirst(); // nao ta funcionando
		System.out.println(Arrays.toString(list.toArray()));
		
		list.removeLast();
		System.out.println(Arrays.toString(list.toArray()));
		
		list.remove(3);
		System.out.println(Arrays.toString(list.toArray()));
		
		list.remove(8);
		System.out.println(Arrays.toString(list.toArray()));
		
		list.removeFirst();
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(list.size());
		
		list.insert(1);
		System.out.println(list.size());
		
		list.removeLast();
		
		System.out.println(list.size());
		System.out.println(Arrays.toString(list.toArray()));
		*/
		
	}
	
	
}
