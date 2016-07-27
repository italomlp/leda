package sorting.nQuadrado;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class InserctionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null || array.length == 0 || leftIndex < 0 || rightIndex < leftIndex){
			return;
		}
		
		for(int i = leftIndex; i <= rightIndex; i++){
			int iEleito = i;

			int j = i - 1;
			while(j >= leftIndex && array[j].compareTo(array[iEleito]) > 0){
				Util.swap(array, iEleito, j);
				iEleito--;
				j--;
			}
			
		}
		
	}

	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		InserctionSort<Integer> sort = new InserctionSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
	}
	
}
