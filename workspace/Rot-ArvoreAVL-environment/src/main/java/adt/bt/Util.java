package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir o seu filho a direita e retorna-lo em seguida
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		
		if (pivot.getParent() != null) {  
			if (pivot.getData().compareTo(pivot.getParent().getData()) < 0) 
				pivot.getParent().setLeft(pivot);
			else
				pivot.getParent().setRight(pivot);
		}
		
		return pivot;
		
	}

	/**
	 * A rotacao a direita em node deve subir seu filho a esquerda s retorna-lo em seguida
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.setRight(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		
		if (pivot.getParent() != null) {  
			if (pivot.getData().compareTo(pivot.getParent().getData()) > 0) 
				pivot.getParent().setLeft(pivot);
			else
				pivot.getParent().setRight(pivot);
		}
		
		return pivot;
	}

}
