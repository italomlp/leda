package sorting.variationsOfSelectionsort;


import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm simulates a logical partitioning of the input array by considering 
 * different indexing, that is, the first sub-array is indexed by even elements and
 * the second sub-array is indexed by odd elements. Then, it applies a complete selectionsort
 * in the first sub-array considering neighbours (even). After that, 
 * it applies a complete selectionsort in the second sub-array considering
 * neighbours (odd).  After that, the algorithm performs a merge between elements indexed
 * by even and odd numbers.
 */
public class OddEvenSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex){
		// Verifies exceptional cases
		if(array == null || array.length == 0 || leftIndex > rightIndex || leftIndex < 0)
			return;
		
		// Outer loop of Selection Sort
		for(int j = leftIndex; j <= rightIndex; j++){
			
			// Odd (if leftIndex is even - even if is odd) - executes the selection under odd indexes
			innerLoopOfSelection(array, j+1, rightIndex);
			
			// Even (if leftIndex is even - odd if is odd) - executes the selection under even indexes
			innerLoopOfSelection(array, j, rightIndex);
		}
		
		// executes merge under the odd and even indexes
		merge(array, leftIndex, rightIndex);
		
	}
	
	private void innerLoopOfSelection(T[] array, int j, int rightIndex){
		boolean swapped = false;
		int lowerIndex = j;
		for(int i = lowerIndex; i <= rightIndex; i += 2){
			if(array[i].compareTo(array[lowerIndex]) < 0){
				lowerIndex = i;
				swapped = true;
			}
		}
		if(swapped)
			Util.swap(array, j, lowerIndex);
		
	}
	
	private void merge(T[] array, int left, int right){
		T[] helper = Arrays.copyOf(array, array.length);
		
		int i = left;
		int j = left+1;
		int k = left;
		while(i <= right && j <= right){
			if(helper[i].compareTo(helper[j]) < 0){
				array[k] = helper[i];
				i += 2;
			} else {
				array[k] = helper[j];
				j += 2;
			}
			k++;
		}
		
		while(i <= right){
			array[k] = helper[i];
			i += 2;
			k++;
		}
		while(j <= right){
			array[k] = helper[j];
			j += 2;
			k++;
		}
		
	}

}
