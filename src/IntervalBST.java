import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;

public class IntervalBST<K extends Interval> implements SortedListADT<K> {
	private IntervalBSTnode<K> head;
	private int size = 0;

	public IntervalBST() {
		head = null;
	}

	@SuppressWarnings("unchecked")
	public void insert(K key){
		if (head == null) {
			head = new IntervalBSTnode<K>(key);
			++size;
		} else {
			IntervalBSTnode<K> data = head, parent = null;
			while(null != data) {
				if (data.getStart() == key.getStart()) {
					return;
				} else {
					parent = data;
					data = data.getStart() < key.getStart() ? data.getRight():data.getLeft();
				}

			}
			if (parent.getStart() < key.getStart()) {
				parent.setRight(new IntervalBSTnode<K>(key));
			} else {
				parent.setLeft(new IntervalBSTnode<K>(key));
			}
			++size;
		}
	}

	public boolean delete(K key) {
		if (head == null) {
			return false;
		} else {
			IntervalBSTnode<K> data = head, parent = null, temp = null, child = null;
			while(null != data) {
				if (data.getStart() == key.getStart()) {
					break;
				} else {
					parent = data;
					data = data.getStart() < key.getStart() ? data.getRight():data.getLeft();
				}
			}
			if (null == data) {
				return false;
			}
			temp = data;

			int flag = 0;
			if (null != data.getLeft() && null != data.getRight()) {
				parent = data;
				data = data.getRight();
				while(null != data.getLeft()) {
					parent = data;
					data = data.getLeft();
				}
				child = data.getRight();
			} else if (null == data.getLeft()) {
				child = data.getRight();
			} else {
				child = data.getLeft();
			}
			if (null == parent) {
				head = null;
			} else {
				if (parent.getLeft().getStart() == data.getStart()) {
					parent.setLeft(child);
				} else {
					parent.setRight(child);
				}
				if (data.getStart() != temp.getStart()) {
					temp = data;
					K dsp= temp.getKey();
					temp.setKey(data.getKey());
					data.setKey(dsp);
				}
			}
			--size;
			return true;
		}
	}

	
	public K lookup(K key) {
		IntervalBSTnode<K> data = head;
		while (null != data) {
			if (data.getStart() == key.getStart())
				break;
			else
				data = data.getStart() < key.getStart() ? data.getRight() : data.getLeft();
		}
		if (data == null)
			return null;
		else
			return data.getKey();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return 0 == size;
	}

	public Iterator<K> iterator() {
		return new IntervalBSTIterator<K>(head);
	}
}