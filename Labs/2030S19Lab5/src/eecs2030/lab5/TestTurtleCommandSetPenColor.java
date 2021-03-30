package eecs2030.lab5;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTurtleCommandSetPenColor {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_getTurtle() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandSetPenColor(t, t.leakPen().getColor(), Color.GREEN);
		assertSame("getTurtle did not return a reference to the correct turtle",
				t, c.getTurtle());
	}
	
	@Test
	public void test02_execute() {
		Turtle t = new Turtle();
		Pen p = t.leakPen();
		TurtleCommand c = new TurtleCommandSetPenColor(t, p.getColor(), Color.ORANGE);
		c.execute();
		assertEquals("execute did not set the turtle\'s pen color",
				Color.ORANGE, p.getColor());
	}

	@Test
	public void test03_execute() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandSetPenColor(t, t.leakPen().getColor(), Color.LIGHT_GRAY);
		Turtle u = new Turtle();
		Pen p = u.leakPen();
		c.execute(u);
		assertEquals("execute did not set the turtle\'s pen color",
				Color.LIGHT_GRAY, p.getColor());
	}
	
	@Test
	public void test04_getOldColor() {
		Turtle t = new Turtle();
		Pen p = t.leakPen();
		TurtleCommandSetPenColor c = new TurtleCommandSetPenColor(t, p.getColor(), Color.ORANGE);
		assertEquals("execute did not set the turtle\'s pen color",
				p.getColor(), c.getOldColor());
	}
	
	@Test
	public void test05_getNewColor() {
		Turtle t = new Turtle();
		Pen p = t.leakPen();
		TurtleCommandSetPenColor c = new TurtleCommandSetPenColor(t, p.getColor(), Color.ORANGE);
		assertEquals("execute did not set the turtle\'s pen color",
				Color.ORANGE, c.getNewColor());
	}
}
