package sorting.nQuadrado;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null || array.length == 0 || leftIndex < 0 || rightIndex < leftIndex){
			return;
		}
		
		for(int i = leftIndex; i < rightIndex; i++){
			for(int j = i + 1; j <= rightIndex; j++){
				if(array[i].compareTo(array[j]) > 0){
					Util.swap(array, i, j);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		BubbleSort<Integer> sort = new BubbleSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
		
	}

	
	
}
