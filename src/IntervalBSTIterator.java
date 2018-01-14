import java.util.*;

public class IntervalBSTIterator<K extends Interval> implements Iterator<K> {

	private Stack<IntervalBSTnode<K>> stack; //for keeping track of nodes
	private IntervalBSTnode node;
	
	public IntervalBSTIterator(IntervalBSTnode<K> root) {
		stack = new Stack<IntervalBSTnode<K>>();
		node = root;
	}
	
    public boolean hasNext() {
		return (node != null || !stack.isEmpty());
    }

    public K next() {
		while(node != null) {
			stack.push(node);
			node = node.getLeft();
		}
		node = stack.pop();
		IntervalBSTnode<K> temp = node;
		node = node.getRight();
		return temp.getKey();
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }
}