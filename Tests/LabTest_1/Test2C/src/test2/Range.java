package test2;

/**
 * A class that represents a range of integer values. A range
 * has a minimum value and a maximum value.
 *
 */
public class Range {

	/**
	 * The minimum value of the range.
	 */
	private int min;
	
	/**
	 * The maximum value of the range.
	 */
	private int max;
	
	public Range() {
		this(-1, 1);
	}
	
	/**
	 * Initializes the range to have the given minimum and maximum values.
	 * 
	 * @param min
	 * @param max
	 * 
	 * @throws IllegalArgumentException
	 */
	public Range(int min, int max) {
		if (min >= max)
			throw new IllegalArgumentException("min greater than max");
		this.min = min;
		this.max = max;
	}
}
