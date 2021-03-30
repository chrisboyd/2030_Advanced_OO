package eecs2030.lab5;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTurtleCommandWalk {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_getTurtle() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandWalk(t, 0.1);
		assertSame("getTurtle did not return a reference to the correct turtle",
				t, c.getTurtle());
	}
	
	@Test
	public void test02_execute() {
		Turtle t = new Turtle(0.1, 0.1, 90.0, Color.BLACK);
		TurtleCommand c = new TurtleCommandWalk(t, 0.3);
		c.execute();
		Point2 pos = t.leakPosition();
		Point2 exp = new Point2(0.1, 0.4);
		assertTrue("execute did not walk the turtle to the correct position",
				exp.similarTo(pos, 1e-6));
	}

	@Test
	public void test03_execute() {
		Turtle t = new Turtle(0.1, 0.1, 90.0, Color.BLACK);
		TurtleCommand c = new TurtleCommandWalk(t, 0.3);
		Turtle u = new Turtle(0.2, 0.3, 90.0, Color.BLACK);
		c.execute(u);
		Point2 pos = u.leakPosition();
		Point2 exp = new Point2(0.2, 0.6);
		assertTrue("execute did not walk the turtle to the correct position",
				exp.similarTo(pos, 1e-6));
	}

	@Test
	public void test04_getDistance() {
		Turtle t = new Turtle(0.1, 0.1, 25.5, Color.BLACK);
		TurtleCommandWalk c = new TurtleCommandWalk(t, 0.95);
		assertEquals("getDelta did not return the correct angle",
				0.95, c.getDistance(), 1e-8);
	}
}
