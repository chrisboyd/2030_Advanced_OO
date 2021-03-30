package eecs2030.lab3;

/**
 * The utility class {@code ShapeUtil} provides helper methods to instantiate
 * objects of basic shapes and compare them by area. It also provides the
 * capability to introduce methods that are not implemented in the shape
 * classes.
 * 
 * <p>
 * The recognized shapes by this class includes: {@code Circle},
 * {@code Rectangle}, and {@code Triangle}.
 * 
 * @author EECS2030 Summer 2019
 *
 */
public final class ShapeUtil {

	/**
	 * A tolerance value below which two objects are considered equal. The value of
	 * the tolerance is 1e-6
	 */
	public static final double TOL = 1e-6;

	/**
	 * The no-args constructor is private so a client cannot instantiate an object
	 * from this utility class.
	 */
	private ShapeUtil() {

	}

	/**
	 * Static method that returns a new circle object
	 * 
	 * @param radius
	 *            the radius of the circle
	 * @return object of {@link Circle} class that has the radius {@code radius}
	 */
	public static Circle circle(double radius) {
		return new Circle(radius);
	}

	/**
	 * Static method that returns a new triangle object
	 * 
	 * @param side1
	 *            the first side of this triangle
	 * @param side2
	 *            the second side of this triangle
	 * @param side3
	 *            the third side of this triangle
	 * @return object of {@link Triangle} class
	 */
	public static Triangle triangle(double side1, double side2, double side3) {
		return new Triangle(side1, side2, side3);
	}

	/**
	 * Static method that returns a new rectangle object
	 * 
	 * @param width
	 *            the width of this rectangle
	 * @param height
	 *            the height of this rectangle
	 * @return object of {@link Rectangle} class
	 */
	public static Rectangle rectangle(double width, double height) {
		return new Rectangle(width, height);
	}

	/**
	 * Static method to create the unit circle that has the {@code radius = 1.0}
	 * 
	 * @return the unit circle
	 */
	public static Circle unitCircle() {
		return new Circle(1.0);
	}

	/**
	 * Static method to create an equilateral triangle with sides length
	 * {@code = side}
	 * 
	 * @param side
	 *            the length of the equilateral sides
	 * 
	 * @return equilateral triangle
	 */
	public static Triangle equilateralTri(double side) {
		return new Triangle(side, side, side);
	}

	/**
	 * Static method to create a square that has side length {@code = side}
	 * 
	 * @param side
	 *            the length of the square side
	 * @return a square
	 */
	public static Rectangle square(double side) {
		return new Rectangle(side, side);
	}

	/**
	 * A static method that returns the circle inscribed inside a triangle. The
	 * radius of the inscribed circle is equal to the area of the triangle divided
	 * by its semiperimeter.
	 * 
	 * @param t
	 *            the triangle to find the inscribed circle inside.
	 * @return the inscribed circle inside the triangle argument
	 */
	public static Circle incircle(Triangle t) {
		double s = t.getPerimeter() / 2D;
		double r = t.getArea() / s;
		return circle(r);
	}

	/**
	 * An overloaded static method that returns the circle inscribed inside a
	 * square. The radius of the inscribed circle is equal to half of the side
	 * length. The method checks that the argument rectangle is a square otherwise
	 * it throws {@link IllegalArgumentException}
	 * 
	 * @param r
	 *            the square to find the inscribed circle inside.
	 * @throws IllegalArgumentException
	 *             if the argument rectangle is not square
	 * @return the inscribed circle inside the square argument
	 */
	public static Circle incircle(Rectangle r) {
		if (!r.isSquare())
			throw new IllegalArgumentException("Cannot form an inscribed circle for non-square rectangles");
		double d = r.getWidth();
		return circle(d / 2D);
	}

	/**
	 * This static method returns the area of the argument shape. Based on the class
	 * name of the argument it decides to call the getArea method of the specific
	 * shape argument. The recognized shape types by this method are:
	 * {@link Circle}, {@link Rectangle}, or {@link Triangle}. If the argument is
	 * not a recognized shape, the method throws {@link IllegalArgumentException}.
	 * 
	 * @param shape
	 *            an instance of one of the recognized shapes.
	 * @throws IllegalArgumentException
	 *             if the argument type is not one of recognized shapes or the shape is null.
	 * @return the area of the argument shape
	 * 
	 * 		@pre. the argument {@code shape} is one of the recognized shapes.
	 */
	public static double getArea(Object shape) {
		if(shape == null)
			throw new IllegalArgumentException("Null shape argument");
		switch (shape.getClass().getSimpleName()) {
		case "Circle":
			Circle c = (Circle) shape;
			return c.getArea();
		case "Rectangle":
			Rectangle r = (Rectangle) shape;
			return r.getArea();
		case "Triangle":
			Triangle t = (Triangle) shape;
			return t.getArea();
		default:
			throw new IllegalArgumentException("Unrecognized shape type");
		}
	}

	/**
	 * This static method compares two shapes by their area for sorting. This method
	 * calls the static getArea method to find the area of the shapes arguments.
	 * 
	 * @param shape1
	 *            the first shape to compare
	 * @param shape2
	 *            the second shape to compare
	 * @return the value {@code -1 } if the area of {@code shape1} is less than the
	 *         area of {@code shape2}; {@code +1} if the area of {@code shape1} is
	 *         greater than the area of {@code shape2}; and {@code 0} if this area
	 *         of {@code shape1} is equal to the area of {@code shape2} within the
	 *         specified tolerance {@link ShapeUtil#TOL}
	 * 
	 * 		@pre. the arguments {@code shape1} and {@code shape2} are one of the
	 *         recognized shapes.
	 */
	public static int compare(Object shape1, Object shape2) {
		double a1 = getArea(shape1);
		double a2 = getArea(shape2);
		if (Math.abs(a1 - a2) < ShapeUtil.TOL)
			return 0;
		return Double.compare(a1, a2);
	}

}
