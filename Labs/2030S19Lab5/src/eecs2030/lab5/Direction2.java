package eecs2030.lab5;

import java.util.Objects;

/**
 * A class that represents a direction in 2D in a right-handed coordinate
 * system. A direction is defined by an angle in degrees measured from the
 * positive x axis. Positive angles are measured in the counter-clockwise
 * direction.
 * 
 * <p>
 * Any double value (positive or negative) can be used to construct a direction,
 * but it is a class invariant that {@code Direction2} objects will always
 * normalize the direction angle so that {@code 0.0 <= getDirection() < 360.0}.
 * 
 * @author EECS2030 Summer 2019
 * 
 */
public final class Direction2 {

	private double degrees;

	/**
	 * Initialize this direction to 0 degrees.
	 */
	public Direction2() {
		this(0.0);
	}

	/**
	 * Initialize this direction to have the specified degrees. Note
	 * that the current implementation fails if the specified
	 * degrees is outside the range {@code [0.0, 360.0)}.
	 * 
	 * @param degrees
	 *            the direction measured in degrees
	 *            
	 * @pre. {@code 0.0 <= degrees < 360.0}
	 */
	public Direction2(double degrees) {
		this.degrees = degrees;
	}

	/**
	 * Sets this direction to have the specified degrees. Any double value
	 * (positive or negative) can be used to set the direction, but it is a
	 * class invariant that this {@code Direction2} object will normalize the
	 * direction angle so that {@code 0.0 <= this.getDirection() < 360.0}.
	 * 
	 * @param degrees
	 *            the direction measured in degrees
	 */
	public final void setAngle(double degrees) {
		this.degrees = degrees % 360.0;
		if (this.degrees < 0.0) {
			this.degrees += 360.0;
		}
	}

	/**
	 * Returns the direction measured in degrees represented by this object. The
	 * returned value is guaranteed to be in the range
	 * {@code 0.0 <= this.getDirection() < 360.0}
	 * 
	 * @return the direction measured in degrees
	 */
	public double getAngle() {
		return this.degrees;
	}

	/**
	 * Returns the x component of the unit vector pointing in the direction
	 * given by this object. The returned value is equal to the cosine of the
	 * angle represented by this direction.
	 * 
	 * @return the x component of the unit vector pointing in the direction
	 *         given by this object
	 */
	public double getX() {
		double radians = Math.toRadians(this.degrees);
		return Math.cos(radians);
	}

	/**
	 * Returns the y component of the unit vector pointing in the direction
	 * given by this object. The returned value is equal to the sine of the
	 * angle represented by this direction.
	 * 
	 * @return the y component of the unit vector pointing in the direction
	 *         given by this object
	 */
	public double getY() {
		double radians = Math.toRadians(this.degrees);
		return Math.sin(radians);
	}
	
	/**
	 * Changes the direction represented by this object by adding the
	 * angle delta measured in degrees to the angle of this object.
	 * A positive value of delta corresponds to a counter-clockwise
	 * change of the angle, and a negative value of delta corresponds
	 * to a clockwise change of the angle.
	 * 
	 * @param delta the angle to add to this direction
	 */
	public void turn(double delta) {
		double degrees = this.degrees + delta;
		this.setAngle(degrees);
	}

	/**
	 * Returns a hash code for this object.
	 * 
	 * @return a hash code for this object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.degrees);
	}

	/**
	 * Compares this direction to the specified object. The result is true if
	 * and only if the argument is not null and is a {@code Direction2} object
	 * that represents the same direction as this object.
	 * 
	 * @param obj
	 *            the object to compare this Direction2 against
	 * @return true if the given object represents a Direction2 equivalent to
	 *         this object and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Direction2 other = (Direction2) obj;
		return (Double.doubleToLongBits(this.degrees) == Double
				.doubleToLongBits(other.degrees));
	}

	/**
	 * Returns a string representation of this direction. The returned string is
	 * the angle of this direction in degrees followed by the string
	 * {@code " deg"}.
	 * 
	 * @return a string representation of this direction
	 */
	@Override
	public String toString() {
		return this.degrees + " deg";
	}
}
