
public interface Interval extends Comparable<Interval>{
	/**
     * Returns the start of the interval.
     * @return the start
     */
	long getStart();
	
	/**
     * Returns the end of the interval.
     * @return the end
     */
	long getEnd();
	
	/**
     * Returns whether there is an overlap between the two intervals.
     * @return if there is an overlap between the intervals.
     */
	boolean overlap(Interval otherInterval);
}
