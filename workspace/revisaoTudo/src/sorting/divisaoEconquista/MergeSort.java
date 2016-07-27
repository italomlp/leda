package sorting.divisaoEconquista;

import java.util.Arrays;

import sorting.AbstractSorting;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
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
		T[] helper = Arrays.copyOf(array, array.length);
		
		int i = left;
		int j = med + 1;
		int k = left;
		while(i <= med && j <= right){
			if(helper[i].compareTo(helper[j]) <= 0){
				array[k++] = helper[i++];
//				i++;
			} else {
				array[k++] = helper[j++];
//				j++;
			}
//			k++;
		}
		
		while(i <= med){
			array[k++] = helper[i++];
//			i++;
//			k++;
		}
		
		while(j <= right){
			array[k++] = helper[j++];
//			j++;
//			k++;
		}
		
	}
	
	public static void main(String[] args) {
		Integer[] v = new Integer[] {2,4,12,4,6,34,1,32,0};
		MergeSort<Integer> sort = new MergeSort<>();
		
		sort.sort(v);
		
		System.out.println(Arrays.toString(v));
	}

}
