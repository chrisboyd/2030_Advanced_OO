package test2;

/**
 * A class that represents a fraction. A Fraction has
 * an integer numerator that may be any integer value.
 * A Fraction has an integer denominator that is
 * never equal to zero.
 *
 */
public class Fraction {

	/**
	 * The numerator of the fraction.
	 */
	private int numer;
	
	/**
	 * The denominator of the fraction.
	 */
	private int denom;
	
	public Fraction() {
		this(1, 1);
	}
	
	/**
	 * Initializes the fraction so that its numerator is
	 * equal to the given numerator and its denominator is
	 * equal to the given denominator.
	 * 
	 * @param numer the numerator of the fraction
	 * @param denom the denominator of the fraction
	 * @throws ArithmeticException if the denominator is equal to zero.
	 */
	public Fraction(int numer, int denom) {
		if (denom == 0)
			throw new ArithmeticException("denominator cannot be 0");
		
		this.numer = numer;
		this.denom = denom;
		
	}
	
	
	
}
