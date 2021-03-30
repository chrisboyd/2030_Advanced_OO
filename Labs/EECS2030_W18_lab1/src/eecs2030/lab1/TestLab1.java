package eecs2030.lab1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLab1 {

	private static Random rng = new Random();

	@Test
	public void test01_maxInt() {
		assertEquals(Integer.MAX_VALUE, Lab1.maxInt());
	}

	@Test
	public void test02_minDouble() {
		assertEquals(0, Double.compare(Double.MIN_VALUE, Lab1.minDouble()));
	}

	@Test
	public void test24_numQuarters() {
		for (int quarters = 0; quarters < 100; quarters++) {
			int totalCents = 25 * quarters;
			for (int cents = 0; cents < 25; cents++) {
				String error = String.format("numQuarters(%d) should return %d\n", totalCents, quarters);
				assertEquals(error, quarters, Lab1.numQuarters(totalCents));
				totalCents++;
			}
		}
	}

	@Test
	public void test04_wrapAngle() {
		for (int revolutions = 0; revolutions <= 10; revolutions++) {
			// positive angles
			int totalAngle = revolutions * 360;
			for (int deg = 0; deg <= 359; deg++) {
				String error = String.format("wrapAngle(%d) should return %d\n", totalAngle, deg);
				assertEquals(error, deg, Lab1.wrapAngle(totalAngle));
				totalAngle++;
			}
		}

	}
	
	@Test
	public void test05_wrapAngle() {
		for (int revolutions = 0; revolutions <= 10; revolutions++) {
			// negative angles
			int totalAngle = -revolutions * 360;
			for (int deg = 0; deg >= -359; deg--) {
				String error = String.format("wrapAngle(%d) should return %d\n", totalAngle, deg);
				assertEquals(error, deg, Lab1.wrapAngle(totalAngle));
				totalAngle--;
			}
		}

	}

	@Test
	public void test06_avg() {
		rng.setSeed(6);
		for (int i = 0; i < 100; i++) {
			int a = rng.nextInt();
			int b = rng.nextInt();
			double sum = 0.0 + a + b;
			String error = String.format("avg(%d, %d) failed", a, b);
			assertEquals(error, sum, 2.0 * Lab1.avg(a, b), Math.ulp(sum));
		}
	}

	@Test
	public void test07_normal() {
		final double[] x = { -10.0, -5.0, -3.0, -1.0, 0.0, 1.0, 3.0, 5.0, 10.0 };
		final double[] exp = { 7.6946e-23, 1.48672e-06, 0.00443185, 0.241971, 0.398942, 0.241971, 0.00443185,
				1.48672e-06, 7.6946e-23 };
		for (int i = 0; i < x.length; i++) {
			String error = String.format("normal(%s) failed\n", x[i]);
			assertEquals(error, exp[i], Lab1.normal(x[i]), 1e-6);
		}
	}

	@Test
	public void test08_isEven() {
		rng.setSeed(8);
		for (int i = 0; i < 100; i++) {
			int even = 2 * rng.nextInt(Integer.MAX_VALUE / 2);
			int odd = even + 1;
			String error = String.format("isEven(%d) failed (returned true)", odd);
			assertFalse(error, Lab1.isEven(odd));

			error = String.format("isEven(%d) failed (returned false)", even);
			assertTrue(error, Lab1.isEven(even));
		}
	}

	@Test
	public void test09_isInsideUnitCircle() {
		rng.setSeed(9);
		// random points inside the unit circle
		for (int i = 0; i < 100; i++) {
			double radius = rng.nextDouble(); // less than 1
			double radians = 2.0 * Math.PI * i / 100;
			double x = radius * Math.cos(radians);
			double y = radius * Math.sin(radians);
			String error = String.format("isInsideUnitCircle(%s, %s) failed (returned false)", x, y);
			assertTrue(error, Lab1.isInsideUnitCircle(x, y));
		}
		// random points outside the unit circle
		for (int i = 0; i < 100; i++) {
			double radius = 1.0 + rng.nextDouble() + Math.ulp(1.0); // greater
																	// than 1
			double radians = 2.0 * Math.PI * i / 100;
			double x = radius * Math.cos(radians);
			double y = radius * Math.sin(radians);
			String error = String.format("isInsideUnitCircle(%s, %s) failed (returned true)", x, y);
			assertFalse(error, Lab1.isInsideUnitCircle(x, y));
		}
		// some points exactly on the unit circle
		final double[] X = { 1.0, 0.0, -1.0, 0.0 };
		final double[] Y = { 0.0, 1.0, 0.0, -1.0 };
		for (int i = 0; i < X.length; i++) {
			double x = X[i];
			double y = Y[i];
			String error = String.format("isInsideUnitCircle(%s, %s) failed (returned true)", x, y);
			assertFalse(error, Lab1.isInsideUnitCircle(x, y));
		}
	}

	@Test
	public void test10_isGradeA() {
		final double[] notA = { 0.0, 79.0, Math.nextDown(80.0), 90.0, 100.0 };
		final double[] isA = { 80.0, 85.0, Math.nextDown(90.0) };

		for (double percent : notA) {
			String error = String.format("isGradeA(%s) failed (returned true)", percent);
			assertFalse(error, Lab1.isGradeA(percent));
		}
		for (double percent : isA) {
			String error = String.format("isGradeA(%s) failed (returned false)", percent);
			assertTrue(error, Lab1.isGradeA(percent));
		}
	}

	@Test
	public void test11_isGradeOutOfRange() {
		final double[] notInRange = { -10.0, Math.nextDown(0.0), Math.nextUp(100.0), 105.0 };
		final double[] isInRange = { 0.0, 50.0, 100.0 };

		for (double percent : isInRange) {
			String error = String.format("isGradeOutOfRange(%s) failed (returned true)", percent);
			assertFalse(error, Lab1.isGradeOutOfRange(percent));
		}
		for (double percent : notInRange) {
			String error = String.format("isGradeOutOfRange(%s) failed (returned false)", percent);
			assertTrue(error, Lab1.isGradeOutOfRange(percent));
		}
	}

	@Test
	public void test12_isLeapYear() {
		final int[] notLeapYr = { 1582, 1700, 1800, 1900, 2001 };
		final int[] isLeapYr = { 1592, 1600, 1904, 2000, 2016 };

		for (int yr : isLeapYr) {
			String error = String.format("isLeapYear(%s) failed (returned false)", yr);
			assertTrue(error, Lab1.isLeapYear(yr));
		}

		for (int yr : notLeapYr) {
			String error = String.format("isLeapYear(%s) failed (returned true)", yr);
			assertFalse(error, Lab1.isLeapYear(yr));
		}
	}

	@Test
	public void test13_isLeapYear() {
		try {
			Lab1.isLeapYear(1581);
			fail("isLeapYear(1581) should have thrown an exception");
		} catch (IllegalArgumentException x) {
			// ok
		} catch (Exception ex) {
			fail("isLeapYear threw the wrong type of exception");
		}
	}

	@Test
	public void test14_contains() {
		rng.setSeed(13);
		for (int i = 0; i < 100; i++) {
			int min = rng.nextInt(100) - 50;
			int max = min + i;
			this.testOutsideRange(min, max);
			this.testInsideRange(min, max);
		}
	}

	private void testOutsideRange(int min, int max) {
		final double[] X = { Double.NEGATIVE_INFINITY, min - 100.0, min - 1.0, Math.nextAfter(min, min - 1.0), min, max,
				Math.nextAfter(max, max + 1.0), max + 1.0, max + 100.0, Double.POSITIVE_INFINITY };
		Range r = new Range(min, max);
		for (double x : X) {
			String error = String.format("contains(%s, r) failed\n" + "where r = %s\n", x, r);
			assertEquals(error, 0, Lab1.contains(x, r));
		}
	}

	private void testInsideRange(int min, int max) {
		if (min == max) {
			return;
		}
		double xStart = Math.nextAfter(min, min + 1.0);
		double xEnd = Math.nextAfter(max, max - 1.0);
		double step = (xEnd - xStart) / 10;
		double[] X = new double[10];
		X[0] = xStart;
		X[9] = xEnd;
		for (int i = 1; i < 9; i++) {
			X[i] = X[i - 1] + step;
		}
		Range r = new Range(min, max);
		for (double x : X) {
			String error = String.format("contains(%s, r) failed\n" + "where r = %s\n", x, r);
			assertEquals(error, 1, Lab1.contains(x, r));
		}
	}

	@Test
	public void test15_compareTo() {
		rng.setSeed(14);
		for (int i = 0; i < 100; i++) {
			int min = rng.nextInt(100) - 50;
			Range skinny = new Range(min, min + 1);
			min = rng.nextInt(100) - 50;
			Range skinny2 = new Range(min, min + 1);
			min = rng.nextInt(100) - 50;
			Range wide = new Range(min, min + 2);
			String error = String.format("compareTo(r1, r2) failed for r1 = %s, r2 = %s", skinny, skinny);
			assertEquals(error, 0, Lab1.compareTo(skinny, skinny));

			error = String.format("compareTo(r1, r2) failed for r1 = %s, r2 = %s", skinny, skinny2);
			assertEquals(error, 0, Lab1.compareTo(skinny, skinny2));

			error = String.format("compareTo(r1, r2) failed for r1 = %s, r2 = %s", skinny, wide);
			assertEquals(error, -1, Lab1.compareTo(skinny, wide));

			error = String.format("compareTo(r1, r2) failed for r1 = %s, r2 = %s", wide, skinny);
			assertEquals(error, 1, Lab1.compareTo(wide, skinny));
		}
	}

	@Test
	public void test16_getCourseName() {
		assertEquals("getCourseName() failed", "Advanced Object Oriented Programming", Lab1.getCourseName());
	}

	@Test
	public void test17_toString() {
		String exp = "minimum: -10.00000001, maximum: 9253.353156731684";
		Range r = new Range(-10.00000001, 9253.353156731684);
		String error = String.format("toString(r) failed for r = %s", r.toString());
		assertEquals(error, exp, Lab1.toString(r));
	}

	private static char randChar() {
		return (char) ('A' + rng.nextInt(26));
	}
	
	@Test
	public void test18_charFromEnd() {
		StringBuilder s = new StringBuilder();
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			char c = TestLab1.randChar();
			s.append(c);
			t.insert(0, c);
		}
		String str = s.toString();
		for (int i = 0; i < 10; i++) {
			char exp = t.charAt(i);
			char got = Lab1.charFromEnd(str, i);
			String error = String.format("charFromEnd(%s, %s) failed\nexpected: %c but was: %c", 
					str, i, exp, got);
			assertTrue(error, exp == got);
		}
	}
	

	@Test
	public void test19_sort2() {
		ArrayList<Integer> t = new ArrayList<>();
		String error = "sort2(t) failed to throw an IllegalArgumentException";
		try {
			Lab1.sort2(t);
			fail(error);
		} catch (IllegalArgumentException x) {
			// do nothing
		} catch (Exception x) {
			fail("sort2(t) threw the wrong kind of exception");
		}

		t.add(1);
		try {
			Lab1.sort2(t);
			fail(error);
		} catch (IllegalArgumentException x) {
			// do nothing
		} catch (Exception x) {
			fail("sort2(t) threw the wrong kind of exception" + x);
		}

		t.add(2);
		t.add(3);
		try {
			Lab1.sort2(t);
			fail(error);
		} catch (IllegalArgumentException x) {
			// do nothing
		} catch (Exception x) {
			fail("sort2(t) threw the wrong kind of exception");
		}
	}

	@Test
	public void test20_sort2() {
		ArrayList<Integer> t = new ArrayList<>();
		t.add(1000);
		t.add(-1000);

		ArrayList<Integer> u = new ArrayList<>(t);
		String error = String.format("sort2(t) failed for t = %s", u.toString());
		Lab1.sort2(u);
		assertEquals(error, t, u);

		u.clear();
		u.add(t.get(1));
		u.add(t.get(0));
		error = String.format("sort2(t) failed for t = %s", u.toString());
		Lab1.sort2(u);
		assertEquals(error, t, u);
	}

	@Test
	public void test21_frequency() {
		List<String> t = new ArrayList<>();
		String target = "hi";
		String error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 0, Lab1.frequency(t, target));
	}

	@Test
	public void test22_frequency() {
		List<String> t = Arrays.asList("hi");
		String target = "hi";
		String error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 1, Lab1.frequency(t, target));
		
		target = "bye";
		error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 0, Lab1.frequency(t, target));
	}
	
	@Test
	public void test23_frequency() {
		List<String> t = Arrays.asList("hi", "bye", "bye", "hi", "bye", "bye", "fly");
		String target = "hi";
		String error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 2, Lab1.frequency(t, target));
		
		target = "bye";
		error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 4, Lab1.frequency(t, target));
		
		target = "fly";
		error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 1, Lab1.frequency(t, target));
		
		target = "cry";
		error = String.format("frequency(%s, %s) failed\n", 
				t.toString(), target);
		assertEquals(error, 0, Lab1.frequency(t, target));
	}
	
	@Test
	public void test24a_repair() {
		List<Integer> t = new ArrayList<>();
		Lab1.repair(t);
		assertTrue("repair([]) failed",
				t.isEmpty());
	}
	
	@Test
	public void test24b_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(3));
		List<Integer> exp = new ArrayList<>(t);
		Lab1.repair(t);
		assertEquals("repair([3]) failed",
				exp, t);
	}
	
	@Test
	public void test24c_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(3, 4));
		List<Integer> exp = new ArrayList<>(t);
		Lab1.repair(t);
		assertEquals("repair([3, 4]) failed",
				exp, t);
	}
	
	@Test
	public void test24d_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(4, 3));
		List<Integer> exp = new ArrayList<>(Arrays.asList(3, 4));
		Lab1.repair(t);
		assertEquals("repair([4, 3]) failed",
				exp, t);
	}
	
	@Test
	public void test24e_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(0, 2, 1));
		List<Integer> exp = new ArrayList<>(Arrays.asList(0, 1, 2));
		Lab1.repair(t);
		assertEquals("repair([0, 2, 1]) failed",
				exp, t);
	}
	
	@Test
	public void test24f_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(0, 2, 1, 3));
		List<Integer> exp = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
		Lab1.repair(t);
		assertEquals("repair([0, 2, 1, 3]) failed",
				exp, t);
	}
	
	@Test
	public void test24g_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(0, 2, 1, 4, 3));
		List<Integer> exp = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
		Lab1.repair(t);
		assertEquals("repair([0, 2, 1, 4, 3]) failed",
				exp, t);
	}
	
	@Test
	public void test24h_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(0, 1, 3, 2, 4));
		List<Integer> exp = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
		Lab1.repair(t);
		assertEquals("repair([0, 1, 3, 2, 4]) failed",
				exp, t);
	}
	
	@Test
	public void test24i_repair() {
		List<Integer> t = new ArrayList<>(Arrays.asList(0, 5, 10, 9, 20, 22, 37, 150, 99, 200));
		List<Integer> exp = new ArrayList<>(Arrays.asList(0, 5, 9, 10, 20, 22, 37, 99, 150, 200));
		Lab1.repair(t);
		assertEquals("repair([0, 1, 3, 2, 4]) failed",
				exp, t);
	}
	
	@Test
	public void test25_isPythagTriple() {
		rng.setSeed(8);
		int p[][] = {{3,4,5}, {4,3,5}, {5,4,3}, {778344504,227017147,810775525}};
		for (int i = 0; i < 4; i++) {
			int a = p[i][0];
			int b = p[i][1];
			int c = p[i][2];
			String error = String.format("isPythagTriple(%d, %d, %d) failed (returned false)", a, b, c);
			assertTrue(error, Lab1.isPythagTriple(a, b, c));

			error = String.format("isPythagTriple(%d, %d, %d) failed (returned true)", a, b, a + b);
			assertFalse(error, Lab1.isPythagTriple(a, b, a + b));
		}
	}
}
