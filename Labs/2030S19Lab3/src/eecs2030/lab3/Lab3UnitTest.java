package eecs2030.lab3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class Lab3UnitTest {
	
	private Random rnd = new Random();

	@Test
	void testC01() {
		int n = Circle.getNumCircles();
		assertEquals(0, n, "Incorrect number of Circle objects");
	}
	@Test
	void testC02() {
		new Circle(10);
		int n = Circle.getNumCircles();
		assertEquals(1, n, "Incorrect number of Circle objects");
	}
	
	@Test
	void testC03() {
		int k = rnd.nextInt(2000);
		for(int i=1; i < k; i++) {
			new Circle(10);
		}
		
		int n = Circle.getNumCircles();
		assertEquals(k, n, "Incorrect number of Circle objects");
	}

	@Test
	void testC04() {
		Circle c = new Circle(10.0d);
		assertEquals(10.0, c.getRadius(), "Incorrect circle radius, verify the constructor and getRadius method");
	}

	@Test
	void testC05() {
		assertThrows(IllegalArgumentException.class, ()->new Circle(-1), "Expected to throw an exception for negative circle radius");
	}

	@Test
	void testC06() {
		assertThrows(IllegalArgumentException.class, ()->new Circle(0), "Expected to throw an exception for zero circle radius");
	}
	
	@Test
	void testC07() {
		int k = rnd.nextInt(2000);
		for(int i=1; i < k; i++) {
			double x = rnd.nextDouble()*10;
			Circle c = new Circle(x);
			assertEquals(x, c.getRadius(), "Incorrect circle radius, verify the constructor and getRadius method");
		}
	}

	@Test
	void testC08() {
		Circle c = new Circle(10.0d);
		assertEquals(100.0*Math.PI, c.getArea(), 1e-6, "Incorrect circle [radius=10.0] area, verify Circle.getArea() method");
	}

	@Test
	void testC09() {
		Circle c = new Circle(10.0d);
		assertEquals(20.0*Math.PI, c.getCircumference(), 1e-6, "Incorrect circle [radius=10.0] circumference, verify Circle.getCircumference() method");
	}
	
	@Test
	void testC10() {
		Circle c1 = new Circle(10.0d);
		assertEquals(true, c1.equals(c1), "Circle [radius=10.0] should be equal to itself, verify Circle.equals(...) method");
	}
	
	@Test
	void testC11() {
		Circle c1 = new Circle(10.0d);
		assertEquals(false, c1.equals(null), "Circle [radius=10.0] should not be equal to null, verify Circle.equals(...) method");
	}

	@Test
	void testC12() {
		Circle c1 = new Circle(10.0d);
		assertEquals(false, c1.equals(new Object()), "Circle [radius=10.0] should not be equal to non-Circle objects, verify Circle.equals(...) method");
	}

	@Test
	void testC13() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(10.0d);
		assertEquals(true, c1.equals(c2), c1+" should be equal to "+c2+", verify Circle.equals(...) method");
	}

	@Test
	void testC14() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(10.0d+9.99e-7);
		assertEquals(true, c1.equals(c2), "Circle c1 [radius=10.0] should be equal to c2[radius=10.0+tol], verify Circle.equals(...) method");
	}
	
	@Test
	void testC15() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(11.0d);
		assertEquals(false, c1.equals(c2), c1+" should not be equal to " + c2 + ", verify Circle.equals(...) method");
	}
	
	@Test
	void testC16() {
		Circle c1 = new Circle(10.0d);
		assertThrows(IllegalArgumentException.class, ()->c1.compareTo(null), "Method compareTo is expected to throw an exception for null arguments");
	}

	@Test
	void testC17() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(11.0d);
		assertEquals(-1, c1.compareTo(c2), "comparing " + c1 + " and " + c2 + ", verify Circle.compareTo(...) method");
	}
	@Test
	void testC18() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(11.0d);
		assertEquals(+1, c2.compareTo(c1), "comparing " + c2 + " and " + c1 + ", verify Circle.compareTo(...) method");
	}
	@Test
	void testC19() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(10.0d);
		assertEquals(0, c2.compareTo(c1), "comparing " + c2 + " and " + c1 + ", verify Circle.compareTo(...) method");
	}
	@Test
	void testC20() {
		Circle c1 = new Circle(10.0d);
		Circle c2 = new Circle(10.0d+1.0e-8);
		assertEquals(0, c2.compareTo(c1), "comparing " + c2 + " and " + c1 + ", verify Circle.compareTo(...) method");
	}
	@Test
	void testS01() {
		Circle c1 = new Circle(10.0d);
		double d = c1.getArea();
		assertEquals(d, ShapeUtil.getArea(c1), "ShapeUtil area works");		
	}
}
