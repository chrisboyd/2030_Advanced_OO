package eecs2030.lab5;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTurtle {

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
	
	@Test
	public void test08_getCommands() {
		Turtle t = new Turtle(0.25, 0.75, 45.0, Color.MAGENTA);
		assertNotSame("getCommands failed to make a defensive copy of this.commands",
				t.leakCommands(), t.getCommands());
	}
	
	@Test
	public void test09_getCommands() {
		Turtle t = new Turtle(0.25, 0.75, 45.0, Color.MAGENTA);
		assertEquals("getCommands returned the wrong list of commands",
				new ArrayList<TurtleCommand>(), t.getCommands());
	}
	
	@Test
	public void test10_getCommands() {
		Turtle t = new Turtle(0.25, 0.75, 45.0, Color.MAGENTA);
		List<TurtleCommand> exp = new ArrayList<TurtleCommand>();
		
		t.turnLeft(21.5);
		exp.add(new TurtleCommandTurnLeft(t, 21.5));
		
		t.walk(0.2);
		exp.add(new TurtleCommandWalk(t, 0.2));
		
		t.turnLeft(100.1);
		exp.add(new TurtleCommandTurnLeft(t, 100.1));
		
		t.penOff();
		exp.add(new TurtleCommandPenOff(t));
		
		t.walk(0.01);
		exp.add(new TurtleCommandWalk(t, 0.01));
		
		t.turnTo(180.0);
		exp.add(new TurtleCommandTurnTo(t, 180.0));
		
		t.penOn();
		exp.add(new TurtleCommandPenOn(t));
		
		t.setPenColor(Color.CYAN);
		exp.add(new TurtleCommandSetPenColor(t, Color.MAGENTA, Color.CYAN));
		
		t.walk(0.5);
		exp.add(new TurtleCommandWalk(t, 0.5));
		
		List<TurtleCommand> got = t.getCommands();
		TestTurtle.testCommands(exp, got);
	}
	
	
	@Test
	public void test11_repeatCommands() {
		Turtle t = new Turtle();
		Point2 expPos = new Point2(t.leakPosition());
		Direction2 expDir = new Direction2(t.leakDirection().getAngle());
		Pen expPen = new Pen(t.leakPen());
		List<TurtleCommand> expCommands = new ArrayList<>();
		
		t.repeatCommands(10);
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test12_repeatCommands() {
		Turtle t = new Turtle();
		Point2 expPos = new Point2(t.leakPosition());
		Direction2 expDir = new Direction2(5.0);
		Pen expPen = new Pen(t.leakPen());
		List<TurtleCommand> expCommands = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			expCommands.add(new TurtleCommandTurnLeft(t, 1.0));
		}
		
		t.turnLeft(1.0);
		t.repeatCommands(4);
		
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test13_repeatCommands() {
		Turtle t = new Turtle(0.5, 0.5, 180, Color.BLUE);
		Point2 expPos = new Point2(t.leakPosition());
		Direction2 expDir = new Direction2(130.0);
		Pen expPen = new Pen(t.leakPen());
		List<TurtleCommand> expCommands = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			expCommands.add(new TurtleCommandTurnRight(t, 10.0));
		}
		
		t.turnRight(10.0);
		t.repeatCommands(4);
		
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test14_repeatCommands() {
		Turtle t = new Turtle(0.5, 0.5, 180, Color.BLUE);
		Point2 expPos = new Point2(t.leakPosition());
		Direction2 expDir = new Direction2(10.0);
		Pen expPen = new Pen(t.leakPen());
		List<TurtleCommand> expCommands = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			expCommands.add(new TurtleCommandTurnTo(t, 10.0));
		}
		
		t.turnTo(10.0);
		t.repeatCommands(4);
		
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test15_repeatCommands() {
		Turtle t = new Turtle(0.5, 0.9, 270.0, Color.BLUE);
		Point2 expPos = new Point2(0.5, 0.4);
		Direction2 expDir = new Direction2(270.0);
		Pen expPen = new Pen(t.leakPen());
		List<TurtleCommand> expCommands = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			expCommands.add(new TurtleCommandWalk(t, 0.1));
		}
		
		t.walk(0.1);
		t.repeatCommands(4);
		
		assertTrue(expPos.similarTo(t.leakPosition(), 1e-9));
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test16_repeatCommands() {
		Turtle t = new Turtle(0.5, 0.9, 270.0, Color.BLUE);
		Point2 expPos = new Point2(0.5, 0.9);
		Direction2 expDir = new Direction2(270.0);
		Pen expPen = new Pen(t.leakPen());
		expPen.off();
		List<TurtleCommand> expCommands = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			expCommands.add(new TurtleCommandPenOff(t));
		}
		
		t.penOff();
		t.repeatCommands(4);
		
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test17_repeatCommands() {
		Turtle t = new Turtle(0.5, 0.9, 270.0, Color.BLUE);
		Point2 expPos = new Point2(0.5, 0.9);
		Direction2 expDir = new Direction2(270.0);
		Pen expPen = new Pen(t.leakPen());
		expPen.on();
		List<TurtleCommand> expCommands = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			expCommands.add(new TurtleCommandPenOn(t));
		}
		
		t.penOn();
		t.repeatCommands(4);
		
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test18_repeatCommands() {
		Turtle t = new Turtle(0.5, 0.9, 270.0, Color.BLUE);
		Point2 expPos = new Point2(0.5, 0.9);
		Direction2 expDir = new Direction2(270.0);
		Pen expPen = new Pen(t.leakPen());
		expPen.setColor(Color.GREEN);
		List<TurtleCommand> expCommands = new ArrayList<>();
		expCommands.add(new TurtleCommandSetPenColor(t, Color.BLUE, Color.GREEN));
		expCommands.add(new TurtleCommandSetPenColor(t, Color.GREEN, Color.GREEN));
				
		t.setPenColor(Color.GREEN);
		t.setPenColor(Color.GREEN);
		
		assertEquals(expPos, t.leakPosition());
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		TestTurtle.testCommands(expCommands, t.leakCommands());
	}
	
	@Test
	public void test19_doCommands() {
		Turtle t = new Turtle(0.1, 0.1, 0.0, Color.MAGENTA);
		List<TurtleCommand> exp = new ArrayList<TurtleCommand>();
		
		exp.add(new TurtleCommandWalk(t, 0.2));
		exp.add(new TurtleCommandTurnLeft(t, 90.0));
		exp.add(new TurtleCommandWalk(t, 0.2));
		exp.add(new TurtleCommandTurnTo(t, 180.0));
		exp.add(new TurtleCommandPenOn(t));
		exp.add(new TurtleCommandSetPenColor(t, Color.MAGENTA, Color.CYAN));
		exp.add(new TurtleCommandWalk(t, 0.2));
		exp.add(new TurtleCommandTurnLeft(t, 90.0));
		exp.add(new TurtleCommandWalk(t, 0.2));
		exp.add(new TurtleCommandTurnRight(t, 90.0));
		exp.add(new TurtleCommandPenOff(t));
		
		Point2 expPos = new Point2(0.1, 0.1);
		Direction2 expDir = new Direction2(180.0);
		Pen expPen = new Pen(Color.CYAN);
		expPen.off();

		t.doCommands(new ArrayList<>(exp));

		assertTrue(expPos.similarTo(t.leakPosition(), 1e-6));
		assertEquals(expDir, t.leakDirection());
		assertEquals(expPen, t.leakPen());
		List<TurtleCommand> got = t.getCommands();
		TestTurtle.testCommands(exp, got);
	}
	
	private static void testCommands(List<TurtleCommand> exp, List<TurtleCommand> got) {
		assertEquals("getCommands returned a list with the wrong size", 
				exp.size(), got.size());
		for (int i = 0; i < exp.size(); i++) {
			String err = String.format("getCommands command %d, expected %s, got %s",
					i,
					TestTurtle.toString(exp.get(i)), 
					TestTurtle.toString(got.get(i)));
			assertTrue(err,
					TestTurtle.equals(exp.get(i), got.get(i)));
		}
	}
	
	private static boolean equals(TurtleCommand a, TurtleCommand b) {
		if (a.getClass() != b.getClass()) {
			return false;
		}
		if (a.getTurtle() != b.getTurtle()) {
			return false;
		}
		if (a instanceof TurtleCommandTurnLeft) {
			TurtleCommandTurnLeft x = (TurtleCommandTurnLeft) a;
			TurtleCommandTurnLeft y = (TurtleCommandTurnLeft) b;
			return x.getDelta() == y.getDelta();
		}
		else if (a instanceof TurtleCommandTurnRight) {
			TurtleCommandTurnRight x = (TurtleCommandTurnRight) a;
			TurtleCommandTurnRight y = (TurtleCommandTurnRight) b;
			return x.getDelta() == y.getDelta();
		}
		else if (a instanceof TurtleCommandTurnTo) {
			TurtleCommandTurnTo x = (TurtleCommandTurnTo) a;
			TurtleCommandTurnTo y = (TurtleCommandTurnTo) b;
			return x.getAngle() == y.getAngle();
		}
		else if (a instanceof TurtleCommandWalk) {
			TurtleCommandWalk x = (TurtleCommandWalk) a;
			TurtleCommandWalk y = (TurtleCommandWalk) b;
			return x.getDistance() == y.getDistance();
		}
		else if (a instanceof TurtleCommandPenOff) {
			return true;
		}
		else if (a instanceof TurtleCommandPenOn) {
			return true;
		}
		else if (a instanceof TurtleCommandSetPenColor) {
			TurtleCommandSetPenColor x = (TurtleCommandSetPenColor) a;
			TurtleCommandSetPenColor y = (TurtleCommandSetPenColor) b;
			return x.getOldColor().equals(y.getOldColor()) &&
					x.getNewColor().equals(y.getNewColor());
		}
		return false;
	}
	
	private static String toString(TurtleCommand c) {
		
		if (c instanceof TurtleCommandTurnLeft) {
			TurtleCommandTurnLeft x = (TurtleCommandTurnLeft) c;
			return String.format("turn left %s", x.getDelta());
		}
		else if (c instanceof TurtleCommandTurnRight) {
			TurtleCommandTurnRight x = (TurtleCommandTurnRight) c;
			return String.format("turn right %s", x.getDelta());
		}
		else if (c instanceof TurtleCommandTurnTo) {
			TurtleCommandTurnTo x = (TurtleCommandTurnTo) c;
			return String.format("turn to %s", x.getAngle());
		}
		else if (c instanceof TurtleCommandWalk) {
			TurtleCommandWalk x = (TurtleCommandWalk) c;
			return String.format("walk %s", x.getDistance());
		}
		else if (c instanceof TurtleCommandPenOff) {
			return "pen off";
		}
		else if (c instanceof TurtleCommandPenOn) {
			return "pen on";
		}
		else if (c instanceof TurtleCommandSetPenColor) {
			TurtleCommandSetPenColor x = (TurtleCommandSetPenColor) c;
			return String.format("set pen color from %s to %s", x.getOldColor(), x.getNewColor());
		}
		return "";
	}
}
