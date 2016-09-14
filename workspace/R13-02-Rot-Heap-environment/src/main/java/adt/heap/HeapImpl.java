package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = (T[]) new Comparable[index + 1];
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int max = position;
		
		int left = left(position);
		int right = right(position);
		
		if (left <= this.index && this.comparator.compare(this.heap[left], this.heap[max]) > 0)
			max = left;
		if (right <= this.index && this.comparator.compare(this.heap[right], this.heap[max]) > 0)
			max = right;
		
		if (max != position) {
			Util.swap(heap, position, max);
			heapify(max);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		
		if (element == null)
			return;
		
		this.index++;
		this.heap[this.index] = element;
		
		int i = this.index;
		while (i > 0 && this.comparator.compare(this.heap[parent(i)], this.heap[i]) < 0) { // ver se ta certo o sinal de menor que
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array == null || array.length == 0)
			return;
		
//		for (int i = 0; i < array.length; i++)
//			insert(array[i]);
		
		this.heap = Arrays.copyOf(array, array.length); 
		this.index = array.length - 1;
		
		for (int i = parent(this.index); i > -1; i--)
			heapify(i);
	}

	@Override
	public T extractRootElement() {
		T root = rootElement(); // se heap estiver vazia, root vai ser null
		if (root != null) { // se nao ta vazia, faca
			Util.swap(heap, 0, index--);
			heapify(0);
		}
		return root;
	}

	@Override
	public T rootElement() {
		if (isEmpty())
			return null;
		
		return this.heap[0]; 
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array == null)
			return null;
		
		// In-Place
		Comparator<T> oldComparator = getComparator(); // guarda comparator antigo para restaurar depois
		setComparator(new Comparator<T>() { // seta comparator para MinHeap

			@Override
			public int compare(T o1, T o2) {
				return -o1.compareTo(o2);
			}
		});
		
		buildHeap(array);
		T[] sorted = (T[]) new Comparable[array.length];
		
		for (int i = 0; i < array.length; i++)
			sorted[i] = extractRootElement();
		setComparator(oldComparator);
		
		return sorted;
		
		/* Not In-Place
		Heap<T> newHeap = new HeapImpl<>(new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return -o1.compareTo(o2);
			}
		});
		
		newHeap.buildHeap(array);
		
		T[] sorted = (T[]) new Comparable[array.length];
		
		for (int i = 0; i < array.length; i++) {
			sorted[i] = newHeap.extractRootElement();
		}
		
		return sorted;
		*/
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
