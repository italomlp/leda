package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return recursiveBlackHeight((RBNode<T>) this.root);
	}
	
	private int recursiveBlackHeight(RBNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			int actualHeight = 0;
			
			if (node.getColour() == Colour.BLACK) {
				actualHeight += 1;
			}
			
			// ajeitar aqui
			return actualHeight + recursiveBlackHeight((RBNode<T>) node.getLeft());
		}
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodesRecursively((RBNode<T>) this.root);
	}

	private boolean verifyChildrenOfRedNodesRecursively(RBNode<T> node) {
		if (node.isEmpty()) {
			return true;
		} else if (node.getColour() == Colour.BLACK) {
			return verifyChildrenOfRedNodesRecursively((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodesRecursively((RBNode<T>) node.getRight());
		} else { // if (node.getColour() == Colour.RED) {
			if (((RBNode<T>) node.getLeft()).getColour() == Colour.RED ||
					((RBNode<T>) node.getRight()).getColour() == Colour.RED) {
				return false;
			} else {
				return verifyChildrenOfRedNodesRecursively((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodesRecursively((RBNode<T>) node.getRight());
			}
		}
	}
	
	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeightRecursively((RBNode<T>) this.root);
	}
	
	private boolean verifyBlackHeightRecursively(RBNode<T> node) {
		if (node.isEmpty()) {
			return true;
		} else {
			return recursiveBlackHeight((RBNode<T>) node.getLeft()) == recursiveBlackHeight((RBNode<T>) node.getRight());
		}
	}

	@Override
	public void insert(T element) {
		insert((RBNode<T>) this.root, element);
	}
	
	private void insert(RBNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft( new RBNode<>());
			node.getLeft().setParent(node);
			node.setRight( new RBNode<>());
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((RBNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert((RBNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		if (isEmpty())
			return new RBNode[0];
		
		RBNode<T>[] array = new RBNode[this.size()];
		preOrder(array, 0, (RBNode<T>) this.root);
		return array;
	}
	
	private int preOrder(RBNode<T>[] array, int count, RBNode<T> node) {
		if (!node.isEmpty()) {
			array[count++] = node;
			count = preOrder(array, count, (RBNode<T>) node.getLeft()); // conferir o incremento
			count = preOrder(array, count, (RBNode<T>) node.getRight());
		}
		return count;
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
	
	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node == this.root) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> uncle = (parent.getParent().getLeft() == parent)  ? (RBNode<T>) parent.getParent().getRight() : (RBNode<T>) parent.getParent().getLeft();
		
		if (uncle.getColour() == Colour.RED) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			((RBNode<T>) parent.getParent()).setColour(Colour.RED);
			fixUpCase1((RBNode<T>) parent.getParent());
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> aux = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		
		if (parent.getRight() == node && parent.getParent().getLeft() == parent) {
			leftRotation(parent);
			aux = (RBNode<T>) node.getLeft();
		} else if (parent.getLeft() == node && parent.getParent().getRight() == parent) {
			rightRotation(parent);
			aux = (RBNode<T>) node.getRight();
		}
		
		fixUpCase5(aux);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		
		parent.setColour(Colour.BLACK);
		((RBNode<T>) parent.getParent()).setColour(Colour.RED);
		
		if (parent.getLeft() == node) {
			rightRotation(((RBNode<T>) parent.getParent()));
		} else {
			leftRotation(((RBNode<T>) parent.getParent()));
		}
	}
}
