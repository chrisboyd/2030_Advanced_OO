package eecs2030.lab5;

import java.awt.Color;

/**
 * A command corresponding to a turtle executing the method
 * {@code setPenColor(newColor)}.
 *
 */
public class TurtleCommandSetPenColor extends TurtleCommand {

	private Color oldColor;
	private Color newColor;

	/**
	 * Initialize this command so that this command is associated with the specified
	 * turtle executing the method {@code setPenColor(newColor)}. The previous pen
	 * color is also stored in this command.
	 * 
	 * @param turtle   the turtle that is associated with this command
	 * @param oldColor the previous color of the pen
	 * @param newColor the new color of the pen
	 */
	public TurtleCommandSetPenColor(Turtle turtle, Color oldColor, Color newColor) {
		super(turtle);
		this.oldColor = oldColor;
		this.newColor = newColor;
	}

	/**
	 * Returns the previous pen color.
	 * 
	 * @return the previous pen color
	 */
	public Color getOldColor() {
		return oldColor;
	}

	/**
	 * Returns the new (or current) pen color.
	 * 
	 * @return the new pen color
	 */
	public Color getNewColor() {
		return newColor;
	}

	/**
	 * Runs the method ({@code setPenColor(newColor)} using this turtle.
	 */
	public void execute() {
		this.getTurtle().setPenColor(this.newColor);
	}

	/**
	 * Run the method ({@code setPenColor(newColor)} using the specified turtle.
	 * 
	 * @param t a turtle that should run this command
	 */
	public void execute(Turtle t) {
		t.setPenColor(this.newColor);
	}
}
