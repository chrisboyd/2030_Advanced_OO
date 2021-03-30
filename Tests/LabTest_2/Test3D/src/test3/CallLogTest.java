package test3;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CallLogTest {

	private static final PhoneNumber P0 = PhoneNumber.getInstance(416, 736, 2100);
	private static final PhoneNumber P1 = PhoneNumber.getInstance(416, 736, 2101);
	private static final PhoneNumber P2 = PhoneNumber.getInstance(416, 736, 2102);
	private static final PhoneNumber P3 = PhoneNumber.getInstance(416, 736, 2103);
	private static final PhoneNumber P4 = PhoneNumber.getInstance(416, 736, 2104);
	
	
	private List<CallLogEntry> getLog() {
		List<CallLogEntry> c = new ArrayList<>();
		Date d = new Date(1499999999990L);
		c.add(new CallLogEntry(d, P0));
		d = new Date(d.getTime() + 100000L);
		c.add(new CallLogEntry(d, P1));
		d = new Date(d.getTime() + 100000L);
		c.add(new CallLogEntry(d, P2));
		return c;
	}

	private List<CallLogEntry> getLog(CallLog g) {
		Object obj = null;
		try {
			Field callLog = CallLog.class.getDeclaredField("log");
			callLog.setAccessible(true);
			obj = callLog.get(g);
			assertNotNull("this.log is null",
					obj);
			if (!(obj instanceof List<?>)) {
				fail("this.log has the wrong type!");
			}
		} catch (Exception x) {
			fail("exception occurred trying to get this.callLog!");
			System.out.println(x);
		}
		return (List<CallLogEntry>) obj;
	}
	
	private String getName(CallLog g) {
		Object obj = null;
		try {
			Field name = CallLog.class.getDeclaredField("name");
			name.setAccessible(true);
			obj = name.get(g);
			assertNotNull("this.name is null", 
					obj);
			if (!(obj instanceof String)) {
				fail("this.name has the wrong type!\n");
			}
			
		} catch (Exception x) {
			fail("exception occurred trying to get this.number!");
			System.out.println(x);
		}
		return (String) obj;
	}
	
	@Test
	public void test01_ctor() {
		CallLog g = new CallLog("Received");
		String name = this.getName(g);
		List<CallLogEntry> log = this.getLog(g);
		
		assertEquals("CallLog(String) failed, wrong name\n",
				"Received", name);
		assertEquals("CallLog(String) failed, this.log not empty\n",
				0, log.size());
	}
	
	@Test
	public void test02_ctor() {
		Date d = new Date();
		PhoneNumber p = P0;
		CallLog g = new CallLog("Received", d, p);
		String name = this.getName(g);
		List<CallLogEntry> log = this.getLog(g);
		
		assertEquals("CallLog(String, Date, PhoneNumber) failed, wrong name\n",
				"Received", name);
		assertEquals("CallLog(String, Date, PhoneNumber) failed, this.log has the wrong size\n",
				1, log.size());
		CallLogEntry exp = new CallLogEntry(d, p);
		assertEquals("CallLog(String, Date, PhoneNumber) failed, this.log has the wrong entry\n",
				exp, log.get(0));
	}
	
	@Test
	public void test03_getName() {
		CallLog g = new CallLog();
		assertEquals("getName() failed to return the correct name\n", 
				"Default name", g.getName());
	}
	
	@Test
	public void test04_setName() {
		CallLog g = new CallLog();
		String exp = "Received calls";
		g.setName(exp);
		String name = this.getName(g);
		assertEquals("setName() failed to set the correct name\n", 
				exp, name);
	}
	
	@Test
	public void test05_size() {
		CallLog g = new CallLog();
		assertEquals("size() failed to return the correct size of 0\n", 
				0, g.size());
	}
	
	@Test
	public void test06_size() {
		CallLog g = new CallLog();
		List<CallLogEntry> log = this.getLog(g);
		log.addAll(this.getLog());
		assertEquals("size() failed to return the correct size\n", 
				log.size(), g.size());
	}
	
	@Test
	public void test07_getPhoneNumbers() {
		CallLog g = new CallLog();
		Set<PhoneNumber> exp = new HashSet<>();
		assertEquals("getPhoneNumbers() failed to return an empty set\n",
				exp, g.getPhoneNumbers());
	}
	
	@Test
	public void test08_getPhoneNumbers() {
		CallLog g = new CallLog();
		List<CallLogEntry> log = this.getLog(g);
		log.addAll(this.getLog());
		Set<PhoneNumber> exp = new HashSet<>();
		for (CallLogEntry e : log) {
			exp.add(e.getNumber());
		}
		assertEquals("getPhoneNumbers() failed to return the correct set\n",
				exp, g.getPhoneNumbers());
	}
	
	@Test(expected = SameDateException.class)
	public void test09_add() {
		CallLog g = new CallLog();
		CallLogEntry e = this.getLog().get(0);
		g.add(e);
		g.add(new CallLogEntry(e.getDate(), e.getNumber()));
	}
	
	@Test(expected = SameDateException.class)
	public void test10_add() {
		CallLog g = new CallLog();
		List<CallLogEntry> log = this.getLog(g);
		log.addAll(this.getLog());
		CallLogEntry e = this.getLog().get(1);
		g.add(new CallLogEntry(e.getDate(), e.getNumber()));
	}
	
	@Test
	public void test11_add() {
		CallLog g = new CallLog();
		CallLogEntry e = this.getLog().get(1);
		g.add(new CallLogEntry(e.getDate(), e.getNumber()));
		
		List<CallLogEntry> exp = new ArrayList<>();
		exp.add(e);
		List<CallLogEntry> log = this.getLog(g);
		assertEquals("add(CallLogEntry) failed to update the call log correctly\n", 
				exp, log);
	}
	
	@Test
	public void test12_add() {
		CallLog g = new CallLog();
		List<CallLogEntry> log = this.getLog(g);
		log.addAll(this.getLog());
		
		CallLogEntry e = new CallLogEntry(new Date(1199999999990L), 
				PhoneNumber.getInstance(905, 111, 2222));
		
		List<CallLogEntry> exp = new ArrayList<>(this.getLog());
		exp.add(e);
		g.add(e);
		assertEquals("add(CallLogEntry) failed to update the call log correctly\n", 
				exp, log);
	}
	
	@Test
	public void test13_callsAfter() {
		CallLog g = new CallLog();
		List<CallLogEntry> log = this.getLog(g);
		log.addAll(this.getLog());
		Collections.sort(log);
		
		List<CallLogEntry> expLog = this.getLog();
		for (int i = 0; i < expLog.size(); i++) {
			CallLogEntry e = expLog.get(i);
			Date after = new Date(e.getDate().getTime() - 100L);
			List<CallLogEntry> got = g.callsAfter(after);
			List<CallLogEntry> exp = expLog.subList(i, expLog.size());
			assertEquals("callsAfter(Date) failed to return the correct list\n", 
					exp, got);
		}
	}
	
	@Test
	public void test14_callsAfter() {
		CallLog g = new CallLog();
		List<CallLogEntry> log = this.getLog(g);
		log.addAll(this.getLog());
		Collections.shuffle(log);
		
		List<CallLogEntry> expLog = this.getLog();
		for (int i = 0; i < expLog.size(); i++) {
			CallLogEntry e = expLog.get(i);
			Date after = new Date(e.getDate().getTime() - 100L);
			List<CallLogEntry> got = g.callsAfter(after);
			List<CallLogEntry> exp = expLog.subList(i, expLog.size());
			assertEquals("callsAfter(Date) failed to return the correct list\n", 
					exp, got);
		}
	}
}
