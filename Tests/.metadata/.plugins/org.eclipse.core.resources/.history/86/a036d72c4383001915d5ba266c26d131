package test2;

/**
 * A class that keeps count of an integer number of things.
 * A Counter has a name that is represented as a string.
 * A Counter has a value (the count) that is always greater
 * than or equal to zero.
 *
 */
public class Counter {

	/**
	 * The name of this counter.
	 */
	private String name;
	
	/**
	 * The value of this counter.
	 */
	private int value;
	
	public Counter(String name) {
		this(name, 0);
	}
	
	/**
	 * Initialize this counter to have the given name and value.
	 * 
	 * @param name the non-null name of this counter
	 * @param value the value of this counter
	 * @throws IllegalArgumentException if value is less than zero
	 */
	public Counter(String name, int value) {
		if (value < 0)
			throw new IllegalArgumentException("Value less than zero");
		this.name = name;
		this.value = value;
	}
	
	
	
}
