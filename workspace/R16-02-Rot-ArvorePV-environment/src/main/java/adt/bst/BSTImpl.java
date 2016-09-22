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
		return height(this.root) - 1;
	}
	
	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			return Math.max(height((BSTNode<T>) node.getLeft()) + 1, height((BSTNode<T>) node.getRight()) + 1) ;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return new BSTNode<T>();
		}
		return search(this.root, element);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty())
			return node;
//			return new BSTNode<>();
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
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}
	
	protected BSTNode<T> maximum(BSTNode<T> node) {
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
	
	protected BSTNode<T>minimum(BSTNode<T> node) {
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
		BSTNode<T> node = search(element);
		
		if (node.isEmpty())
			return null;
		
		BSTNode<T> sucessor = minimum((BSTNode<T>) node.getRight());
		
		if (sucessor == null) {	
			sucessor = (BSTNode<T>) node.getParent();
			while (sucessor != null && sucessor.getData().compareTo(node.getData()) < 0) {
				sucessor = (BSTNode<T>) sucessor.getParent();
			}
		}
		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		
		if (node.isEmpty())
			return null;
		
		BSTNode<T> predecessor = maximum((BSTNode<T>) node.getLeft());
		
		if (predecessor == null) {	
			predecessor = (BSTNode<T>) node.getParent();
			while (predecessor != null && predecessor.getData().compareTo(node.getData()) > 0) {
				predecessor = (BSTNode<T>) predecessor.getParent();
			}
		} 
		return predecessor;
	}

	@Override
	public void remove(T element) {
		if (!isEmpty())
			remove(search(element));
	}
	
	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) // 1 caso - grau 0
				node.setData(null);
			else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || 
					(node.getLeft().isEmpty() && !node.getRight().isEmpty())) // 2 caso - grau 1
				removeOneChildNode(node);
			else // 3 caso - grau 3
				removeTwoChildrenNode(node);
		}
	}
	
	private void removeOneChildNode(BSTNode<T> node) {
		BSTNode<T> aux;
		
		if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) 
			aux = (BSTNode<T>) node.getLeft();
		else
			aux = (BSTNode<T>) node.getRight();
		
		if (node.getParent() == null) {
			aux.setParent(null);
			this.root = aux;
		} else {
			if (!node.getParent().isEmpty() && !node.getParent().getLeft().isEmpty()
					&& node.getParent().getLeft().getData().equals(node.getData())) 
				node.getParent().setLeft(aux);
			
			else 
				node.getParent().setRight(aux);
			
			aux.setParent(node.getParent());
		}
	}
	
	private void removeTwoChildrenNode(BSTNode<T> node) {
		BSTNode<T> aux = minimum((BSTNode<T>) node.getRight());

		T data = node.getData();

		node.setData(aux.getData());
		aux.setData(data);

		remove(aux);
	}

	@Override
	public T[] preOrder() {
		if (isEmpty())
			return (T[]) new Comparable[] {};
		
		T[] array = (T[]) new Comparable[size()];
		preOrder(array, 0, this.root);
		return array;
	}
	
	private int preOrder(T[] array, int count, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[count++] = (T) node.getData();
			count = preOrder(array, count, (BSTNode<T>) node.getLeft()); // conferir o incremento
			count = preOrder(array, count, (BSTNode<T>) node.getRight());
		}
		return count;
	}

	@Override
	public T[] order() {
		if (isEmpty()) 
			return (T[]) new Comparable[] {};
		
		T[] array = (T[]) new Comparable[size()];
		order(array, 0, this.root);
		return array;
	}
	
	private int order(T[] array, int count, BSTNode<T> node) {
		if (!node.isEmpty()) {
			count = order(array, count, (BSTNode<T>) node.getLeft());
			array[count++] = (T) node.getData();
			count = order(array, count, (BSTNode<T>) node.getRight());
		}
		return count;
	}

	@Override
	public T[] postOrder() {
		if (isEmpty())
			return (T[]) new Comparable[] {};
		
		T[] array = (T[]) new Comparable[size()];
		postOrder(array, 0, this.root);
		return array;
	}
	
	private int postOrder(T[] array, int count, BSTNode<T> node) {
		if (!node.isEmpty()) {
			count = postOrder(array, count, (BSTNode<T>) node.getLeft());
			count = postOrder(array, count, (BSTNode<T>) node.getRight());
			array[count++] = (T) node.getData();
		}
		return count;
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
	
}
