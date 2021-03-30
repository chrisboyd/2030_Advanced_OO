package eecs2030.lab5;

/**
 * A command corresponding to a turtle executing the method {@code penOn()}.
 *
 */
public class TurtleCommandPenOn extends TurtleCommand {

	/**
	 * Initialize this command so that this command is associated with the specified
	 * turtle.
	 * 
	 * @param turtle the turtle that is associated with this command
	 */
	public TurtleCommandPenOn(Turtle turtle) {
		super(turtle);
	}

	/**
	 * Runs the method ({@code penOn} using this turtle.
	 */
	public void execute() {
		this.getTurtle().penOn();
	}

	/**
	 * Run the method ({@code penOn} using the specified turtle.
	 * 
	 * @param t a turtle that should run this command
	 */
	public void execute(Turtle t) {
		t.penOn();
	}
}
