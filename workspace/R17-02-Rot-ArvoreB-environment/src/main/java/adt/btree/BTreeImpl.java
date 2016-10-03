package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root) - 1;
	}

	private int height(BNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			if (node.isLeaf()) {
				return 1;
			} else {
				return height(node.getChildren().getFirst()) + 1 ;
			}
		}
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public int size() {
		return size(this.root);
	}
	
	private int size(BNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			int result = 0;
			for (BNode<T> aux : node.getChildren()) { 
				result += size(aux);
			}
			return result;
		}
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(this.root, element);
	}
	
	private BNodePosition<T> search(BNode<T> node, T element) {
		int index = 0;
		while (index < node.elements.size() && element.compareTo(node.elements.get(index)) > 0) {
			index++;
		}
		
		if (index <= node.elements.size() && element == node.elements.get(index)) {
			return new BNodePosition<>(node, index);
		}
		
		if (node.isLeaf()) {
			return new BNodePosition<>();
		}
		
		return search(node.getChildren().get(index), element);
	}

	@Override
	public void insert(T element) {
		BNode<T> node = leafToAdd(element, this.root);
		node.addElement(element);
		split(node);
	}
	
	private BNode<T> leafToAdd(T element, BNode<T> node){
		int index = 0;
		while((index < node.size()) && (node.getElementAt(index).compareTo(element) < 0)){
			index++;
		}
		if(node.isLeaf()){
			return node;
		}
		return leafToAdd(element, node.getChildren().get(index));
	}

	private void split(BNode<T> node) {
		if (node.isFull()) {
			int med = node.getElements().size() / 2;
			T elemMed = node.getElementAt(med);
			
			BNode<T> left = new BNode<>(node.maxChildren);
			BNode<T> right = new BNode<>(node.maxChildren);
			
			LinkedList<T> leftList = new LinkedList<>();
			for (int i = 0; i < med; i++) {
				leftList.add(node.getElementAt(i));
			}
			
			LinkedList<T> rightList = new LinkedList<>();
			for (int i = med + 1; i < node.getElements().size(); i++) {
				rightList.add(node.getElementAt(i));
			}
			
			left.setElements(leftList);
			right.setElements(rightList);
			
			BNode<T> parent;
			if (node.getParent() == null) {
				parent = new BNode<>(node.maxChildren);
				parent.addElement(elemMed);
				parent.addChild(0, left);
				parent.addChild(1, right);
				this.root = parent;
			} else {
				parent = node.getParent();
				parent.addElement(elemMed);
				int index = 0;
				for (int i = 0; i < parent.getElements().size(); i++) {
					if (parent.getElements().get(i).compareTo(elemMed) > 0) {
						index = i;
					}
				}
				parent.addChild(index, left);
				parent.addChild(index+1, right);
			}
			left.parent = right.parent = parent;
			split(parent);
		}
	}

	private void promote(BNode<T> node) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
