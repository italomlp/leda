package sorting.divisaoEconquista;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(array == null || array.length == 0 || leftIndex < 0 || rightIndex < leftIndex){
			return;
		}
		
		if(leftIndex >= rightIndex){
			return;
		} else {
			int pivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivot-1);
			sort(array, pivot+1, rightIndex);
		}
	}
	
	private int partition(T[] array, int left, int right){
		int i = left;
		for(int j = left + 1; j <= right; j++){
			if(array[j].compareTo(array[i]) < 0){
				i++;
				int k = j;
				while(k >= i){
					Util.swap(array, k, k-1);
					k--;
				}
			}
		}
		return i;
	}

	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		QuickSort<Integer> sort = new QuickSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
	}
	
}
