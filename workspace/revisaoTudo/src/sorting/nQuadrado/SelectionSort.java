package sorting.nQuadrado;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		for(int i = leftIndex; i < rightIndex; i++){
			boolean swap = false;
			int iMenor = i;
			for(int j = i + 1; j <= rightIndex; j++){
				if(array[j].compareTo(array[iMenor]) < 0){
					iMenor = j;
					swap = true;
				}
			}
			if(swap)
				Util.swap(array, i, iMenor);
		}
		
	}
	
	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		SelectionSort<Integer> sort = new SelectionSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
	}

}
