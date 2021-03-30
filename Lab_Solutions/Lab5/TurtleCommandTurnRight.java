package eecs2030.lab5;

/**
 * A command corresponding to a turtle executing the method
 * {@code turnRight(delta)}.
 *
 */
public class TurtleCommandTurnRight extends TurtleCommand {

	private double delta;
	
	/**
	 * Initialize this command so that this command is associated with
	 * the specified turtle executing a {@code turnRight(delta)}
	 * command.
	 * 
	 * @param turtle the turtle that is associated with this command
	 * @param delta the angle of the turnRight command
	 */
	public TurtleCommandTurnRight(Turtle turtle, double delta) {
		super(turtle);
		this.delta = delta;
	}
	
	/**
	 * Returns the angle associated with this command.
	 * 
	 * @return the angle associated with this command
	 */
	public double getDelta() {
		return this.delta;
	}

	/**
	 * Runs the method {@code turnRight(delta)} using the turtle associated
	 * with this command.
	 */
	@Override
	public void execute() {
		this.getTurtle().turnRight(this.delta);
	}

	/**
	 * Runs the method {@code turnRight(delta)} using the specified turtle.
	 */
	@Override
	public void execute(Turtle t) {
		t.turnRight(this.delta);
	}

}
