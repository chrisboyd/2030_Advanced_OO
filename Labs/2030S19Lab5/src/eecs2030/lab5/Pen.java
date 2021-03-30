package eecs2030.lab5;

import java.awt.Color;
import java.util.Objects;

import princeton.introcs.StdDraw;

/**
 * A class representing a pen that draws straight lines on a
 * {@code princeton.introcs.StdDraw} object. A pen has a color
 * and can be on or off. A pen will draw a line if it is on
 * but not if it is off.
 * 
 * @author EECS2030 Summer 2019
 *
 */
public class Pen {

	private Color color;
	private boolean isOn;
	
	/**
	 * Initialize this pen so that its color is black and it is on.
	 */
	public Pen() {
		this(Color.BLACK);
	}
	
	/**
	 * Initialize this pen so that its color is the same as
	 * {@code other.getColor()} and it is on if {@code other.isOn()}
	 * is true and it is off if {@code other.isOn()} is false.
	 * 
	 * @param other a pen to copy
	 */
	public Pen(Pen other) {
		this.color = other.color;
		this.isOn = other.isOn;
	}
	
	/**
	 * Initialize this pen so that its color is equal to the specified
	 * color and it is on.
	 * 
	 * @param color the color of this pen
	 */
	public Pen(Color color) {
		this.color = color;
		this.isOn = true;
	}
	
	/**
	 * Returns the color of this pen.
	 * 
	 * @return the color of this pen
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Sets the color of this pen to the specified color.
	 * 
	 * @param color the color to set this pen to
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Returns true if this pen is on and false if this pen is off.
	 * 
	 * @return returns true if this pen is on and false if this pen is off
	 */
	public boolean isOn() {
		return this.isOn;
	}
	
	/**
	 * Turns this pen on.
	 */
	public void on() {
		this.isOn = true;
	}
	
	/**
	 * Turns this pen off.
	 */
	public void off() {
		this.isOn = false;
	}
	
	/**
	 * Draws a line using the color of this pen on a StdDraw object.
	 * No line is drawn if this pen is off.
	 * 
	 * @param from the start point of the line
	 * @param to the end point of the line
	 */
	public void drawLine(Point2 from, Point2 to) {
		if (this.isOn) {
			StdDraw.setPenColor(this.getColor());
			StdDraw.line(from.getX(), from.getY(), to.getX(),
					to.getY());
		}
	}
	
	/**
	 * Returns a string representation of this pen. The returned string
	 * is the color of this pen followed by " : " followed by "true" if
	 * this pen is on and "false" if this pen is off.
	 * 
	 * @return a string representation of this pen
	 */
	public String toString() {
		return this.color.toString() + " : " + this.isOn;
	}

	/**
	 * Returns a hash code for this pen.
	 * 
	 * @return a hash code for this pen.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.color, this.isOn);
	}

	/**
	 * Compares this pen to the specified object. The result is true if
	 * and only if the argument is not null and is a {@code Pen} object
	 * that has the same color as this pen and the same on-off state as
	 * this pen.
	 * 
	 * @param obj the object to compare this Pen against
	 * @return true if the given object represents a Pen equivalent to
	 *         this object and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pen other = (Pen) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		}
		else if (!color.equals(other.color))
			return false;
		if (isOn != other.isOn)
			return false;
		return true;
	}
	
	
}
