package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements SplayTree<T> {

	
	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return new BSTNode<T>();
		}
		
		BSTNode<T> node = search(this.root, element);
		
		if (!node.isEmpty()) {
			splay(node);
		} else {
			splay((BSTNode<T>) node.getParent());
		}
		
		return node;
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return node;
		} else if (element.equals(node.getData()))
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
			splay(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			BSTNode<T> node = super.search(element);
			if (node.isEmpty()) {
				splay((BSTNode<T>) node.getParent());
			} else {
				BSTNode<T> aux = remove(node);
				if (aux != null)
					splay((BSTNode<T>) aux.getParent());
			}
			
		}
	}
	
	private BSTNode<T> remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {// 1 caso - grau 0
				node.setData(null);
				return node;
			} else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || 
					(node.getLeft().isEmpty() && !node.getRight().isEmpty())) {// 2 caso - grau 1
				return removeOneChildNode(node);
			} else // 3 caso - grau 3
				return removeTwoChildrenNode(node);
		}
		return new BSTNode<T>();
	}
	
	private BSTNode<T> removeOneChildNode(BSTNode<T> node) {
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
		return (BSTNode<T>) aux.getParent();
	}
	
	private BSTNode<T> removeTwoChildrenNode(BSTNode<T> node) {
		BSTNode<T> aux = minimum((BSTNode<T>) node.getRight());

		T data = node.getData();

		node.setData(aux.getData());
		aux.setData(data);

		return remove(aux);
	}
	
	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> aux = Util.leftRotation(node);
		if (aux.getParent() == null) {
			this.root = aux;
		}
	}
	
	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> aux = Util.rightRotation(node);
		if (aux.getParent() == null) {
			this.root = aux;
		}
	}
	
	private void splay(BSTNode<T> node) {
		if (node == null || node.isEmpty() || node == this.root)
			return ;
		
		BSTNode<T> parent = null;
		while (node != this.root && !node.isEmpty()) {
			parent = (BSTNode<T>) node.getParent();
			// filho da raiz esquerda
			if (node == this.root.getLeft()) {
				rightRotation(parent);
			// filho da raiz a direita
			} else if (node == this.root.getRight()) {
				leftRotation(parent);
			// rotacao dupla a direita
			} else if (parent.getParent().getLeft() == parent && parent.getLeft() == node) {
				rightRotation((BSTNode<T>) parent.getParent());
				rightRotation(parent);
			// rotacao dupla a esquerda
			} else if (parent.getParent().getRight() == parent && parent.getRight() == node) {
				leftRotation((BSTNode<T>) parent.getParent());
				leftRotation(parent);
			// zig-zag's
			} else if (parent.getParent().getLeft() == parent && parent.getRight() == node) {
				leftRotation(parent);
				rightRotation((BSTNode<T>) node.getParent());
			} else if (parent.getParent().getRight() == parent && parent.getLeft() == node) {
				rightRotation(parent);
				leftRotation((BSTNode<T>) node.getParent());
			} else {
				break;
			}
		}
	}

}
