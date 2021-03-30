package test3;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactSetTest {
	
	private Set<Contact> getContacts() {
		Set<Contact> c = new LinkedHashSet<>();
		c.add(new Contact("Mom", PhoneNumber.getInstance(416, 736, 2100)));
		c.add(new Contact("Dad", PhoneNumber.getInstance(416, 736, 9999)));
		c.add(new Contact("PizzaPizza", PhoneNumber.getInstance(416, 967, 1111)));
		return c;
	}
	

	private Set<Contact> getContacts(ContactSet t) {
		Object obj = null;
		try {
			Field contacts = ContactSet.class.getDeclaredField("contacts");
			contacts.setAccessible(true);
			obj = contacts.get(t);
			assertNotNull("this.contacts is null",
					obj);
			if (!(obj instanceof Set<?>)) {
				fail("this.contacts has the wrong type!");
			}
		} catch (Exception x) {
			fail("exception occurred trying to get this.contacts!");
			System.out.println(x);
		}
		return (Set<Contact>) obj;
	}
	
	private String getName(ContactSet t) {
		Object obj = null;
		try {
			Field name = ContactSet.class.getDeclaredField("name");
			name.setAccessible(true);
			obj = name.get(t);
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
		ContactSet t = new ContactSet("My contacts");
		String name = this.getName(t);
		assertEquals("ContactSet(String) failed, wrong name\n",
				"My contacts", name);
		Set<Contact> c = this.getContacts(t);
		assertEquals("ContactSet(String) failed, this.contacts not empty\n",
				0, c.size());
	}

	
	@Test
	public void test02_ctor() {
		
		ContactSet t = new ContactSet("My contacts");
		ContactSet u = new ContactSet(t);
		
		String name = this.getName(u);
		assertEquals("ContactSet(ContactSet) failed, wrong name\n",
				"My contacts", name);
		Set<Contact> ct = this.getContacts(t);
		Set<Contact> cu = this.getContacts(u);
		assertNotSame("ContactSet(ContactSet) failed, this.contacts is not a new set\n",
				ct, cu);
		assertEquals("ContactSet(ContactSet) failed, this.contacts contains the wrong contacts\n",
				ct, cu);
	}
	
	@Test
	public void test03_ctor() {
		
		ContactSet t = new ContactSet("My contacts");
		Set<Contact> contacts = this.getContacts(t);
		contacts.addAll(this.getContacts());
		
		ContactSet u = new ContactSet(t);
		
		String name = this.getName(u);
		assertEquals("ContactSet(ContactSet) failed, wrong name\n",
				"My contacts", name);
		Set<Contact> ct = this.getContacts(t);
		Set<Contact> cu = this.getContacts(u);
		assertNotSame("ContactSet(ContactSet) failed, this.contacts is not a new set\n",
				ct, cu);
	}
	
	@Test
	public void test04_ctor() {
		
		ContactSet t = new ContactSet("My contacts");
		Set<Contact> contacts = this.getContacts(t);
		contacts.addAll(this.getContacts());
		
		ContactSet u = new ContactSet(t);
		
		List<Contact> ct = new ArrayList<>(this.getContacts(t));
		List<Contact> cu = new ArrayList<>(this.getContacts(u));
		Collections.sort(ct);
		Collections.sort(cu);
		assertEquals("ContactSet(ContactSet) failed, this.contacts contains the wrong contacts\n",
				ct, cu);
		for (int i = 0; i < ct.size(); i++) {
			assertNotSame("ContactSet(ContactSet) failed, this.contacts is not a deep copy\n", 
					ct.get(i), cu.get(i));
		}
	}
	
	
	@Test
	public void test05_getName() {
		ContactSet t = new ContactSet();
		assertEquals("getName() failed to return the correct name\n", 
				"Default name", t.getName());
	}
	
	@Test
	public void test06_setName() {
		ContactSet t = new ContactSet();
		String exp = "My contacts";
		t.setName(exp);
		String name = this.getName(t);
		assertEquals("setName() failed to set the correct name\n", 
				exp, name);
	}
	
	
	@Test
	public void test07_getContacts() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		List<Contact> got = t.getContacts();
		
		// did getContacts return a list with the correct contacts?
		assertTrue("getContacts() failed, returned list has the wrong contacts\n", 
				c.containsAll(got) && c.size() == got.size());
		
		// did getContacts return references to the contacts in this.contacts?
		Iterator<Contact> ic = c.iterator();
		while (ic.hasNext()) {
			Contact con = ic.next();
			int idx = got.indexOf(con);
			assertSame("getContacts() failed, returned list contains deep copies of the contacts\n",
					con, got.get(idx));
		}
		
		// is the list sorted?
		List<Contact> exp = new ArrayList<>(c);
		Collections.sort(exp);
		assertEquals("getContacts() failed, returned list is not sorted\n",
				exp, got);
	}
	
	@Test
	public void test08_getByName() {
		ContactSet t = new ContactSet();
		
		assertNull("getByName() failed to return null\n",
				t.getByName("Some name"));
	}
	
	@Test
	public void test09_getByName() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		for (Contact con : c) {
			String name = con.getName();
			Contact got = t.getByName(name);
			assertNotNull("getByName() failed, returned null for an existing contact\n",
					got);
			assertEquals("getByName() failed to return the correct contact\n",
					con, got);
			assertNotSame("getByName() failed to return a new copy of the contact\n",
					con, got);
		}
	}
	
	@Test
	public void test10_add() {
		ContactSet t = new ContactSet();
		
		Contact yu = new Contact("YorkU", PhoneNumber.getInstance(416, 736, 2100));
		t.add(yu);
		
		Set<Contact> exp = new HashSet<>();
		exp.add(yu);
		Set<Contact> got = this.getContacts(t);
		assertEquals("add(Contact) failed to add the contact correctly to this.contacts\n",
				exp, got);
	}
	
	@Test
	public void test11_add() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		Contact yu = new Contact("YorkU", PhoneNumber.getInstance(416, 736, 2100));
		Set<Contact> exp = new HashSet<>(this.getContacts());
		exp.add(yu);
		
		t.add(yu);
		Set<Contact> got = this.getContacts(t);
		assertEquals("add(Contact) failed to add the contact correctly to this.contacts\n",
				exp, got);
	}
	
	@Test(expected = ContactDoesNotExistException.class)
	public void test12_update() {
		ContactSet t = new ContactSet();
		Contact yu = new Contact("YorkU", PhoneNumber.getInstance(416, 736, 2100));
		t.update(yu, "York University", PhoneNumber.getInstance(416, 736, 2100));
	}
	
	@Test(expected = ContactDoesNotExistException.class)
	public void test13_update() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		Contact yu = new Contact("YorkU", PhoneNumber.getInstance(416, 736, 2100));
		t.update(yu, "York University", PhoneNumber.getInstance(416, 736, 2100));
	}
	
	@Test(expected = DuplicateContactException.class) 
	public void test14_update() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		Iterator<Contact> iter = c.iterator();
		Contact con = iter.next();
		t.update(con, con.getName(), con.getNumber());
	}
	
	@Test(expected = DuplicateContactException.class) 
	public void test15_update() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		Iterator<Contact> iter = c.iterator();
		Contact con = iter.next();
		Contact con2 = iter.next();
		t.update(con, con2.getName(), con2.getNumber());
	}
	
	@Test
	public void test16_update() {
		ContactSet t = new ContactSet();
		Set<Contact> c = this.getContacts(t);
		c.addAll(this.getContacts());
		
		Contact mom = new Contact("Mom", PhoneNumber.getInstance(416, 736, 2100));
		Contact mum = new Contact("Mum", PhoneNumber.getInstance(416, 736, 2101));
		t.update(mom, mum.getName(), mum.getNumber());
		
		Set<Contact> exp = this.getContacts();
		exp.remove(mom);
		exp.add(mum);
		assertEquals("update(Contact, String, PhoneNumber) failed to update a contact correctly\n",
				exp, this.getContacts(t));
	}
}
