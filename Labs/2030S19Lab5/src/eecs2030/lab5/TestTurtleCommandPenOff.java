package eecs2030.lab5;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTurtleCommandPenOff {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_getTurtle() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandPenOff(t);
		assertSame("getTurtle did not return a reference to the correct turtle",
				t, c.getTurtle());
	}
	
	@Test
	public void test02_execute() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandPenOff(t);
		c.execute();
		Pen p = t.leakPen();
		assertFalse("execute did not turn the turtle\'s pen off",
				p.isOn());
	}

	@Test
	public void test03_execute() {
		Turtle t = new Turtle();
		TurtleCommand c = new TurtleCommandPenOff(t);
		Turtle u = new Turtle();
		c.execute(u);
		Pen p = u.leakPen();
		assertFalse("execute did not turn the turtle\'s pen off",
				p.isOn());
	}
}
