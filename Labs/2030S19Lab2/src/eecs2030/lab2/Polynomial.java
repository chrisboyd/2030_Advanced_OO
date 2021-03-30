/**
 * 
 */
package eecs2030.lab2;

import java.util.Arrays;

/**
 * 
 * 
 * The {@code Polynomial} class represents a polynomial of a single
 * indeterminate with double coefficients. A polynomial is an an expression
 * consisting of variables with non-negative integer exponents and real
 * coefficients. The set of operations supported by the {@code Polynomial} class
 * includes: addition, subtraction, multiplication, function composition,
 * polynomial derivative, and evaluation.
 * 
 * <p>
 * Examples of polynomials with a single indeterminate, x, are:
 * 
 * <pre>
 * p(x) = x^2 - 5.0x + 1.0
 * p(x) = x^6 + 4.1x^4 + 0.5x - 7.2
 * p(x) = 1.0
 * </pre>
 * 
 * @author EECS2030 Summer 2019
 *
 */
public class Polynomial implements Comparable<Polynomial> {

	/**
	 * Array of the polynomial coefficients.
	 * 
	 * <pre>
	 * p(x) = cf[0] * x^0 + ... + cf[n-1] * x^(n-1)
	 * </pre>
	 */
	private double[] cf;

	/**
	 * The degree of the polynomial. That is the largest exponent with non zero
	 * coefficient. If all the coefficients are zero, the degree is zero. To set the
	 * value of {@code degree}, implement and call {@code findDegree()} method every
	 * time the polynomial is created or an operation is performed
	 */
	private int degree;

	/**
	 * A helper function for testing. Do not remove or change.
	 */
	protected int test_getDegree() {
		return degree;
	}

	/**
	 * A helper function for testing. Do not remove or change.
	 */
	protected void test_setDegree(int d) {
		degree = d;
	}

	/**
	 * A helper function for testing. Do not remove or change.
	 */
	protected double[] test_getCf() {
		return cf;
	}

	/**
	 * Creates a new polynomial {@code a * x^i}
	 * 
	 * @param a
	 *            the leading coefficient
	 * @param i
	 *            the exponent
	 * @throws IllegalArgumentException
	 *             if {@code i} is negative
	 */
	public Polynomial(double a, int i) {
		if (i < 0)
			throw new IllegalArgumentException();

		cf = new double[i + 1];
		cf[i] = a;
		findDegree();
	}

	/**
	 * Creates a new polynomial from array of coefficients. The new polynomial has
	 * the form
	 * 
	 * <pre>
	 * cf[0] * x^0 + ... + cf[n-1] * x^(n-1)
	 * </pre>
	 * 
	 * where {@code n} is the length of the coefficients array.
	 * 
	 * @param cf
	 *            the coefficients array
	 * @throws IllegalArgumentException
	 *             if {@code cf} is empty
	 */
	public Polynomial(double[] cf) {
		if (cf.length == 0)
			throw new IllegalArgumentException();

		this.cf = cf;
		findDegree();
	}

	/**
	 * Creates a polynomial with zero degree and one zero coefficient.
	 * 
	 * <pre>
	 * p(x) = 0
	 * </pre>
	 */
	public Polynomial() {
		this(0, 0);

	}

	/**
	 * Finds the polynomial {@code degree}, which is the largest exponent with non
	 * zero coefficient. If all the coefficients are zero, the degree is zero.
	 */
	private void findDegree() {
		int tempdeg = 0;
		for (int i = 0; i < cf.length; i++) {
			if (cf[i] != 0)
				tempdeg = i;
		}
		degree = tempdeg;
	}

	/**
	 * Returns the degree of the polynomial. A polynomial with zero coefficients has
	 * a zero degree.
	 * 
	 * @return the degree of the polynomial
	 */
	public int getDegree() {
		return this.degree;
	}

	/**
	 * Returns the sum of this polynomial {@code p}} and the other polynomial
	 * {@code q} the argument.
	 *
	 * @param q
	 *            the other polynomial
	 * @return the polynomial whose value is {@code (p(x) + q(x))}
	 */
	public Polynomial plus(Polynomial q) {
		Polynomial sum = new Polynomial(0, Math.max(this.getDegree(), q.getDegree()));

		for (int i = 0; i <= this.getDegree(); i++) {
			sum.cf[i] += this.cf[i];
			sum.findDegree();
		}
		for (int i = 0; i <= q.getDegree(); i++) {
			sum.cf[i] += q.cf[i];
			sum.findDegree();
		}

		return sum;
	}

	/**
	 * Returns the result of subtracting the specified polynomial {@code q} from
	 * this polynomial {@code p}.
	 *
	 * @param q
	 *            the other polynomial
	 * @return the polynomial whose value is {@code (p(x) - q(x))}
	 */
	public Polynomial minus(Polynomial q) {
		Polynomial difference = new Polynomial(0, Math.max(this.getDegree(), q.getDegree()));

		for (int i = 0; i <= this.getDegree(); i++) {
			difference.cf[i] += this.cf[i];
			difference.findDegree();
		}
		for (int i = 0; i <= q.getDegree(); i++) {
			difference.cf[i] -= q.cf[i];
			difference.findDegree();
		}

		return difference;
	}

	/**
	 * Returns the product of this polynomial {@code p} and the specified polynomial
	 * {@code q}.
	 *
	 * @param q
	 *            the other polynomial
	 * @return the polynomial whose value is {@code (p(x) * q(x))}
	 */
	public Polynomial times(Polynomial q) {
		Polynomial mult = new Polynomial(0, this.getDegree() + q.getDegree());

		for (int i = 0; i <= this.getDegree(); i++)
			for (int j = 0; j <= q.getDegree(); j++) {
				mult.cf[i + j] += this.cf[i] * q.cf[j];
				mult.findDegree();
			}

		return mult;
	}

	/**
	 * Returns the composition of this polynomial {@code p} and the specified
	 * polynomial {@code q}.
	 *
	 * @param q
	 *            the other polynomial
	 * @return the polynomial whose value is {@code (p(q(x)))}
	 */
	public Polynomial compose(Polynomial q) {
		Polynomial comp = new Polynomial(0, this.getDegree() * q.getDegree());

		for (int i = this.getDegree(); i >= 0; i--) {
			Polynomial term = new Polynomial(this.cf[i], 0);
			comp = term.plus(q.times(comp));
		}

		return comp;
	}

	/**
	 * Returns the result of deriving this polynomial {@code p}.
	 *
	 * @return the polynomial whose value is {@code p'(x)}
	 */
	public Polynomial derive() {
		Polynomial derivative;

		if (this.degree == 0)
			return new Polynomial(0, 0);
		else {
			derivative = new Polynomial(0, this.getDegree() - 1);
			for (int i = 1; i <= this.getDegree(); i++) {
				derivative.cf[i - 1] = this.cf[i] * (i);
				derivative.findDegree();
			}
			return derivative;
		}
	}

	/**
	 * Returns the result of evaluating this polynomial {@code p} at the value x.
	 *
	 * @param x
	 *            the value at which to evaluate the polynomial
	 * @return the value of evaluating {@code (p(x))}
	 */
	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < cf.length; i++) {
			result += cf[i] * Math.pow(x, i);
		}

		return result;
	}

	/**
	 * Returns a hash code for this polynomial. The hash code value is equal to the
	 * degree of the polynomial multiplied with 31 plus the hashcode of the
	 * coefficients array.
	 * 
	 * @return 31 * degree + Arrays.hashCode(cf)
	 */
	@Override
	public int hashCode() {
		return 31 * degree + Arrays.hashCode(cf);
	}

	/**
	 * Compares this polynomial to the other object {@code obj}. The result is true
	 * if and only if the argument is a polynomial object having the same degree and
	 * coefficients as this polynomial.
	 * 
	 * @param obj
	 *            an object to compare
	 * @return {@code true} if this polynomial equals to the specified object; and
	 *         {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && this.getClass() == obj.getClass())
			return this.compareTo((Polynomial) obj) == 0;

		return false;
	}

	/**
	 * Compares this polynomial {@code p} and the other polynomial {@code q} by
	 * their degree. If the degrees are equal, the polynomial coefficients are
	 * compared, and so on.
	 *
	 * @param q
	 *            the other polynomial
	 * @return the value {@code -1 } if this polynomial is less than the argument
	 *         polynomial; {@code +1} if this polynomial is greater than the
	 *         argument polynomial; and {@code 0} if this polynomial is equal to the
	 *         argument polynomial.
	 */
	@Override
	public int compareTo(Polynomial q) {
		int difference = 0;
		if (this.getDegree() == q.getDegree()) {
			int i = this.getDegree();

			while (difference == 0 && i >= 0) {
				if (this.cf[i] > q.cf[i])
					difference = 1;
				else if (this.cf[i] < q.cf[i])
					difference = -1;
				i--;
			}
		} else if (this.getDegree() > q.getDegree())
			difference = 1;
		else
			difference = -1;

		return difference;
	}

	/**
	 * Return a string representation of this polynomial in the standard form. The
	 * standard form of a polynomial is to put the terms with the highest degree
	 * first. The following are examples of the returned string for different
	 * polynomials.
	 * 
	 * <pre>
	new Polynomial();                              //0.0
	new Polynomial(1,0)                            //1.0
	new Polynomial(1,1);                           //1.0x
	new Polynomial(new double[] {1,1});            //1.0x + 1.0
	new Polynomial(new double[] {0,0,2});          //2.0x^2
	new Polynomial(new double[] {0,1,2});          //2.0x^2 + 1.0x
	new Polynomial(new double[] {1,0,2});          //2.0x^2 + 1.0
	new Polynomial(new double[] {0.0001213,1,2});  //2.0x^2 + 1.0x + 1.213E-4
	 * </pre>
	 * 
	 * @return a string representation of this polynomial
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = degree; i >= 0; i--) {
			if (s != "")
				if (cf[i] > 0)
					s += " + ";
				else if (cf[i] < 0)
					s += " - ";

			if (cf[i] != 0) {
				s += Math.abs(cf[i]);
				if (i > 1)
					s += "x^" + i;
				else if (i == 1)
					s += "x";
			}
		}
		if (s == "")
			s = "0.0";
		return s;
	}
}
