package sorting.variacoes;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do MergeSort 
 * que pode fazer uso do InsertionSort (um algoritmo híbrido) da seguinte forma: 
 * o MergeSort é aplicado a entradas maiores a um determinado limite. Caso a entrada 
 * tenha tamanho menor ou igual ao limite o algoritmo usa o InsertionSort. 
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de 
 *   forma que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada
 *   chamada interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e 
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 *  - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;
	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null || array.length == 0 || leftIndex < 0 || rightIndex < leftIndex){
			return;
		}
		
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		hybridMergeSort(array, leftIndex, rightIndex);
	}
	
	private void hybridMergeSort(T[] array, int left, int right){
		if(left >= right){
			return;
		} else {
			if((right - left + 1) <= 4){
				inserctionSort(array, left, right);
			} else {
				int med = (left + right) / 2;
				hybridMergeSort(array, left, med);
				hybridMergeSort(array, med + 1, right);
				merge(array, left, med, right);
			}
		}
		
	}
	
	private void merge(T[] array, int left, int mid, int right){
		MERGESORT_APPLICATIONS++;
		T[] helper = Arrays.copyOf(array, array.length);
		
		int i = left;
		int j = mid + 1;
		int k = left;
		while(i <= mid && j <= right){
			if(helper[i].compareTo(helper[j]) <= 0)
				array[k++] = helper[i++];
			else
				array[k++] = helper[j++];
		}
		
		while(i <= mid)
			array[k++] = helper[i++];
		
		while(j <= right)
			array[k++] = helper[j++];
		
		
	}
	
	private void inserctionSort(T[] array, int left, int right){
		INSERTIONSORT_APPLICATIONS++;
		for(int i = left; i <= right; i++){
			int iEleito = i;
			
			int j = i - 1;
			while(j >= left && array[j].compareTo(array[iEleito]) > 0){
				Util.swap(array, iEleito, j);
				j--;
				iEleito--;
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		HybridMergeSort<Integer> sort = new HybridMergeSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
		System.out.println(INSERTIONSORT_APPLICATIONS);
		System.out.println(MERGESORT_APPLICATIONS);
	}

}
