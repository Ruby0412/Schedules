
class IntervalBSTnode<K extends Interval> {
	private K key;
	private IntervalBSTnode<K> left;
	private IntervalBSTnode<K> right;
	private long maxEnd;
	
    public IntervalBSTnode(K keyValue) {
		key = keyValue;
    	left = null;
    	right = null;
    	maxEnd = key.getEnd();
    }
    
    public IntervalBSTnode(K keyValue, IntervalBSTnode<K> leftChild, IntervalBSTnode<K> rightChild, long maxEnd) {
		key = keyValue;
    	left = leftChild;
    	right = rightChild;
    	this.maxEnd = maxEnd;
    }

    public K getKey() { 
    	return key;
    }
    
    public IntervalBSTnode<K> getLeft() { 
		return left;
    }
  
    public IntervalBSTnode<K> getRight() { 
		return right;
    }
 
    public long getMaxEnd(){
    	IntervalBSTnode<K> parent = null, current = this;
    	while(current != null) {
    		parent = current;
    		current = current.getRight();
    	}
    	if (parent == null)
    		return key.getEnd();
		return parent.getEnd();
    }
 
    public void setKey(K newK) {
    	key = newK;
    }
    
    public void setLeft(IntervalBSTnode<K> newL) { 
		left = newL;
    }
    
    public void setRight(IntervalBSTnode<K> newR) { 
		right = newR;
    }
    
    public void setMaxEnd(long newEnd) {
    	this.maxEnd = newEnd;
    }
    
    public long getStart(){ 
		return key.getStart();
	}

    public long getEnd(){
		return key.getEnd();
	}

    public K getData(){
		return key;
	}
}