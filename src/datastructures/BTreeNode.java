package datastructures;

public class BTreeNode<T extends Comparable<T>> {

	public T value;

	public BTreeNode<T> left;

	public BTreeNode<T> right;

	public int height = 0;

	public BTreeNode(T value) {
		super();
		this.value = value;
	}

}
