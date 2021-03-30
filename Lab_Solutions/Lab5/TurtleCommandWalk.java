package eecs2030.lab5;

/**
 * A command corresponding to a turtle executing the method
 * {@code walk(distance)}.
 *
 */
public class TurtleCommandWalk extends TurtleCommand {

	private double distance;
	
	/**
	 * Initialize this command so that this command is associated with
	 * the specified turtle executing a {@code walk(distance)}
	 * command.
	 * 
	 * @param turtle the turtle that is associated with this command
	 * @param distance the distance of the walk command
	 */
	public TurtleCommandWalk(Turtle turtle, double distance) {
		super(turtle);
		this.distance = distance;
	}
	
	/**
	 * Returns the distance associated with this command.
	 * 
	 * @return the distance associated with this command
	 */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * Runs the method {@code walk(distance)} using the turtle associated
	 * with this command.
	 */
	@Override
	public void execute() {
		this.execute(this.getTurtle());
	}

	/**
	 * Runs the method {@code walk(distance)} with the specified turtle.
	 */
	@Override
	public void execute(Turtle t) {
		t.walk(this.distance);
	}

}
