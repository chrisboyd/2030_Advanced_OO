package eecs2030.lab2;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomialTest {

	@Test
	public void test01_ctr_noarg() {
		Polynomial p = new Polynomial();
		assertTrue(p.test_getDegree() == 0, "Degree must be zero!");
		double[] a = p.test_getCf();
		assertTrue(a.length == 1, "There should be one coefficient!");
		assertTrue(Double.doubleToLongBits(a[0]) == 0, "Coefficient must be zero!");
	}

	@Test
	public void test02_ctr_arg() {
		double a = 2.0;
		int i = 3;
		Polynomial p = new Polynomial(a, i);
		assertTrue(p.test_getDegree() == i, "Degree must be " + i);
		double[] cf = p.test_getCf();
		assertTrue(cf.length == i + 1, "There should be " + (i + 1) + " coefficients");
		assertTrue(Double.doubleToLongBits(cf[i]) == Double.doubleToLongBits(a), "Coefficient " + i + " must be " + a);
		assertThrows(IllegalArgumentException.class, () -> {
			new Polynomial(a, -1);
		}, "IllegalArgumentException expected");
	}

	@Test
	public void test03_ctr_arr() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Polynomial(new double[0]);
		}, "IllegalArgumentException expected");
		double[] a = new double[] { 1, 3, 2, 6 };
		Polynomial p = new Polynomial(a);
		assertTrue(p.test_getDegree() == 3, "Degree must be 3");
		double[] cf = p.test_getCf();
		assertTrue(cf.length == 4, "There should be 4 coefficients");
		for (int i = 3; i >= 0; i--)
			assertTrue(Double.doubleToLongBits(cf[i]) == Double.doubleToLongBits(a[i]),
					"Coefficient " + i + " must be " + a[i]);
	}

	@Test
	public void test04_equals() {
		double[] a = new double[] { 1, 3, 2, 6 };
		double[] b = new double[] { 1, 3, 2, 6 };
		Polynomial p1 = new Polynomial(a);
		assertTrue(p1.equals(p1), "Polynomials P1 must be equal to itself");
		Polynomial p2 = new Polynomial(b);
		assertTrue(p1.equals(p2), "Polynomials P1 and P2 must be equal");
		p2 = new Polynomial(3, 3);
		assertFalse(p1.equals(p2), "Polynomials P1 and P2 must not be equal");
		assertFalse(p1.equals(new Polynomial()), "Polynomials P1 and P2 must not be equal");
		assertFalse(p1.equals(null), "Polynomials P1 must not be equal to null");
		assertFalse(p1.equals(new Object()), "Polynomials P1 must not be equal to non-polynomial object");
	}

	@Test
	public void test05_getDegree() {
		double[] a = new double[] { 1, 3, 2, 6 };
		Polynomial p1 = new Polynomial(a);
		int n = p1.getDegree();
		assertTrue(n == 3, "Wrong polynomial degree, expected 3 but " + n + " was returned");
	}

	@Test
	public void test06_hashCode() {
		double[] a = new double[] { 1, 3, 2, 6 };
		double[] b = new double[] { 1, 3, 2, 6 };
		Polynomial p1 = new Polynomial(a);
		Polynomial p2 = new Polynomial(b);
		assertTrue(p1.hashCode() == p1.hashCode(), "hashcode is inconsistant");
		assertTrue(p1.hashCode() == p2.hashCode(), "hashcodes are not equal");
		a = p2.test_getCf();
		a[0] = 3;
		assertFalse(p1.hashCode() == p2.hashCode(), "hashcodes shall not be equal");
	}

	@Test
	public void test07_compareTo() {
		double[] a = new double[] { 1, 3, 2, 6 };
		double[] b = new double[] { 1, 3, 2, 6 };
		Polynomial p1 = new Polynomial(a);
		assertTrue(p1.compareTo(p1) == 0, "compareTo: Polynomials P1 must be equal to itself");
		Polynomial p2 = new Polynomial(b);
		assertTrue(p1.compareTo(p2) == 0, "compareTo: Polynomials P1 and P2 must be equal");
		a = p2.test_getCf();
		a[0] = a[0] * 2;
		assertFalse(p1.compareTo(p2) > 0, "compareTo: Polynomial P2 must be greater than P1");
		a[0] = -1;
		assertTrue(p1.compareTo(p2) > 0, "compareTo: Polynomial P1 must be greater than P2");
		assertFalse(p1.compareTo(new Polynomial(3, 3)) < 0, "compareTo: Polynomial P2 must be greater than P1");
		assertTrue(p1.compareTo(new Polynomial(3, 2)) > 0, "compareTo: Polynomial P1 must be greater than P2");
		assertTrue(p1.compareTo(new Polynomial()) > 0, "compareTo: Polynomials P1 and P2 must not be equal");
		assertTrue(p1.compareTo(new Polynomial(3, 4)) < 0, "compareTo: Polynomial P2 must be greater than P1");
	}

	@Test
	public void test08_evaluate() {
		double[] a = new double[] { 1, 3, 2, 6 };
		Polynomial p1 = new Polynomial(a);
		double x = 2;
		double y = p1.evaluate(x);
		double p = 1 + x * (3 + x * (2 + 6 * x));
		assertTrue(y == p, "Wrong polynomial function value returned in the evaluate method\n" + "Expected: " + p
				+ " Returned: " + y);
	}

	@Test
	public void test09_plus() {
		Polynomial p = new Polynomial(new double[] { 1, 3, 2, 6 });
		Polynomial q = new Polynomial(new double[] { 0, 1, -2, -3 });
		Polynomial w_ret = p.plus(q);
		Polynomial w_exp = new Polynomial(new double[] { 1, 4, 0, 3 });
		assertTrue(w_exp.equals(w_ret), "Wrong polynomial addition");
	}

	@Test
	public void test10_minus() {
		Polynomial p = new Polynomial(new double[] { 1, 3, 2, 6 });
		Polynomial q = new Polynomial(new double[] { 0, 1, -2, -3 });
		Polynomial w_ret = p.minus(q);
		Polynomial w_exp = new Polynomial(new double[] { 1, 2, 4, 9 });
		assertTrue(w_exp.equals(w_ret), "Wrong polynomial subtraction");
	}

	@Test
	public void test11_times() {
		Polynomial p = new Polynomial(new double[] { 1, 3, 2, 6 });
		Polynomial q = new Polynomial(new double[] { 0, 1, -2, -3 });
		Polynomial w_ret = p.times(q);
		Polynomial w_exp = new Polynomial(new double[] { 0, 1, 1, -7, -7, -18, -18 });
		assertTrue(w_exp.equals(w_ret), "Wrong polynomial multiplication");
	}

	@Test
	public void test12_compose() {
		Polynomial p = new Polynomial(new double[] { 1, 3, 2, 6 });
		Polynomial q = new Polynomial(new double[] { 0, 1, -2, -3 });
		Polynomial w_ret = p.compose(q);
		Polynomial w_exp = new Polynomial(new double[] { 1, 3, -4, -11, -40, 42, 186, -54, -324, -162 });
		assertTrue(w_exp.equals(w_ret), "Wrong polynomial composition");
	}

	@Test
	public void test13_derive() {
		Polynomial p = new Polynomial(new double[] { 1, 3, 2, 6 });
		Polynomial w_ret = p.derive();
		Polynomial w_exp = new Polynomial(new double[] { 3, 4, 18});
		assertTrue(w_exp.equals(w_ret), "Wrong polynomial derivation");
	}

	@Test
	public void test14_toString() {
		String[] s_exp = new String[] { "0.0", "1.0", "1.0x", "1.0x - 1.0", "2.0x^2", "2.0x^2 + 1.0x", "2.0x^2 - 1.0",
				"2.0x^2 + 1.0x + 1.213E-4" };
		Polynomial[] p = new Polynomial[] { new Polynomial(), new Polynomial(1, 0), new Polynomial(1, 1),
				new Polynomial(new double[] { -1, 1 }), new Polynomial(new double[] { 0, 0, 2 }),
				new Polynomial(new double[] { 0, 1, 2 }), new Polynomial(new double[] { -1, 0, 2 }),
				new Polynomial(new double[] { 0.0001213, 1, 2 }) };
		for (int i = 0; i < s_exp.length; i++) {
			String msg = "Expected " + s_exp[i] + " but " + p[i].toString() + " was returned";
			assertEquals(msg, s_exp[i], (p[i]).toString());
		}
	}
}
