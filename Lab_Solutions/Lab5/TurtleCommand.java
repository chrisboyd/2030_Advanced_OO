package eecs2030.lab5;

/**
 * Abstract base class for classes that implement a turtle command.
 * A turtle command encapsulates the information needed to call
 * a {@code Turtle} method.
 *
 */
public abstract class TurtleCommand {

	private Turtle turtle;
	
	/**
	 * Initialize this command so that this command is associated with
	 * the specified turtle.
	 * 
	 * @param turtle the turtle that is associated with this command
	 */
	public TurtleCommand(Turtle turtle) {
		this.turtle = turtle;
	}
	
	/**
	 * Get the turtle associated with this command.
	 * 
	 * @return a reference to the turtle associated with this command
	 */
	public Turtle getTurtle() {
		return this.turtle;
	}
	
	/**
	 * Run this command using the turtle associated with this command.
	 */
	public abstract void execute();
	
	/**
	 * Run this command using the specified turtle.
	 * 
	 * @param t a turtle that should run this command
	 */
	public abstract void execute(Turtle t);
}
