package eecs2030.lab5;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TurtleTest {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_copyCtor() {
		Turtle t = new Turtle(0.1, 0.2, 30.0, Color.CYAN);
		Turtle u = new Turtle(t);
		assertNotSame("copy ctor failed to make a defensive copy of other.position",
				u.leakPosition(), t.leakPosition());
		assertEquals("copy ctor failed to correctly copy other.position",
				new Point2(0.1, 0.2), u.leakPosition());
	}
	
	@Test
	public void test02_copyCtor() {
		Turtle t = new Turtle(0.1, 0.2, 30.0, Color.CYAN);
		Turtle u = new Turtle(t);
		assertNotSame("copy ctor failed to make a defensive copy of other.direction",
				u.leakDirection(), t.leakDirection());
		assertEquals("copy ctor failed to correctly copy other.direction",
				new Direction2(30.0), u.leakDirection());
	}
	
	@Test
	public void test03_copyCtor() {
		Turtle t = new Turtle(0.1, 0.2, 30.0, Color.CYAN);
		Turtle u = new Turtle(t);
		assertNotSame("copy ctor failed to make a defensive copy of other.pen",
				u.leakPen(), t.leakPen());
		assertEquals("copy ctor failed to correctly copy other.pen",
				new Pen(Color.CYAN), u.leakPen());
	}

	@Test
	public void test04_copyCtor() {
		Turtle t = new Turtle(0.15, 0.25, 35.0, Color.RED);
		Turtle u = new Turtle(t);
		assertEquals("copy ctor failed to correctly copy other.position",
				new Point2(0.15, 0.25), u.leakPosition());
		assertEquals("copy ctor failed to correctly copy other.direction",
				new Direction2(35.0), u.leakDirection());
		assertEquals("copy ctor failed to correctly copy other.pen",
				new Pen(Color.RED), u.leakPen());
	}
	
	@Test
	public void test05_getPosition() {
		Turtle t = new Turtle(0.25, 0.75, 35.0, Color.RED);
		assertNotSame("getPosition failed to make a defensive copy of this.position",
				t.leakPosition(), t.getPosition());
		assertEquals("getPosition returned the wrong position",
				new Point2(0.25, 0.75), t.getPosition());
	}
	
	@Test
	public void test06_getDirection() {
		Turtle t = new Turtle(0.25, 0.75, 45.0, Color.RED);
		assertNotSame("getDirection failed to make a defensive copy of this.direction",
				t.leakDirection(), t.getDirection());
		assertEquals("getDirection returned the wrong direction",
				new Direction2(45.0), t.getDirection());
	}
	
	@Test
	public void test07_getPen() {
		Turtle t = new Turtle(0.25, 0.75, 45.0, Color.MAGENTA);
		assertNotSame("getPen failed to make a defensive copy of this.pen",
				t.leakPen(), t.getPen());
		assertEquals("getPen returned the wrong pen",
				new Pen(Color.MAGENTA), t.getPen());
	}
	
	
}
