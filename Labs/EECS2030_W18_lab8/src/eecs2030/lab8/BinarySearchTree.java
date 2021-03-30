package eecs2030.lab8;

/**
 * A binary search tree class.
 * 
 * <p>
 * A binary search tree is a binary tree where values are stored in the tree
 * according to three rules:
 * 
 * <ol>
 * <li>values in the left subtree are less than values in the root node
 * <li>values in the right subtree are greater than or equal to values in the
 * root node
 * <li>rules 1 and 2 apply recursively to every subtree
 * </ol>
 * 
 * 
 * @param <E> the type of elements in this tree
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

	/**
	 * A class that represents nodes in the binary search tree. Each node is an
	 * aggregation of a data element, and has a left and right child node. This
	 * class is a top-level public class for testing purposes only; normally, Node
	 * would be a private inner class.
	 * 
	 * @param <E> the type of the data element stored in the node
	 */
	public static class Node<E> {
		private E data;
		private Node<E> left;
		private Node<E> right;

		/**
		 * Create a node with the given data element. The left and right child nodes
		 * are set to null.
		 * 
		 * @param data
		 *            the element to store
		 */
		public Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		/**
		 * Get the left child node.
		 * 
		 * @return the left child node
		 */
		public Node<E> left() {
			return this.left;
		}

		/**
		 * Get the right child node.
		 * 
		 * @return the right child node
		 */
		public Node<E> right() {
			return this.right;
		}

		/**
		 * Get the data stored in the node.
		 * 
		 * @return the data stored in the node
		 */
		public E data() {
			return this.data;
		}

		/**
		 * Set the left child of this node to the specified node.
		 * 
		 * @param left
		 *            the left child of this node
		 */
		public void setLeft(Node<E> left) {
			this.left = left;
		}

		/**
		 * Set the right child of this node to the specified node.
		 * 
		 * @param right
		 *            the right child of this node
		 */
		public void setRight(Node<E> right) {
			this.right = right;
		}

		/**
		 * Set the data of this node to the specified element.
		 * 
		 * @param element
		 *            the data for this node
		 */
		public void setData(E element) {
			this.data = element;
		}
	}
	
	
	private Node<E> root;

	/**
	 * Create an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.root = null;
	}

	/**
	 * Add an element to the tree. The element is inserted into the tree in a
	 * position that preserves the definition of a binary search tree.
	 * 
	 * @param element
	 *            the element to add to the tree
	 */
	public void add(E element) {
		if (this.root == null) {
			this.root = new Node<E>(element);
		} else {
			BinarySearchTree.add(element, this.root);
		}
	}

	/**
	 * Add an element to the subtree rooted at <code>root</code>. The
	 * element is inserted into the tree in a position that preserves the
	 * definition of a binary search tree.
	 * 
	 * @param element
	 *            the element to add to the subtree
	 * @param root
	 *            the root of the subtree
	 */
	private static <E extends Comparable<? super E>> void add(E element, Node<E> root) {
		if (element.compareTo(root.data()) < 0) {
			if (root.left() == null) {
				root.setLeft(new Node<E>(element));
			} else {
				BinarySearchTree.add(element, root.left());
			}
		} else {
			if (root.right() == null) {
				root.setRight(new Node<E>(element));
			} else {
				BinarySearchTree.add(element, root.right());
			}
		}
	}

	/**
	 * Returns <code>true</code> if the tree contains the given element,
	 * <code>false</code> otherwise.
	 * 
	 * @param element
	 *            the element to search for
	 * @return <code>true</code> if the tree contains the given element,
	 *         <code>false</code> otherwise
	 */
	public boolean contains(E element) {
		return contains(element, this.root);
	}

	/**
	 * Returns <code>true</code> if the subtree rooted at
	 * <code>root</code> contains the given element, <code>false</code>
	 * otherwise.
	 * 
	 * @param element
	 *            the element to search for
	 * @param root
	 *            the root of the subtree
	 * @return <code>true</code> if the subtree rooted at
	 *         <code>root</code> contains the given element,
	 *         <code>false</code> otherwise
	 */
	private static <E extends Comparable<? super E>> boolean contains(E element, Node<E> root) {
		if (root == null) {
			return false;
		}
		if (element.equals(root.data())) {
			return true;
		}
		boolean result;
		if (element.compareTo(root.data()) < 0) {
			result = contains(element, root.left());
		} else {
			result = contains(element, root.right());
		}
		return result;
	}

	/**
	 * Return a string representation of the tree.
	 * 
	 * <p>
	 * The string is made up of the elements stored in the tree separated by
	 * commas; the entire list of elements is enclosed in braces. The elements
	 * are in ascending sorted order.
	 * 
	 * @see java.lang.Object#toString()
	 *
	 * @return a string representation of the tree
	 */
	@Override
	public String toString() {
		return "{" + toString(this.root) + "}";
	}

	/**
	 * Return a string representation of the subtree rooted at
	 * <code>root</code>.
	 * 
	 * <p>
	 * The string is made up of the elements stored in the tree separated by
	 * commas where the elements are in ascending sorted order.
	 * 
	 * <p>
	 * The string is generated using inorder processing of the subtree:
	 * 
	 * <ol>
	 * <li>the string corresponding to <code>root.left()</code> is computed
	 * <li>the string corresponding to <code>root.data()</code> is computed
	 * <li>the string corresponding to <code>root.right()</code> is
	 * computed
	 * </ol>
	 * 
	 * <p>
	 * The returned string is the concatenation of the three strings computed by
	 * the inorder processing of the subtree.
	 * 
	 * @param root
	 *            the root of the subtree
	 * @return the string representation of the subtree
	 */
	private static <E extends Comparable<? super E>> String toString(Node<E> root) {
		if (root == null) {
			return "";
		}
		String left = toString(root.left());
		if (!left.isEmpty()) {
			left = left + ", ";
		}
		String right = toString(root.right());
		if (!right.isEmpty()) {
			right = ", " + right;
		}
		return left + root.data() + right;
	}

	/**
	 * Get the root node of the tree. For testing purposes only.
	 * 
	 * @return the root node of the tree.
	 */
	public INode<E> root() {
		if (this.root == null) {
			return null;
		}
		return new INode<E>(this.root);
	}

	
}