package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int iMenor;
		for(int i = leftIndex; i < rightIndex - 1; i++){
			iMenor = i;
			for(int j = leftIndex + 1; j < rightIndex; j++){
				if(array[i].compareTo(array[j]) == 1){
					Util.swap(array, j, i);
				}
			}
		}
	}
}
