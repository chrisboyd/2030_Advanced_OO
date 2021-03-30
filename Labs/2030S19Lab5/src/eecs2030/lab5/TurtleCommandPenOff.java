package eecs2030.lab5;

/**
 * A command corresponding to a turtle executing the method {@code penOff()}.
 *
 */
public class TurtleCommandPenOff extends TurtleCommand {

	/**
	 * Initialize this command so that this command is associated with the specified
	 * turtle.
	 * 
	 * @param turtle the turtle that is associated with this command
	 */
	public TurtleCommandPenOff(Turtle turtle) {
		super(turtle);
	}

	/**
	 * Runs the method ({@code penOff} using this turtle.
	 */
	public void execute() {
		this.getTurtle().penOff();

	}

	/**
	 * Run the method ({@code penOff} using the specified turtle.
	 * 
	 * @param t a turtle that should run this command
	 */
	public void execute(Turtle t) {
		t.penOff();
	}
}
