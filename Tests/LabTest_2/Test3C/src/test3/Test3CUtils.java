package test3;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class Test3CUtils {

	private Test3CUtils() {
		
	}
	
	public static void initialize(ContactSet cs) throws Exception {
		try {
			String n = "Default name";
			Set<Contact> c = new TreeSet<>();
			Field name = ContactSet.class.getDeclaredField("name");
			name.setAccessible(true);
			name.set(cs, n);
			Field contacts = ContactSet.class.getDeclaredField("contacts");
			contacts.setAccessible(true);
			contacts.set(cs, c);			
		}
		catch (Exception x) {
			System.out.println(x);
			throw x;
		}
	}
}
