package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		if (isEmpty())
			return -1;
		
		return height(this.root);
	}
	
	private int height(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return Math.max(height((BSTNode<T>) node.getLeft()) + 1, height((BSTNode<T>) node.getRight()) + 1);
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.root, element);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty())
			return new BSTNode<>();
		else if (element.equals(node.getData()))
			return node;
		else if (element.compareTo(node.getData()) < 0)
			return search((BSTNode<T>) node.getLeft(), element);
		else
			return search((BSTNode<T>) node.getRight(), element);
	}
	
	@Override
	public void insert(T element) {
		insert(this.root, element);
	}
	
	private void insert(BSTNode<T> node, T element) { // enxugar codigo
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
		}
	}
	
//	private void insert(BSTNode<T> node, T element) { // enxugar codigo
//		if (element.compareTo(node.getData()) < 0) {
//			if (node.getLeft() == null || node.getLeft().isEmpty()) {
//				BSTNode<T> newNode = new BSTNode<>();
//				newNode.setData(element);
//				newNode.setParent(node);
//				newNode.setLeft((BTNode<T>) new BSTNode<>());
//				newNode.setRight((BTNode<T>) new BSTNode<>());
//				node.setLeft(newNode);
//			}
//			else
//				insert((BSTNode<T>) node.getLeft(), element);
//		} else if (element.compareTo(node.getData()) > 0) {
//			if (node.getRight() == null || node.getRight().isEmpty()) {
//				BSTNode<T> newNode = new BSTNode<>();
//				newNode.setData(element);
//				newNode.setParent(node);
//				newNode.setLeft((BTNode<T>) new BSTNode<>());
//				newNode.setRight((BTNode<T>) new BSTNode<>());
//				node.setRight(newNode);
//			} else
//				insert((BSTNode<T>) node.getRight(), element);
//		}
//	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;
		if (node.getRight().isEmpty())
			return node;
		else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}
	
	private BSTNode<T>minimum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;
		if (node.getLeft().isEmpty())
			return node;
		else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
//		if(isEmpty())
//			return null;
		
		BSTNode<T> node = search(element);
		
		if (node.isEmpty())
			return null;
		
		BSTNode<T> sucessor = minimum((BSTNode<T>) node.getRight());
		
		if (sucessor == null) {	
			sucessor = (BSTNode<T>) node.getParent();
			while (sucessor != null && sucessor.getData().compareTo(node.getData()) < 0) {
				sucessor = (BSTNode<T>) sucessor.getParent();
			}
			return sucessor;
		} else {
			return sucessor;
		}
	}
	
//	@Override
//	public BSTNode<T> sucessor(T element) {
//		BSTNode<T> node = search(element);
//		if (node != null) {
//			if (!node.getRight().isEmpty())
//				return minimum((BSTNode<T>) node.getRight());
//			else
//				return node;
//		}
//		return null;
//	}

	@Override
	public BSTNode<T> predecessor(T element) {
//		if(isEmpty())
//			return null;
//		
		BSTNode<T> node = search(element);
		
		if (node.isEmpty())
			return null;
		
		BSTNode<T> predecessor = maximum((BSTNode<T>) node.getLeft());
		
		if (predecessor == null) {	
			predecessor = (BSTNode<T>) node.getParent();
			while (predecessor != null && predecessor.getData().compareTo(node.getData()) > 0) {
				predecessor = (BSTNode<T>) predecessor.getParent();
			}
			return predecessor;
		} else {
			return predecessor;
		}
		
	}
	
//	@Override
//	public BSTNode<T> predecessor(T element) {
//		BSTNode<T> node = search(element);
//		if (node != null) {
//			if (!node.getLeft().isEmpty())
//				return maximum((BSTNode<T>) node.getLeft());
//			else
//				return null;
//		}
//		return null;
//	}

	@Override
	public void remove(T element) {
		
	}
	
	private void remove(T element, BSTNode<T> node) {
		if (node.isLeaf()) { // 1 caso - grau 0
			removeNoChildrenNode(node);
//			if (node.getData().compareTo(node.getParent().getData()) < 0) // se o no for menor q o pai
//				node.getParent().setLeft(null);
//			else
//				node.getParent().setRight(null);
		} else {
			if (node.getLeft() == null && node.getRight() != null) // remove node que esta a direita
				removeOneChildNode(node, true);
			else if (node.getLeft() != null && node.getRight() == null)
				removeOneChildNode(node, false);
			else {
				
			}
//			if (node.getLeft() == null && node.getRight() != null) // remove node que esta a direita
//				node.getParent().setRight(node.getRight());
//			else if (node.getLeft() != null && node.getRight() == null)
//				node.getParent().setLeft(node.getLeft());
//			else {
//				
//			}
		}
	}
	
	private void removeNoChildrenNode(BSTNode<T> node) {
		if (node.getData().compareTo(node.getParent().getData()) < 0) // se o no for menor q o pai
			node.getParent().setLeft(null);
		else
			node.getParent().setRight(null);
	}
	
	private void removeOneChildNode(BSTNode<T> node, boolean inTheRight) {
		if (inTheRight)
			node.getParent().setRight(node.getRight());
		else
			node.getParent().setLeft(node.getLeft());
	}
	
	private void removeTwoChildrenNode(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		BSTNode<T> aux = new BSTNode<>();
		
		aux.setData(sucessor.getData());
		aux.setLeft(sucessor.getLeft());
		aux.setRight(sucessor.getRight());
		aux.setParent(sucessor.getParent());
		
		sucessor.setData(node.getData());
		sucessor.setLeft(node.getLeft());
		sucessor.setRight(node.getRight());
		sucessor.setParent(node.getParent());
		
		node.setData(aux.getData());
		node.setLeft(aux.getLeft());
		node.setRight(aux.getRight());
		node.setParent(aux.getParent());
		
		
		
	}

	@Override
	public T[] preOrder() {
		if (isEmpty())
			return (T[]) new Comparable[] {};
		
		T[] array = (T[]) new Comparable[size()];
		preOrder(array, 0, this.root);
		return array;
	}
	
	private void preOrder(T[] array, int count, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[count] = (T) node.getData();
			count += 1;
			preOrder(array, count, (BSTNode<T>) node.getLeft()); // conferir o incremento
			preOrder(array, count, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		if (isEmpty()) {
			Object[] array =  new Comparable[] {};
			return (T[]) array;
		}
		T[] array = (T[]) new Comparable[size()];
		order(array, 0, this.root);
		return array;
	}
	
	private void order(T[] array, int count, BSTNode<T> node) {
		if (node != null || node.getData() != null) {
			order(array, count, (BSTNode<T>) node.getLeft());
			array[count++] = (T) node.getData();
			order(array, count, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		if (isEmpty())
			return (T[]) new Comparable[] {};
		
		T[] array = (T[]) new Comparable[size()];
		postOrder(array, 0, this.root);
		return array;
	}
	
	private void postOrder(T[] array, int count, BSTNode<T> node) {
		if (node != null || node.getData() != null) {
			postOrder(array, count, (BSTNode<T>) node.getLeft());
			postOrder(array, count, (BSTNode<T>) node.getRight());
			array[count++] = (T) node.getData();
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	
	public static void main(String[] args) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
		
		tree.root.printTree();
	}
}
