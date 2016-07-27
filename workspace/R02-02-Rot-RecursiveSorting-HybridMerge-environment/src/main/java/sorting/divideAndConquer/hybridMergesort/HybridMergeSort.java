package sorting.divideAndConquer.hybridMergesort;

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
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		doSort(array, leftIndex, rightIndex);
	}
	
	private void doSort(T[] array, int leftIndex, int rightIndex){
		if((rightIndex - leftIndex) <= SIZE_LIMIT)
			inserctionSort(array, leftIndex, rightIndex);
		else {
			mergeSort(array, leftIndex, rightIndex);
		}
	}
	
	public void mergeSort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex){
			return;
		} else {
			int med = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, med);
			sort(array, med+1, rightIndex);
			merge(array, leftIndex, med, rightIndex);
		}
	}
	
	private <T> void merge(T[] array, int left, int med, int right){
		T[] helper = Util.<T>makeArray(array.length);
		for(int x = 0; x < array.length; x++)
			helper[x] = array[x];
		
		int i = left;
		int j = med + 1;
		int k = left;
		while(i <= med && j <= right){
			if(((Comparable<T>) helper[i]).compareTo(helper[j]) == -1){
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		while(i <= med){
			array[k] = helper[i];
			i++;
			k++;
		}
		while(j <= right){
			array[k] = helper[j];
			j++;
			k++;
		}
	}
	
	private void inserctionSort(T[] array, int leftIndex, int rightIndex) {
		T eleito;
		int j;
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			eleito = array[i];
			j = i;
			
			while(j > leftIndex && (array[j - 1].compareTo(eleito)) == 1){
				Util.swap(array, j, j-1);
				j--;
			}
		}
		INSERTIONSORT_APPLICATIONS += 1;
	}
}
