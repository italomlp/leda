package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de 
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array 
 * a ser ordenado.  
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(array.length > 0){
			Integer k = array[leftIndex];
			for(int i = leftIndex; i <= rightIndex; i++)
				if(array[i] > k)
					k = array[i];
			
			int[] c = new int[k+1];
			
			// frequencia
			for(int i = leftIndex; i <= rightIndex; i++)
				c[array[i]] += 1;
			
			// cumulativa
			for(int i = 1; i < c.length; i++)
				c[i] += c[i-1];
			
			// popula um array ordenado de acordo com a cumulativa
			Integer[] sorted = new Integer[rightIndex - leftIndex + 1];
			for(int i = (rightIndex - leftIndex); i >= leftIndex; i--){
				sorted[c[array[i]] - 1] = array[i];
				c[array[i]] -= 1;
			}
			
			// substitue os valores do array original
			for(int i = leftIndex; i <= rightIndex; i++)
				array[i] = sorted[i];
		}
	}
}
