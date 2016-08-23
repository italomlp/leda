package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		OrderedLinkedList<T> {

	private Comparator<T> comparator;

	public OrderedSingleLinkedListImpl() {
		this.comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return ((Comparable) o1).compareTo((Comparable) o2);
			}
			
		};
	}

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			
			if (isEmpty()) {
				super.head = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			} else {

				SingleLinkedListNode<T> auxNode = super.head;
				
				while (!auxNode.getNext().isNIL() && this.comparator.compare(auxNode.getNext().getData(), element) < 0) {
					auxNode = auxNode.getNext();
				}
				
				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>();
				newNode.setData(element);
				
				if (auxNode == super.head) {
					newNode.setNext(super.head);
					super.head = newNode;
				} else {

					newNode.setNext(auxNode.getNext());
					
					auxNode.setNext(newNode);
				}
				
			}
			super.size++;
		}
	}

	@Override
	public T minimum() {
		return (!isEmpty()) ? super.head.getData() : null;
	}

	@Override
	public T maximum() {
		if (!isEmpty()) {
			SingleLinkedListNode<T> auxNode = super.head;
			
			while (!auxNode.getNext().isNIL()) {
				auxNode = auxNode.getNext();
			}
			
			return auxNode.getData();
		}
		return null;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
