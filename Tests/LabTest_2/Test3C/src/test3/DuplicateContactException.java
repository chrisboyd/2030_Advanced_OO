package test3;

public class DuplicateContactException extends RuntimeException {

	private static final long serialVersionUID = 1468948233264095188L;

	/**
	 * Initializes a <code>DuplicateContactException</code> with no 
	 * detail message.
	 */
	public DuplicateContactException() {
		super();
	}
	
	/**
	 * Initializes a <code>DuplicateContactException</code> with the 
	 * specified detail message.
	 * 
	 * @param message the detail message
	 */
	public DuplicateContactException(String message) {
		super(message);
	}
}
