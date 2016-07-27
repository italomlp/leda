package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm. 
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if(array == null || leftIndex > rightIndex || leftIndex < 0)
			return;
		
		double fator = 1.25;
		int gap = rightIndex - leftIndex + 1;
		
		while(gap > 1){
			if(gap > 1)
				gap = (int) (gap / fator);
			
			for(int i = leftIndex; i + gap <= rightIndex; i++){
				if(array[i].compareTo(array[i + gap]) > 0)
					Util.swap(array, i, i + gap);
			}
		}
	}
}
