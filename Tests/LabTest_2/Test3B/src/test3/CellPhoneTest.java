package test3;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CellPhoneTest {
	
	private List<CellNumber> getCallLog() {
		List<CellNumber> c = new ArrayList<>();
		c.add(new CellNumber(416, 736, 2100));
		c.add(new CellNumber(416, 736, 9999));
		c.add(new CellNumber(416, 967, 1111));
		c.add(new CellNumber(416, 736, 9999));
		c.add(new CellNumber(416, 736, 9999));
		c.add(new CellNumber(416, 736, 9999));
		c.add(new CellNumber(416, 736, 9999));
		c.add(new CellNumber(416, 967, 1111));
		c.add(new CellNumber(416, 967, 1111));
		c.add(new CellNumber(416, 967, 1111));
		return c;
	}

	private List<CellNumber> getCallLog(CellPhone p) {
		Object obj = null;
		try {
			Field callLog = CellPhone.class.getDeclaredField("callLog");
			callLog.setAccessible(true);
			obj = callLog.get(p);
			assertNotNull("this.callLog is null",
					obj);
			if (!(obj instanceof List<?>)) {
				fail("this.callLog has the wrong type!");
			}
		} catch (Exception x) {
			fail("exception occurred trying to get this.callLog!");
			System.out.println(x);
		}
		return (List<CellNumber>) obj;
	}
	
	private CellNumber getNumber(CellPhone p) {
		Object obj = null;
		try {
			Field number = CellPhone.class.getDeclaredField("number");
			number.setAccessible(true);
			obj = number.get(p);
			assertNotNull("this.number is null", 
					obj);
			if (!(obj instanceof CellNumber)) {
				fail("this.number has the wrong type!\n");
			}
			
		} catch (Exception x) {
			fail("exception occurred trying to get this.number!");
			System.out.println(x);
		}
		return (CellNumber) obj;
	}

	@Test
	public void test01_ctor() {
		CellPhone p = new CellPhone(new CellNumber(416, 736, 2100));
		CellNumber n = this.getNumber(p);
		assertEquals("CellPhone(CellNumber) failed, wrong cell number\n",
				new CellNumber(416, 736, 2100), n);
		List<CellNumber> c = this.getCallLog(p);
		assertEquals("CellPhone(CellNumber) failed, this.callLog not empty\n",
				0, c.size());
	}

	
	@Test
	public void test02_ctor() {
		
		List<CellNumber> callLog = this.getCallLog();
		CellPhone p = new CellPhone(new CellNumber(416, 736, 2100),
				callLog);
		CellNumber n = this.getNumber(p);
		assertEquals("CellPhone(CellNumber, List<CellNumber>) failed, wrong cell number\n",
				new CellNumber(416, 736, 2100), n);
		List<CellNumber> c = this.getCallLog(p);
		assertNotSame("CellPhone(CellNumber, List<CellNumber>) failed, this.callLog is not a copy\n",
				callLog, c);
		assertEquals("CellPhone(CellNumber, List<CellNumber>) failed, this.callLog contains the wrong callLog\n",
				callLog, c);
	}
	
	@Test
	public void test03_ctor() {
		
		List<CellNumber> callLog = new ArrayList<>();
		CellPhone p = new CellPhone(new CellNumber(416, 736, 2100),
				callLog);
		CellNumber n = this.getNumber(p);
		assertEquals("CellPhone(CellNumber, List<CellNumber>) failed, wrong cell number\n",
				new CellNumber(416, 736, 2100), n);
		List<CellNumber> c = this.getCallLog(p);
		assertNotSame("CellPhone(CellNumber, List<CellNumber>) failed, this.callLog is not a copy\n",
				callLog, c);
		assertEquals("CellPhone(CellNumber, List<CellNumber>) failed, this.callLog contains the wrong callLog\n",
				callLog, c);
	}
	
	@Test
	public void test04_getNumber() {
		CellPhone p = new CellPhone();
		CellNumber n = p.getNumber();
		assertEquals("getNumber() failed, wrong cell number\n",
				new CellNumber(416, 736, 2100), n);
		assertNotSame("getNumber() failed to return a new copy of the cell number\n", 
				this.getNumber(p), n);
	}
	
	@Test
	public void test05_setNumber() {
		CellPhone p = new CellPhone();
		CellNumber exp = new CellNumber(905, 123, 4567);
		p.setNumber(exp);
		CellNumber got = this.getNumber(p);
		assertEquals("setNumber() failed, wrong cell number\n",
				exp, got);
		assertNotSame("setNumber() failed to create a new copy of the cell number\n", 
				exp, got);
	}
	
	
	@Test
	public void test06_getCallLog() {
		CellPhone p = new CellPhone();
		List<CellNumber> c = this.getCallLog(p);
		c.addAll(this.getCallLog());
		
		List<CellNumber> got = p.getCallLog();
		
		// did getCallLog return a new list?
		assertNotSame("getCallLog() failed, returned an alias to this.callLog\n", 
				c, got);
		
		// did getCallLog return a list with the correct callLog?
		assertEquals("getCallLog() failed, returned set has the wrong callLog\n", 
				c, got);
		
		// did getCallLog return a shallow copy?
		Iterator<CellNumber> ic = c.iterator();
		Iterator<CellNumber> igot = got.iterator();
		while (ic.hasNext()) {
			assertSame("getCallLog() failed, returned set is a deep copy\n", 
					ic.next(), igot.next());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test07_dial() {
		CellPhone p = new CellPhone();
		List<CellNumber> c = this.getCallLog(p);
		c.addAll(this.getCallLog());
		p.dial(null);
	}
	
	@Test
	public void test08_dial() {
		CellPhone p = new CellPhone();
		List<CellNumber> c = this.getCallLog(p);
		c.addAll(this.getCallLog());
		
		List<CellNumber> exp = this.getCallLog();
		exp.add(new CellNumber(905, 111, 2222));
		
		p.dial(new CellNumber(905, 111, 2222));
		c = this.getCallLog(p);
		assertEquals("dial() failed, this.callLog contains the wrong cell numbers\n", 
				exp, c);
	}
	
	@Test
	public void test09_dial() {
		CellPhone p = new CellPhone();
		
		List<CellNumber> exp = new ArrayList<>();
		exp.add(new CellNumber(905, 111, 2222));
		
		p.dial(new CellNumber(905, 111, 2222));
		List<CellNumber> c = this.getCallLog(p);
		assertEquals("dial() failed, this.callLog contains the wrong cell numbers\n", 
				exp, c);
	}
	
	@Test
	public void test10_favorite() {
		CellPhone p = new CellPhone();
		assertNull("favorite() failed to return null\n", p.favorite());
	}
	
	@Test
	public void test11_favorite() {
		CellPhone p = new CellPhone();
		List<CellNumber> c = this.getCallLog(p);
		c.addAll(this.getCallLog());
		
		CellNumber exp = new CellNumber(416, 736, 9999);
		assertEquals("favorite() failed, returned the wrong cell number\n", 
				exp, p.favorite());
	}
	
	
	@Test
	public void test12_toString() {
		CellPhone p = new CellPhone();
		assertEquals("toString() failed to return the correct string\n", 
				new CellNumber(416, 736, 2100).toString(), p.toString());
	}
}
