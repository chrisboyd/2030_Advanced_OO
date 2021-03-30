package eecs2030.lab3;

/**
 * 
 * The {@code Rectangle} class is immutable class that contains two variables to
 * store the width and heights of the rectangle, and methods to get its area and
 * perimeter. The class implements {@link Comparable} interface to compare two
 * rectangles. It also overrides {@code equals}, {@code hashCode}, and
 * {@code toString} from the {@link Object} class.
 * 
 * @author EECS2030 Summer 2019
 */
public class Rectangle implements Comparable<Rectangle> {
	/**
	 * Stores the number of objects instantiated from the {@code Rectangle} class
	 */
	private static int numRectangles = 0;

	/**
	 * The width of the rectangle
	 */
	private final double width;

	/**
	 * The height of the rectangle
	 */
	private final double height;

	/**
	 * Initializes the {@link Rectangle} object using the provided width and height
	 * arguments.
	 * 
	 * <p>
	 * This constructor accepts two parameters, the width and height of the
	 * rectangle. Before initializing the state variables, this constructor verifies
	 * that the provided width and height are positive otherwise it throws
	 * {@link IllegalArgumentException}.
	 * 
	 * <p>
	 * This constructor also increments the static variable
	 * {@link Rectangle#numRectangles} which tracks the number of Rectangle objects
	 * created.
	 * 
	 * @param width
	 *            the width of this rectangle
	 * @param height
	 *            the height of this rectangle
	 * 
	 * @throws IllegalArgumentException
	 *             if the provided width or the height are not positive
	 */
	public Rectangle(double width, double height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Width and height of a rectangle shall be positive");
		}
		this.width = width;
		this.height = height;
		numRectangles++;
	}

	/**
	 * Method to check if a rectangle is square by having equal width and height.
	 * 
	 * @return {@code true} if the rectangle is square otherwise {@code false}
	 */
	public boolean isSquare() {
		return (width == height);
	}

	/**
	 * Accessor method that returns the width of this rectangle
	 * 
	 * @return the width of this rectangle
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Accessor method that returns the height of this rectangle
	 * 
	 * @return the height of this rectangle
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * This method returns the number of objects instantiated from the
	 * {@code Rectangle} class.
	 * 
	 * @return the number of {@code Rectangle} objects.
	 */
	public static int getNumRectangles() {
		return numRectangles;
	}

	/**
	 * Returns the perimeter of the rectangle which is equal to the sum of its four
	 * sides.
	 * 
	 * @return the perimeter of the rectangle
	 */
	public double getPerimeter() {
		return 2 * (width + height);
	}

	/**
	 * Returns the area of the rectangle which is equal to the width multiplied by
	 * the height.
	 * 
	 * @return the area of the rectangle
	 */
	public double getArea() {
		return width * height;
	}

	/**
	 * Compares area of this rectangle object and the other rectangle {@code r}.
	 *
	 * @param r
	 *            the other rectangle
	 * @return the value {@code -1 } if the area of this rectangle is less than the
	 *         argument's rectangle area; {@code +1} if the area of this rectangle
	 *         is greater than the argument's; and {@code 0} if this area of this
	 *         rectangle is equal to the argument's within the specified tolerance
	 *         {@link ShapeUtil#TOL}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument is null
	 */
	@Override
	public int compareTo(Rectangle r) {
		if (r == null)
			throw new IllegalArgumentException("Cannot compare to null objects");

		if (Math.abs(getArea() - r.getArea()) < ShapeUtil.TOL)
			return 0;
		return Double.compare(getArea(), r.getArea());
	}

	/**
	 * Generates a hashCode of this rectangle using its width and height. Use
	 * Eclipse to generate this method for you.
	 * 
	 * @return some hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Compares this rectangle with the argument {@code obj} of Object type. This
	 * method overrides the base method defined in the root class Object.
	 * <p>
	 * Two rectangles are equal if the difference between the longest sides of the
	 * first and second rectangle is within the allowed tolerance specified in
	 * {@link ShapeUtil#TOL}; and similarly, the difference between the shortest
	 * sides of the first and second rectangle is within the allowed tolerance
	 * specified in {@link ShapeUtil#TOL}
	 * 
	 * 
	 * @return {@code true} if the rectangle is equal to the argument rectangle
	 *         within the tolerance specified in {@link ShapeUtil#TOL}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		double s1w = Math.max(height, width);
		double s1h = Math.min(height, width);
		double s2w = Math.max(other.height, other.width);
		double s2h = Math.min(other.height, other.width);
		if (Math.abs(s1w - s2w) >= ShapeUtil.TOL)
			return false;
		if (Math.abs(s1h - s2h) >= ShapeUtil.TOL)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rectangle [" + width + ", " + height + "]";
	}

}
