package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. 
 * Desta vez este algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		if(array.length > 0){
			Integer maior = array[leftIndex];
			Integer menor = array[leftIndex];
			
			for(int i = leftIndex; i <= rightIndex; i++){
				if(array[i] > maior)
					maior = array[i];
				if(array[i] < menor)
					menor = array[i];
			}
			
			int[] c = new int[maior - menor + 1];
			
			// frequencia
			for(int i = leftIndex; i <= rightIndex; i++)
				c[array[i] - menor] += 1;
			
			// cumulativa
			for(int i = 1; i < c.length; i++)
				c[i] += c[i-1];
			
			// popula um array ordenado de acordo com a cumulativa
			Integer[] sorted = new Integer[rightIndex - leftIndex + 1];
			for(int i = rightIndex - leftIndex; i >= leftIndex; i--){
				sorted[c[array[i] - menor] - 1] = array[i];
				c[array[i] - menor] -= 1;
			}
			
			// substitue os valores do array original
			for(int i = 0; i < sorted.length; i++){
				if(sorted[i] == null)
					continue;
				
				array[leftIndex] = sorted[i];
				leftIndex += 1;
			}
		}
	}
}
