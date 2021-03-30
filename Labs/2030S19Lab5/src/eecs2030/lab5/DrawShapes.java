package eecs2030.lab5;

import java.awt.Color;
import java.util.List;

/**
 * A program that draws some simple shapes using turtles.
 *
 */
public class DrawShapes {

	public static void main(String[] args) {

		// a turtle that draws a square
		Turtle tSquare = new Turtle(0.25, 0.25, 0.0, Color.BLUE);
		tSquare.walk(0.5);
		tSquare.turnLeft(90.0);
		tSquare.repeatCommands(3);

		// a turtle that draws a circle
		Turtle tCircle = new Turtle(0.25, 0.5, 89.0, Color.RED);
		double r = 0.25;
		tCircle.walk(Math.sqrt(
				r * r + r * r - 2 * r * r * Math.cos(Math.toRadians(1.0))));
		tCircle.turnRight(1.0);
		tCircle.repeatCommands(359);

		// a turtle that draws a dashed line
		Turtle tDashedLine = new Turtle(0.1, 0.9, 270.0, Color.BLACK);
		tDashedLine.walk(0.05);
		tDashedLine.penOff();
		tDashedLine.walk(0.05);
		tDashedLine.penOn();
		tDashedLine.repeatCommands(7);
		List<TurtleCommand> dashed = tDashedLine.getCommands();

		// a turtle that draws a dashed line
		Turtle tStripedLine = new Turtle(0.9, 0.1, 90.0, Color.RED);
		tStripedLine.walk(0.05);
		tStripedLine.setPenColor(Color.GREEN);
		tStripedLine.walk(0.05);
		tStripedLine.setPenColor(Color.RED);
		tStripedLine.repeatCommands(7);
		List<TurtleCommand> striped = tStripedLine.getCommands(); 
		
		// have tStripedLine draw a dashed line
		tStripedLine.turnLeft(90.0);
		tStripedLine.setPenColor(Color.BLACK);
		tStripedLine.doCommands(dashed);
		
		// have tDashedLine draw a striped line
		tDashedLine.turnLeft(90.0);
		tDashedLine.setPenColor(Color.RED);
		tDashedLine.doCommands(striped);
	}
}
