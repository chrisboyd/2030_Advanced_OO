package eecs2030.lab5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that supports turtle graphics. A turtle walks between two points in a
 * straight line drawing the line as it moves.
 * 
 * <p>
 * A turtle has-a {@code Point2} instance that represents the position of
 * the turtle, a {@code Direction2} instance that represents the direction
 * that the turtle is facing in, and a {@code Pen} instance that represents the pen
 * that the turtle draws with.
 * 
 * @author EECS2030 Winter 2017-18
 * 
 */
public class Turtle {
	/* DO NOT MODIFY THESE FIELDS */
	/* DO NOT ADD NEW FIELDS */
	private Point2 position;
	private Direction2 direction;
	private Pen pen;
	private List<TurtleCommand> commands;

	/**
	 * Create a turtle at location {@code (0.5, 0.5)} with an direction of
	 * {@code 0.0} degrees, a pen color of {@code Color.BLACK}, and an
	 * empty collection of commands.
	 */
	public Turtle() {
		this(0.5, 0.5, 0.0, Color.BLACK);
	}

	/**
	 * Create a turtle with the given starting position, direction, and pen.
	 * The turtle will have an empty collection of commands.
	 * The starting position must be inside the square with corners
	 * {@code (0.0, 0.0)} and {@code (1.0, 1.0)}, otherwise an
	 * {@code IllegalArgumentException} will be thrown.
	 * 
	 * @param x
	 *            the x coordinate of the starting position of the turtle
	 * @param y
	 *            the y coordinate of the starting position of the turtle
	 * @param angle
	 *            the angle in degrees from the x axis that the turtle is facing
	 *            in
	 * @param c
	 *            the color of the pen that the turtle should draw with
	 * @throws IllegalArgumentException
	 *             if the starting position is not in the square with corners
	 *             (0.0, 0.0) and (1.0, 1.0)
	 */
	public Turtle(double x, double y, double angle, Color c) {
		if (x >= 0. && x <= 1.0 && y >= 0. && y <= 1.0) {
			this.position = new Point2(x, y);
			this.direction = new Direction2(angle);
			this.pen = new Pen(c);
			this.commands = new ArrayList<>();
		}
		else {
			throw new IllegalArgumentException("Invalid starting position!");
		}
	}
	
	/**
	 * Create a turtle by copying the position, direction, pen, and commands of
	 * another turtle.
	 * 
	 * @param other the turtle to copy
	 */
	public Turtle(Turtle other) {
		this.position = new Point2(other.position);
		this.direction = new Direction2(other.direction.getAngle());
		this.pen = new Pen(other.pen);
		this.commands = new ArrayList<>(other.commands);
	}
	

	/**
	 * Walks the turtle forward by a given distance in the direction the turtle
	 * is currently facing. A line is drawn as the turtle moves to the new
	 * position using the current pen color.
	 * 
	 * @param distance
	 *            the distance to move
	 * @throws IllegalArgumentException
	 *             if distance is less than zero
	 */
	public void walk(double distance) {
		if (distance < 0.0) {
			throw new IllegalArgumentException();
		}
		Point2 current = new Point2(this.position);
		this.position.set(current.getX() + distance * this.direction.getX(),
				current.getY() + distance * this.direction.getY());
		this.pen.drawLine(current, this.position);
		this.commands.add(new TurtleCommandWalk(this, distance));
	}


	/**
	 * Turns the turtle to the left (counter clockwise) by an amount
	 * delta degrees.
	 * 
	 * @param delta the angle to turn counter clockwise by
	 * 
	 * @pre. {@code delta >= 0.0}
	 */
	public void turnLeft(double delta) {
		this.direction.turn(delta);
		this.commands.add(new TurtleCommandTurnLeft(this, delta));
	}

	/**
	 * Turns the turtle to the right (clockwise) by an amount delta degrees.
	 * 
	 * @param delta the angle to turn clockwise by
	 * @pre. {@code delta >= 0.0}
	 */
	public void turnRight(double delta) {
		this.direction.turn(-delta);
		this.commands.add(new TurtleCommandTurnRight(this, delta));
	}

	/**
	 * Turns the turtle so that its direction is equal to the given
	 * angle in degrees. Any value of delta can be used, but the turtle
	 * normalize its direction angle so that {@code 0.0 <= this.getAngle() < 360.0}.
	 * 
	 * @param angle
	 *            the new direction angle of the turtle
	 */
	public void turnTo(double angle) {
		this.direction.setAngle(angle);
		this.commands.add(new TurtleCommandTurnTo(this, this.direction.getAngle()));
	}
	
	/**
	 * Sets this turtle's pen to on.
	 */
	public void penOn() {
		this.pen.on();
		this.commands.add(new TurtleCommandPenOn(this));
	}
	
	/**
	 * Sets this turtle's pen to off.
	 */
	public void penOff() {
		this.pen.off();
		this.commands.add(new TurtleCommandPenOff(this));
	}
	
	/**
	 * Sets this turtle's pen color to the specified pen color.
	 * 
	 * @param c the new pen color
	 */
	public void setPenColor(Color c) {
		Color oldColor = this.pen.getColor();
		this.pen.setColor(c);
		this.commands.add(new TurtleCommandSetPenColor(this, oldColor, c));
	}

	/**
	 * Gets the pen belonging to this turtle.
	 * 
	 * @return the pen belonging to this turtle 
	 */
	public Pen getPen() {
		return new Pen(this.pen);
	}

	/**
	 * Gets the current position of the turtle. 
	 *  
	 * @return the current position of the turtle
	 */
	public Point2 getPosition() {
		return new Point2(this.position);
	}

	/**
	 * Gets the direction that the turtle is facing in.
	 * 
	 * @return the direction that the turtle is facing in
	 */
	public Direction2 getDirection() {
		return new Direction2(this.direction.getAngle());
	}
	
	/**
	 * Returns a shallow copy of this turtle's collection of commands.
	 * 
	 * @return a shallow copy of this turtle's collection of commands\
	 */
	public List<TurtleCommand> getCommands() {
		return new ArrayList<TurtleCommand>(this.commands);
	}
	
	/**
	 * Repeat all of the commands that this turtle has previously performed
	 * n times. For example:
	 * 
	 * <pre>
	 * Turtle t = new Turtle();
	 * t.walk(0.2);
	 * t.turnLeft(90);
	 * t.repeatCommands(3);  // repeat previous commands 3 more times
	 * </pre>
	 * 
	 * <p>
	 * would cause the turtle to draw a square.
	 * 
	 * @param n the number of times to repeat the commands that this turtle
	 * has previously performed
	 * 
	 * @pre. {@code n >= 0}
	 * 
	 */
	public void repeatCommands(int n) {
		List<TurtleCommand> commands = this.getCommands();
		for (int i = 0; i < n; i++) {
			for (TurtleCommand c : commands) {
				c.execute();
			}
		}
	}
	
	/**
	 * Have this turtle execute each command in the given list of commands.
	 * The commands are executed in order that they appear in the given
	 * list, and are added to the end of this turtle's collection of
	 * commands.
	 * 
	 * @param commands a list of commands for this turtle to execute
	 */
	public void doCommands(List<TurtleCommand> commands) {
		for (TurtleCommand c : commands) {
			c.execute(this);
		}
	}
	
	
	/* DO NOT MODIFY THE METHODS AFTER THIS COMMENT; THEY ARE HERE FOR TESTING
	   PURPOSES */
	
	/**
	 * Returns a reference to the position of this turtle.
	 * 
	 * @return a reference to the position of this turtle
	 */
	Point2 leakPosition() {
		return this.position;
	}
	
	/**
	 * Returns a reference to the direction of this turtle.
	 * 
	 * @return a reference to the direction of this turtle
	 */
	Direction2 leakDirection() {
		return this.direction;
	}
	
	/**
	 * Returns a reference to the pen of this turtle.
	 * 
	 * @return a reference to the pen of this turtle
	 */
	Pen leakPen() {
		return this.pen;
	}
	
	/**
	 * Returns a reference to the collection of commands of this turtle.
	 * 
	 * @return a reference to the collection of commands of this turtle
	 */
	List<TurtleCommand> leakCommands() {
		return this.commands;
	}
	
	/**
	 * Returns a string representation of this turtle. The string representation
	 * is:
	 * 
	 * <ol>
	 * <li>the position of the turtle (as given by
	 * {@code Point2.toString}), followed by
	 * <li>a comma and a space, followed by
	 * <li>the direction of this turtle (as given by {@code Direction2.toString}),
	 * followed by
	 * <li>a space and a comma, followed by
	 * <li>the pen (as given by {@code Pen.toString})
	 * </ol>
	 * 
	 * @return a string representation of this turtle
	 */
	@Override
	public String toString() {
		String s = String.format("%s, %s degrees, %s", this.getPosition(),
				this.getDirection(), this.getPen());
		return s;
	}

	/**
	 * Returns a hash code for this turtle.
	 * 
	 * @return a hash code for this turtle
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.position, this.direction, this.pen);
	}

	/**
	 * Compares this turtle to the specified object. The result is true if
	 * and only if the argument is not null and is a {@code Turtle} object
	 * having a position, direction, and pen equal to this turtle's
	 * position, direction, and pen.
	 * 
	 * @param obj
	 *            the object to compare this Turtle against
	 * @return true if the given object represents a Turtle equivalent to
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Turtle other = (Turtle) obj;
		if (direction == null) {
			if (other.direction != null) {
				return false;
			}
		} else if (!direction.equals(other.direction)) {
			return false;
		}
		if (pen == null) {
			if (other.pen != null) {
				return false;
			}
		} else if (!pen.equals(other.pen)) {
			return false;
		}
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}
	
}
