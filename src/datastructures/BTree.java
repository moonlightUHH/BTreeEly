package datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;

public class BTree<T extends Comparable<T>> {

	private BTreeNode<T> root;

	public void insert(T value){
		if(root==null){
			this.root = new BTreeNode<>(value);
			return;
		}
		insert(root,value);
	}
	
	private void insert(BTreeNode<T> node, T value) {
		if (value.compareTo(node.value) < 0) {
			// links
			if (node.left == null) {
				// hier einfuegen
				node.left = new BTreeNode<>(value);
			} else {
				// an Kindknoten delegieren
				insert(node.left, value);
			}

		} else if (value.compareTo(node.value) > 0) {
			// rechts
			if (node.right == null) {
				node.right = new BTreeNode<>(value);
			} else {
				insert(node.right, value);
			}

		}
	}

	@Override
	public String toString() {
		// slightly ugly bread-first search -- purpose: to show the internal state of the tree
		// null values are printed as "X"
		if (root == null) return "No root value";
		StringBuffer strB = new StringBuffer();
		List<BTreeNode> printQueue = new LinkedList<>();
		printQueue.add(root);
		int nodesInPrintQueue = 1;

		while (nodesInPrintQueue > 0) {
			nodesInPrintQueue = 0;
			List nextLevelPrintQueue = new LinkedList();

			for (BTreeNode node : printQueue) {
				if (node == null) {
					strB.append(" X;");
					continue;
				}
				strB.append(" " + node.value + ";");
				nextLevelPrintQueue.add(node.left);
				nextLevelPrintQueue.add(node.right);
				nodesInPrintQueue += 2;
			}
			strB.append(System.lineSeparator());
			printQueue = nextLevelPrintQueue;
		}
		return strB.toString();
	}

	public String inOrder() {
		return inOrder(root);
	}
	private String inOrder(BTreeNode current) {
		String res = "";
		if (current.left != null)
			res += inOrder(current.left) + ",";
		res += current.value;
		if (current.right != null)
			res += "," + inOrder(current.right);
		return res;
	}

}
