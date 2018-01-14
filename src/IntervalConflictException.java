

public class IntervalConflictException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntervalConflictException(){
		System.out.println("Two intervals overlap");
	}
}
