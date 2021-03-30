package eecs2030.lab5;

import java.util.Objects;

/**
 * A simple class for representing points in 2D Cartesian
 * coordinates. Every {@code Point2} instance has an
 * x and y coordinate.
 * 
 * @author EECS2030 Winter 2018
 *
 */
public class Point2 {

    private double x;
    private double y;
    
    /**
     * Create a point with coordinates {@code (0, 0)}.
     */
    public Point2() {
        this.set(0.0, 0.0);
    }
    
    /**
     * Create a point with coordinates {@code (newX, newY)}.
     * 
     * @param newX the x-coordinate of the point
     * @param newY the y-coordinate of the point
     */
    public Point2(double newX, double newY) {
        this.set(newX, newY);
    }
    
    /**
     * Create a point with the same coordinates as {@code other}.
     * 
     * @param other another point
     */
    public Point2(Point2 other) {
        this(other.x, other.y);
    }
    
    /**
     * Returns the x-coordinate of this point.
     * 
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Returns the y-coordinate of this point.
     * 
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Sets the x-coordinate of this point to {@code newX}.
     * 
     * @param newX the new x-coordinate of this point
     */
    public void setX(double newX) {
        this.x = newX;
    }
    
    /**
     * Sets the y-coordinate of this point to {@code newY}.
     * 
     * @param newY the new y-coordinate of this point
     */
    public void setY(double newY) {
        this.y = newY;
    }
    
    
    /**
     * Sets the x-coordinate and y-coordinate of this point to
     * {@code newX} and {@code newY}, respectively.
     * 
     * @param newX the new x-coordinate of this point
     * @param newY the new y-coordinate of this point
     */
    public void set(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
    
    
    /**
     * Returns a string representation of this point. The string
     * representation of this point is the x and y-coordinates
     * of this point, separated by a comma and space, inside a pair
     * of parentheses. 
     * 
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        String s = String.format("(%s, %s)", this.getX(), this.getY());
        return s;
    }

    /**
     * Returns a hash code for this point
     * 
     * @return a hash code for this point
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    
    /**
     * Compares this point with the given object. The result is
     * {@code true} if and only if the argument is not {@code null}
     * and is a {@code Point2} object having the same coordinates as this
     * object.
     * 
     * @param obj
     *            the object to compare this vector against
     * @return true if the given object represents a
     *         Point2 equivalent to this point, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point2 other = (Point2) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }
    
    /**
     * Determines if two points are almost equal (similar). Two points are
     * similar if the distance between them is smaller than the
     * specified tolerance.
     * 
     * @param other
     *            the other point to compare
     * @param tol
     *            the threshold distance between this point and the other point
     * @return true if the length of (this - other) is
     *         less than tol, and false otherwise
     */
    public boolean similarTo(Point2 other, double tol) {
        return Math.abs(Math.hypot(this.x - other.x, this.y - other.y)) < tol;
    }
}
