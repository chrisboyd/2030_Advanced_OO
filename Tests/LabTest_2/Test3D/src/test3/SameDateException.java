package test3;

public class SameDateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9072557730868221397L;

	/**
	 * Initializes a <code>DuplicateContactException</code> with no 
	 * detail message.
	 */
	public SameDateException() {
		super();
	}
	
	/**
	 * Initializes a <code>DuplicateContactException</code> with the 
	 * specified detail message.
	 * 
	 * @param message the detail message
	 */
	public SameDateException(String message) {
		super(message);
	}
}
