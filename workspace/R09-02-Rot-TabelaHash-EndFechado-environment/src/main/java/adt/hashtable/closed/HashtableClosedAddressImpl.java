package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method,
				realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int prime = number;
		
		while ( ! Util.isPrime(prime) )
			prime++;
		
		return prime;
	}

	@Override
	public void insert(T element) {
		// cria uma lista auxiliar referente ao respectivo indice do elemento, obtido atraves da funcao hash
		LinkedList<T> auxList = (LinkedList<T>) this.table[ ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element) ];
		
		if ( auxList == null ) // se for null, inicializa para poder adicionar, e atualiza a referencia da lista auxiliar
			auxList = (LinkedList<T>) ( this.table[ ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element) ] = new LinkedList<>() );
		
		else if ( ! auxList.isEmpty() ) // se nao, verifica se esta vazia. Se nao estiver, eh porque ha colisao
				this.COLLISIONS++;
		
		auxList.add(element);
		this.elements++;
	}

	@Override
	public void remove(T element) {
		LinkedList<T> auxList = (LinkedList<T>) this.table[ ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element) ];
		
		if ( auxList != null ) {
			
			// se o tamanho da lista correspondente ao indice referente ao elemento for maior que 1, eh porque ha colisao
			boolean hasCollision = auxList.size() > 1;
			
			if ( auxList.remove(element) ) {
				this.elements--;
				
				if (hasCollision) // se ha colisao, decrementa
					this.COLLISIONS--;
			}
		}
	}

	@Override
	public T search(T element) {
		LinkedList<T> auxList = (LinkedList<T>) this.table[ ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element) ];
		
		if ( auxList == null ) // se nao ha lista no indice referente eh porque a tabela nao contem o elemento
			return null;
		
		for (int i = 0; i < auxList.size(); i++){
			if ( auxList.get(i).equals(element) )
				return element;
		}
		
		return null;
	}

	@Override
	public int indexOf(T element) {
		if ( search(element) != null ) 
			return ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element);
		
		return -1;
	}

}
