package sorting.variacoes;

import java.text.Bidi;
import java.util.Arrays;

import sorting.AbstractSorting;
import sorting.divisaoEconquista.MergeSort;
import util.Util;

/**
 * This selection sort variation has two internal iterations. In the first, it takes the
 * smallest elements from the array, and puts it in the first position. In the second,
 * the iteration is done backwards, that is, from right to left, and this time the biggest
 * element is selected and stored in the last position. Then it repeats the process,
 * excluding the positions already filled in, until the whole array is ordered.
 */
public class BidirectionalSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null || array.length == 0 || leftIndex < 0 || rightIndex < leftIndex){
			return;
		}
		
		int done = 0;
		
		for(int i = leftIndex; i <= rightIndex - done; i++){
			int iMenor = i;
			for(int j = i + 1; j <= rightIndex - done; j++){
				if(array[j].compareTo(array[iMenor]) < 0){
					iMenor = j;
				}
			}
			Util.swap(array, i, iMenor);
			
			int iMaior = rightIndex - done;
			for(int k = rightIndex - done; k >= i; k--){
				if(array[k].compareTo(array[iMaior]) > 0){
					iMaior = k;
				}
			}
			Util.swap(array, iMaior, rightIndex - done);
			done++;
		}
	}
	
	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		BidirectionalSelectionSort<Integer> sort = new BidirectionalSelectionSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
	}

}
