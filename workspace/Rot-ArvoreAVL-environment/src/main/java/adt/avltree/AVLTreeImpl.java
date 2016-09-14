package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	
	@Override
	public void insert(T element) {
		insert(this.root, element);
	}
	
	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft((BTNode<T>) new BSTNode<>());
			node.getLeft().setParent(node);
			node.setRight((BTNode<T>) new BSTNode<>());
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			}
			rebalance(node);
		}
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			BSTNode<T> node = search(element);
			super.remove(node);
			rebalanceUp(node);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null) { // Verificacao necessaria ?? 
			return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
		}
		// return 0; ??
		return -1;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		
		if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0)
				doubleLeftRotation(node);
			else
				leftRotation(node);
		} else if (balance < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0)
				doubleRightRotation(node);
			else
				rightRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		/* 
		 * ********* Codigo puro ********* 
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		
		if (pivot.getParent() == null)  
			this.root = pivot;
		else {
			if (pivot.getData().compareTo(pivot.getParent().getData()) < 0) 
				pivot.getParent().setLeft(pivot);
			else
				pivot.getParent().setRight(pivot);
		}
		*/
		
		/* ********* Codigo reaproveitado de Util ********* */
		BSTNode<T> aux = Util.leftRotation(node);
		if (aux.getParent() == null)
			this.root = aux;
	}
	
	protected void doubleLeftRotation(BSTNode<T> node) {
		rightRotation((BSTNode<T>) node.getRight());
		leftRotation((BSTNode<T>) node);
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		/*
		 * ********* Codigo puro *********
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.setRight(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		
		if (pivot.getParent() == null)  
			this.root = pivot;
		else {
			if (pivot.getData().compareTo(pivot.getParent().getData()) > 0) 
				pivot.getParent().setLeft(pivot);
			else
				pivot.getParent().setRight(pivot);
		}
		*/
		
		/* ********* Codigo reaproveitado de Util ********* */
		BSTNode<T> aux = Util.rightRotation(node);
		if (aux.getParent() == null)
			this.root = aux;
	}
	
	protected void doubleRightRotation(BSTNode<T> node) {
		leftRotation((BSTNode<T>) node.getLeft());
		rightRotation((BSTNode<T>) node);
	}

}
