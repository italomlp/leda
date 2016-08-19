package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isFull())
				throw new HashtableOverflowException();
			
			if (!containsElement(element)) { // faz a verificacao de elementos, para evitar que um elemento com o mesmo hashCode seja adicionado

				int prob = 0; // prob comeca em 0
				int elemIndex =  ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, prob);
				
				while (prob < this.capacity() && this.table[elemIndex] != null) { // executa o laco ate achar uma posicao vazia (null) ou ate o fim da tabela
					elemIndex = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, ++prob);
					this.COLLISIONS++;
				}
				
//				if (prob < this.capacity()) { <- nao precisa fazer a verificacao, pois ja foi feita a verificacao da tabela estar cheia
					this.table[elemIndex] = element;
					this.elements++;
//				}
			}			
		}
	}
	
	/**
	 * This private method verifies if the table contains a element whit the same hashCode of the element to be added.
	 * @param element to be added
	 */
	private boolean containsElement(T element) {
		for ( Object elem : this.table) {
			if (elem != null && elem.hashCode() == element.hashCode())
				return true;
		}
		return false;
		
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int index = indexOf(element); // pega o indice do elemento, que sera -1 se nao estiver na tabela
			
			if (index != -1) { // se o elemento estiver na tabela, remove
				this.table[index] = null;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		int index = indexOf(element);
		
		if (index == -1) // se for -1, elemento nao existe
			return null;
		else
			return element;
	}

	@Override
	public int indexOf(T element) {
		
		if (element != null && ! isEmpty() ) { // verifica se a tabela nao esta vazia e se o elemento nao eh nulll 

			int prob = 0;
			int key = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, prob);
			
			// verifica ate achar o elemento, ou achar uma posicao vazia (null), ou ir ate o fim da tabela
			while (prob < this.capacity() && this.table[key] != null) {
				if (this.table[key].equals(element))
					return key;
				key = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, ++prob);
			}
		}
		return -1; // se chegar aqui, o elemento nao esta na tabela
	}

}
