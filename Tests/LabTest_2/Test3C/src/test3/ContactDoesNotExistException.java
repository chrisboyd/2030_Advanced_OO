package test3;

public class ContactDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = -6043563834154496407L;

	/**
	 * Initializes a <code>DuplicateContactException</code> with no 
	 * detail message.
	 */
	public ContactDoesNotExistException() {
		super();
	}
	
	/**
	 * Initializes a <code>DuplicateContactException</code> with the 
	 * specified detail message.
	 * 
	 * @param message the detail message
	 */
	public ContactDoesNotExistException(String message) {
		super(message);
	}
}
