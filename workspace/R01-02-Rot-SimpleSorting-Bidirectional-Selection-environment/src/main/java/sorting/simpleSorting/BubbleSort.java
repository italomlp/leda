package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex; i < rightIndex; i++){
			for(int j = rightIndex; j > i; j--){
				if(array[i].compareTo(array[j]) == 1){
					Util.swap(array, j, i);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		BubbleSort<Integer> bs = new BubbleSort<Integer>();
		Integer[] array = {1,4,3,5,2,8,11,1,55,6,3};
		bs.sort(array);
		for(int i : array){
			System.out.print(i + " ");
		}
	}
}
