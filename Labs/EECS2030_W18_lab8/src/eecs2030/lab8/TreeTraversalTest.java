package eecs2030.lab8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeTraversalTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Test
	public void test01a_inorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		List<String> exp = new ArrayList<>();
		assertEquals("failed for an empty tree", 
				exp, TreeTraversal.inorder(t));
	}
	
	@Test
	public void test01b_inorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		assertEquals("failed for tree with one element", 
				exp, TreeTraversal.inorder(t));
	}
	
	@Test
	public void test01c_inorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		List<String> exp = new ArrayList<>();
		exp.add("goodbye");
		exp.add("hello");
		assertEquals("failed for tree with two elements", 
				exp, TreeTraversal.inorder(t));
	}
	
	@Test
	public void test01d_inorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		t.add("salut");
		List<String> exp = new ArrayList<>();
		exp.add("goodbye");
		exp.add("hello");
		exp.add("salut");
		assertEquals("failed for tree with three elements", 
				exp, TreeTraversal.inorder(t));
	}
	
	@Test
	public void test01e_inorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("50");
		t.add("27");
		t.add("73");
		t.add("08");
		t.add("44");
		t.add("83");
		t.add("73");
		t.add("93");
		List<String> exp = new ArrayList<>();
		exp.add("08");
		exp.add("27");
		exp.add("44");
		exp.add("50");
		exp.add("73");
		exp.add("73");
		exp.add("83");
		exp.add("93");
		assertEquals("failed for tree in lab document", 
				exp, TreeTraversal.inorder(t));
	}
	
	

	@Test
	public void test02a_preorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		List<String> exp = new ArrayList<>();
		assertEquals("failed for an empty tree",
				exp, TreeTraversal.preorder(t));
	}
	
	@Test
	public void test02b_preorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		assertEquals("failed for tree with one element", 
				exp, TreeTraversal.preorder(t));
	}
	
	@Test
	public void test02c_preorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		exp.add("goodbye");
		assertEquals("failed for tree with two elements", 
				exp, TreeTraversal.preorder(t));
	}
	
	@Test
	public void test02d_preorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		t.add("salut");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		exp.add("goodbye");
		exp.add("salut");
		assertEquals("failed for tree with three elements", 
				exp, TreeTraversal.preorder(t));
	}
	
	@Test
	public void test02e_preorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("50");
		t.add("27");
		t.add("73");
		t.add("08");
		t.add("44");
		t.add("83");
		t.add("73");
		t.add("93");
		List<String> exp = new ArrayList<>();
		exp.add("50");
		exp.add("27");
		exp.add("08");
		exp.add("44");
		exp.add("73");
		exp.add("83");
		exp.add("73");
		exp.add("93");
		assertEquals("failed for tree in lab document", 
				exp, TreeTraversal.preorder(t));
	}
	
	@Test
	public void test03a_postorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		List<String> exp = new ArrayList<>();
		assertEquals("failed for an empty tree",
				exp, TreeTraversal.postorder(t));
	}
	
	@Test
	public void test03b_postorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		assertEquals("failed for tree with one element", 
				exp, TreeTraversal.postorder(t));
	}
	
	@Test
	public void test03d_postorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		t.add("salut");
		List<String> exp = new ArrayList<>();
		exp.add("goodbye");
		exp.add("salut");
		exp.add("hello");
		assertEquals("failed for tree with three elements", 
				exp, TreeTraversal.postorder(t));
	}
	
	@Test
	public void test03e_postorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("50");
		t.add("27");
		t.add("73");
		t.add("08");
		t.add("44");
		t.add("83");
		t.add("73");
		t.add("93");
		List<String> exp = new ArrayList<>();
		exp.add("08");
		exp.add("44");
		exp.add("27");
		exp.add("73");
		exp.add("93");
		exp.add("83");
		exp.add("73");
		exp.add("50");
		assertEquals("failed for tree in lab document", 
				exp, TreeTraversal.postorder(t));
	}
	
	@Test
	public void test03c_postorder() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		List<String> exp = new ArrayList<>();
		exp.add("goodbye");
		exp.add("hello");
		assertEquals("failed for tree with two elements", 
				exp, TreeTraversal.postorder(t));
	}
	
	
	@Test
	public void test04a_breadthFirst() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		List<String> exp = new ArrayList<>();
		assertEquals("failed for an empty tree",
				exp, TreeTraversal.breadthFirst(t));
	}
	
	@Test
	public void test04b_breadthFirst() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		assertEquals("failed for tree with one element", 
				exp, TreeTraversal.breadthFirst(t));
	}
	
	@Test
	public void test04c_breadthFirst() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		exp.add("goodbye");
		assertEquals("failed for tree with two elements", 
				exp, TreeTraversal.breadthFirst(t));
	}
	
	@Test
	public void test04d_breadthFirst() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("hello");
		t.add("goodbye");
		t.add("salut");
		List<String> exp = new ArrayList<>();
		exp.add("hello");
		exp.add("goodbye");
		exp.add("salut");
		assertEquals("failed for tree with three elements", 
				exp, TreeTraversal.breadthFirst(t));
	}
	
	@Test
	public void test04e_breadthFirst() {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("50");
		t.add("27");
		t.add("73");
		t.add("08");
		t.add("44");
		t.add("83");
		t.add("73");
		t.add("93");
		List<String> exp = new ArrayList<>();
		exp.add("50");
		exp.add("27");
		exp.add("73");
		exp.add("08");
		exp.add("44");
		exp.add("83");
		exp.add("73");
		exp.add("93");
		assertEquals("failed for tree in lab document", 
				exp, TreeTraversal.breadthFirst(t));
	}
}
