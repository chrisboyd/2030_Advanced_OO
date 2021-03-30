package eecs2030.lab5;

/**
 * A command corresponding to a turtle executing the method
 * {@code turnTo(angle)}.
 *
 */
public class TurtleCommandTurnTo extends TurtleCommand {

	private double angle;
	
	/**
	 * Initialize this command so that this command is associated with
	 * the specified turtle executing a {@code turnTo(angle)}
	 * command.
	 * 
	 * @param turtle the turtle that is associated with this command
	 * @param angle the angle of the turnTo command
	 */
	public TurtleCommandTurnTo(Turtle turtle, double angle) {
		super(turtle);
		this.angle = angle;
	}
	
	/**
	 * Returns the angle associated with this command.
	 * 
	 * @return the angle associated with this command
	 */
	public double getAngle() {
		return this.angle;
	}

	/**
	 * Runs the method {@code turnTo(angle)} using the turtle associated
	 * with this command.
	 */
	@Override
	public void execute() {
		this.getTurtle().turnTo(this.angle);
	}

	/**
	 * Runs the method {@code turnTo(angle)} using the specified turtle.
	 */
	@Override
	public void execute(Turtle t) {
		t.turnTo(this.angle);
	}

}
