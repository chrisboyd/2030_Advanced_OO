package eecs2030.lab3;

/**
 * Chris Boyd 216869356
 * 
 * The {@code Triangle} class is immutable class that contains three variables
 * to store the length of each side of the triangle, and methods to get its area
 * and perimeter. The class implements {@link Comparable} interface to compare
 * two triangles. It also overrides {@code equals}, {@code hashCode}, and
 * {@code toString} from the {@link Object} class.
 * 
 * @author EECS2030 Summer 2019
 */
public final class Triangle implements Comparable<Triangle> {
	/**
	 * Stores the number of objects instantiated from the {@code Triangle} class
	 */
	private static int numTriangles = 0;

	/**
	 * The shortest side of the triangle
	 */
	private final double a;
	/**
	 * The side of medium length of the triangle
	 */
	private final double b;
	/**
	 * The longest side of the triangle
	 */
	private final double c;

	/**
	 * Initializes the new {@link Triangle} object using the provided sides in the
	 * arguments. The new triangle will have its shortest side stored in the
	 * variable {@code a}, the medium length side stored in the variable {@code b},
	 * and the longest side stored in the variable {@code c}.
	 * 
	 * <p>
	 * Before initializing the triangle object, this constructor verifies that the
	 * provided sides are positive and valid as triangle sides otherwise it throws
	 * {@link IllegalArgumentException}. Verifying that the three sides can form a
	 * valid triangle is to be delegated to {@link Triangle#isValid} method.
	 * 
	 * <p>
	 * This constructor also increments the static variable
	 * {@link Triangle#numTriangles} which tracks the number of Triangle objects
	 * created.
	 * 
	 * @param side1
	 *            the first side of this triangle
	 * @param side2
	 *            the second side of this triangle
	 * @param side3
	 *            the third side of this triangle
	 * 
	 * @throws IllegalArgumentException
	 *             if the sides are not positive or the provided sides cannot form a
	 *             valid triangle
	 */
	public Triangle(double side1, double side2, double side3) {
		if (side1 < 0 || side2 < 0 || side3 < 0 || !isValid(side1, side2, side3))
			throw new IllegalArgumentException();

		if (side1 <= side2 && side1 <= side3) {
			a = side1;
			b = Math.min(side2, side3);
			c = Math.max(side2, side3);
		} else if (side2 <= side1 && side2 <= side3) {
			a = side2;
			b = Math.min(side1, side3);
			c = Math.max(side1, side3);
		} else {
			a = side3;
			b = Math.min(side1, side2);
			c = Math.max(side1, side2);
		}
		numTriangles++;
	}

	/**
	 * A {@code Triangle} is considered valid if and only if the sum of two sides be
	 * greater than the third side. This condition has to be true for all three
	 * combinations of the sides.
	 * 
	 * <p>
	 * <strong> Example 1: valid triangle</strong><br>
	 * Assume you have a triangle that has the following sides {@code side1} =
	 * {@code 5}, {@code side2} = {@code 10}, and {@code side3} = {@code 7}
	 * then:<br>
	 * <code> 5 + 10 greater than 7 (true), 5 + 7 greater than 10 (true), and
	 * 10 + 7 greater than 5 (true)</code>
	 * </p>
	 * 
	 * <p>
	 * <strong> Example 2: invalid triangle</strong><br>
	 * Assume you have a triangle that has the following sides {@code side1} =
	 * {@code 1}, {@code side2} = {@code 2}, and {@code side3} = {@code 7} then:<br>
	 * <code> 1 + 2 greater than 7 (false). Then this is not a valid triangle </code>
	 * </p>
	 *
	 *
	 * @param side1
	 *            the first side of this triangle
	 * @param side2
	 *            the second side of this triangle
	 * @param side3
	 *            the third side of this triangle
	 * @return true if this is a valid triangle
	 */
	public static boolean isValid(double side1, double side2, double side3) {
		if (side1 + side2 > side3)
			if (side2 + side3 > side1)
				if (side3 + side1 > side2)
					return true;
		return false;
	}

	/**
	 * Method to check if a triangle is equilateral triangle by having equal length
	 * sides.
	 * 
	 * @return {@code true} if the triangle is equilateral otherwise {@code false}
	 */
	public boolean isEquilateral() {
		return a == b && b == c;
	}

	/**
	 * Accessor method that returns the smallest side of this triangle.
	 * 
	 * @return the side {@code a} of the triangle. This must be the smallest side.
	 */
	public double getA() {
		return a;
	}

	/**
	 * Accessor method that returns the medium side of this triangle.
	 * 
	 * @return the side {@code b} of the triangle. This must be the medium length
	 *         side.
	 */
	public double getB() {
		return b;
	}

	/**
	 * Accessor method that returns the longest side of this triangle.
	 * 
	 * @return the side {@code c} of the triangle. This must be the longest side.
	 */
	public double getC() {
		return c;
	}

	/**
	 * This method returns the perimeter of the triangle. The perimeter of a
	 * triangle is the sum of all its sides.
	 * 
	 * @return the perimeter of the triangle
	 */
	public double getPerimeter() {
		return a + b + c;
	}

	/**
	 * This method returns the area of the triangle. The area of a triangle given
	 * its sides {@code a}, {@code b}, and {@code c} is $$sqrt(s * (s - a) * (s - b)
	 * * (s - c))$$ where $s$ is half the perimeter (semiperimeter).
	 * 
	 * @return the area of the triangle
	 */
	public double getArea() {
		double s = getPerimeter() / 2.0;
		return Math.sqrt((s * (s - a) * (s - b) * (s - c)));
	}

	/**
	 * This method returns the number of objects instantiated from the
	 * {@code Triangle} class.
	 * 
	 * @return the number of {@code Triangle} objects.
	 */
	public static int getNumTriangles() {
		return numTriangles;
	}

	/**
	 * Compares area of this triangle object and the other triangle {@code t}.
	 *
	 * @param t
	 *            the other triangle
	 * @return the value {@code -1 } if the area of this triangle is less than the
	 *         argument's triangle area; {@code +1} if the area of this triangle is
	 *         greater than the argument's; and {@code 0} if this area of this
	 *         triangle is equal to the argument's within the specified tolerance
	 *         {@link ShapeUtil#TOL}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument is null
	 */
	@Override
	public int compareTo(Triangle t) {
		if (t == null)
			throw new IllegalArgumentException();

		double difference = this.getArea() - t.getArea();
		if (Math.abs(difference) < ShapeUtil.TOL)
			return 0;
		else if (difference < 0)
			return -1;
		else
			return 1;
	}

	/**
	 * Generates a hashCode of this triangle using its sides length. Use Eclipse to
	 * generate this method for you.
	 * 
	 * @return some hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(c);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Compares this triangle with the argument {@code obj} of Object type. This
	 * method overrides the base method defined in the root class Object.
	 * <p>
	 * Two triangles are equal if the difference between the sides of the first
	 * triangle and the sides of second triangle is within the allowed tolerance
	 * specified in {@link ShapeUtil#TOL}
	 * 
	 * 
	 * @return {@code true} if the triangle is equal to the argument triangle within
	 *         the tolerance specified in {@link ShapeUtil#TOL}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		else {
			if (Math.abs(this.a - ((Triangle) obj).getA()) < ShapeUtil.TOL)
				if (Math.abs(this.b - ((Triangle) obj).getB()) < ShapeUtil.TOL)
					if (Math.abs(this.c - ((Triangle) obj).getC()) < ShapeUtil.TOL)
						return true;
			return false;
		}
	}

	@Override
	public String toString() {
		return "Triangle [" + a + ", " + b + ", " + c + "]";
	}
}
