package eecs2030.lab8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A utility class that provides methods for traversing a binary search tree.
 *
 */
public class TreeTraversal {
	private TreeTraversal() {
		// empty by design
	}

	/**
	 * Returns the list of strings formed by traversing the specified tree using an
	 * inorder traversal.
	 * 
	 * @param tree a binary search tree
	 * @return the list of strings formed by traversing the specified tree using an
	 *         inorder traversal
	 */
	public static List<String> inorder(BinarySearchTree<String> tree) {
		return TreeTraversal.inorder(tree.root());
		// YOU SHOULD IMPLEMENT inorder(INode) below
	}

	/**
	 * Returns the list of strings formed by traversing the specified tree using a
	 * preorder traversal.
	 * 
	 * @param tree a binary search tree
	 * @return the list of strings formed by traversing the specified tree using a
	 *         preorder traversal
	 */
	public static List<String> preorder(BinarySearchTree<String> tree) {
		return TreeTraversal.preorder(tree.root());
		// YOU SHOULD IMPLEMENT preorder(INode) below

	}

	/**
	 * Returns the list of strings formed by traversing the specified tree using a
	 * postorder traversal.
	 * 
	 * @param tree a binary search tree
	 * @return the list of strings formed by traversing the specified tree using a
	 *         postorder traversal
	 */
	public static List<String> postorder(BinarySearchTree<String> tree) {
		return TreeTraversal.postorder(tree.root());
		// YOU SHOULD IMPLEMENT postorder(INode) below
	}

	/**
	 * Returns the list of strings formed by traversing the specified tree using a
	 * breadth first traversal. The traversal visits nodes of the tree starting at
	 * the root moving left to right for each level of the tree.
	 * 
	 * @param tree a binary search tree
	 * @return the list of strings formed by traversing the specified tree using a
	 *         breadth first traversal
	 */
	public static List<String> breadthFirst(BinarySearchTree<String> tree) {
		List<String> result = new ArrayList<>();
		INode<String> root = tree.root();
		if (root == null) {
			return result;
		}

		// in Java, a LinkedList is a Queue
		// to enqueue a node, use the Queue method add
		// to dequeue a node, use the Queue method remove
		Queue<INode<String>> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			root = q.remove();
			if (root != null) {
				result.add(root.data());
				if (root.left() != null)
					q.add(root.left());
				if (root.right() != null)
					q.add(root.right());
			}

		}
		return result;
	}

	/**
	 * Returns the list of strings formed by traversing a tree having the specified
	 * root using an inorder traversal.
	 * 
	 * @param root the root of the tree
	 * @return the list of strings formed by traversing a tree having the specified
	 *         root using an inorder traversal
	 */
	private static List<String> inorder(INode<String> root) {
		List<String> result = new ArrayList<>();
		if (root != null) {
			result.addAll(inorder(root.left()));
			result.add(root.data());
			result.addAll(inorder(root.right()));
		}

		return result;
	}

	/**
	 * Returns the list of strings formed by traversing a tree having the specified
	 * root using a preorder traversal.
	 * 
	 * @param root the root of the tree
	 * @return the list of strings formed by traversing a tree having the specified
	 *         root using a preorder traversal
	 */
	private static List<String> preorder(INode<String> root) {
		List<String> result = new ArrayList<>();

		if (root != null) {
			result.add(root.data());
			result.addAll(preorder(root.left()));
			result.addAll(preorder(root.right()));
		}

		return result;
	}

	/**
	 * Returns the list of strings formed by traversing a tree having the specified
	 * root using a postorder traversal.
	 * 
	 * @param root the root of the tree
	 * @return the list of strings formed by traversing a tree having the specified
	 *         root using a postorder traversal
	 */
	private static List<String> postorder(INode<String> root) {
		List<String> result = new ArrayList<>();

		if (root != null) {
			result.addAll(postorder(root.left()));
			result.addAll(postorder(root.right()));
			result.add(root.data());
		}

		return result;
	}

}
