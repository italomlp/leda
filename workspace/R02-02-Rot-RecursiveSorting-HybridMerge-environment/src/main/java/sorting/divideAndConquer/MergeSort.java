package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex){
			return;
		} else {
			int med = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, med);
			sort(array, med+1, rightIndex);
			merge(array, leftIndex, med, rightIndex);
		}
	}
	
	private void merge(T[] array, int left, int med, int right){
		T[] helper = Util.<T>makeArray(array.length);
		for(int x = 0; x < array.length; x++)
			helper[x] = array[x];
		
		int i = left;
		int j = med + 1;
		int k = left;
		while(i <= med && j <= right){
			if((helper[i]).compareTo(helper[j]) == -1){
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
}
