package eecs2030.lab5;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTurtleCommandTurnRight {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_getTurtle() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandTurnRight(t, 55.0);
		assertSame("getTurtle did not return a reference to the correct turtle",
				t, c.getTurtle());
	}
	
	@Test
	public void test02_execute() {
		Turtle t = new Turtle(0.1, 0.1, 65.5, Color.BLACK);
		Direction2 dir = t.leakDirection();
		TurtleCommand c = new TurtleCommandTurnRight(t, 55.0);
		c.execute();
		assertEquals("execute did not turn the turtle right by the correct angle",
				10.5, dir.getAngle(), 1e-8);
	}

	@Test
	public void test03_execute() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandTurnRight(t, 25.0);
		Turtle u = new Turtle(0.1, 0.1, 25.5, Color.BLACK);
		Direction2 dir = u.leakDirection();
		c.execute(u);
		assertEquals("execute did not turn the turtle right by the correct angle",
				0.5, dir.getAngle(), 1e-8);
	}

	@Test
	public void test04_getDelta() {
		Turtle t = new Turtle(0.1, 0.1, 25.5, Color.BLACK);
		TurtleCommandTurnRight c = new TurtleCommandTurnRight(t, 55.0);
		assertEquals("getDelta did not return the correct angle",
				55.0, c.getDelta(), 1e-8);
	}
}
