package eecs2030.lab8;

/**
 * A class that presents an immutable view of a node of a
 * binary search tree. While an <code>INode</code> cannot be 
 * used to change the left or right child of a node, it is 
 * possible to change the state of the element stored in the 
 * node if <code>E</code> is a mutable type.
 * 
 * @param <E> the type of the data element stored in the node
 */
public class INode<E> {

	private BinarySearchTree.Node<E> node;
	
	/**
	 * Initializes this node so that it is an aggregation of
	 * the specified BinarySearchTree.Node.
	 * 
	 * @param node a BinarySearchTree.Node
	 */
	public INode(BinarySearchTree.Node<E> node) {
		this.node = node;
	}
	
	/**
	 * Get the left child node.
	 * 
	 * @return the left child node
	 */
	public INode<E> left() {
		if (this.node.left() == null) {
			return null;
		}
		return new INode<E>(this.node.left());
	}

	/**
	 * Get the right child node.
	 * 
	 * @return the right child node
	 */
	public INode<E> right() {
		if (this.node.right() == null) {
			return null;
		}
		return new INode<E>(this.node.right());
	}

	/**
	 * Get the data stored in the node.
	 * 
	 * @return the data stored in the node
	 */
	public E data() {
		return this.node.data();
	}
}
