package test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class that represents a collection of contacts.
 * 
 * <p>
 * A contact set has a name and a set of contacts.
 * The call log and its name form an aggregation. The call
 * log and its set of contacts form a composition. 
 *  
 * <p>
 * A contact set ensures that every contact is unique (i.e., no two contacts are
 * equal). Two or more contacts with the same name are allowed as long as
 * their phone numbers are different.
 *
 */
public class ContactSet {

	/**
	 * The name of this contact set. DO NOT CHANGE THE NAME OR TYPE OF THIS FIELD.
	 */
	private String name;
	
	
	/**
	 * The set of contacts. DO NOT CHANGE THE NAME OR TYPE OF THIS FIELD.
	 */
	private Set<Contact> contacts;
	
	
	/**
     * Initializes this contact set to have a name.
     * Do not duplicate the code in this constructor; it is here for
     * testing purposes in case your constructors are not implemented
     * correctly.
     */
	public ContactSet() {
		try {
    		Test3CUtils.initialize(this);
    	}
    	catch (Exception x) {
    		System.out.println("something went wrong");
    		throw new NullPointerException();
    	}
	}
	
	
	/**
	 * Initialize this contact set so that it has the given name
	 * and zero contacts.
	 * 
	 * @param setName the non-null name of this contact set
	 */
	public ContactSet(String setName) {
		this.name = setName;
		this.contacts = new TreeSet<Contact>();
	}
	
	
	/**
	 * Initialize this contact set by copying the name and contacts
	 * from another contact set. The contacts held by this contact
	 * set are new copies of the contacts held by the other contact set.
	 * 
	 * @param other a non-null contact set to copy
	 */
	public ContactSet(ContactSet other) {
		this.name = other.getName();
		this.contacts = new TreeSet<Contact>();
		
		for (Contact c : other.getContacts()) 
			this.contacts.add(new Contact(c.getName(), c.getNumber()));
	}
	
	/**
	 * Return the name of this contact set.
	 * 
	 * @return the name of this contact set
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name of this contact set.
	 * 
	 * @param name a non-null name for this contact set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns a list containing references to the contacts
	 * in this contact list (i.e., the list is a new list but the
	 * contacts are not new copies of the existing contacts). The
	 * returned list is sorted by the names of the contacts.
	 * 
	 * @return a sorted list containing references to the contacts
	 * in this contact list 
	 */
	public List<Contact> getContacts() {
		List<Contact> l = new ArrayList<Contact>();
		for (Contact c : this.contacts)
			l.add(c);
		return l;
	}
	
	/**
	 * Returns a new copy of the contact having the given name. If there
	 * are multiple contacts with the given name then any one of those
	 * contacts may be returned. <code>null</code> is returned if
	 * no contact with the given name is in this contact set.
	 * 
	 * @param name the non-null name of the contact
	 * @return a new copy of the contact having the given name, or
	 * null if such a contact does not exist
	 */
	public Contact getByName(String name) {
		Contact found = null;
		for (Contact c : this.contacts)
			if (c.getName() == name)
				found = new Contact(c);
		return found;
	}
	
	/**
	 * Update the information for an existing contact in this contact set.
	 * If the specified contact is not in this contact set then a
	 * <code>ContactDoesNotExistException</code> is thrown and this set
	 * remains unchanged. If a contact with the same name and phone number
	 * as the updated contact information is already in this contact set
	 * then a <code>DuplicateContactException</code> is thrown and this
	 * set remains unchanged.
	 * 
	 * <p>
	 * NOTE TO STUDENTS: To correctly update the set, you must remove
	 * the contact c from the set and then add the updated contact back to the set.
	 * 
	 * @param c the non-null contact to update
	 * @param name the non-null updated contact name
	 * @param number the non-null updated contact number
	 * @throws ContactDoesNotExistException if the contact c is not in
	 * this contact set
	 * @throws DuplicateContactException if a contact having the
	 * same name and number as the updated contact information is already in this
	 * contact set
	 */
	public void update(Contact c, String name, PhoneNumber number) {
		if (!this.contacts.contains(c))
			throw new ContactDoesNotExistException();
		
		Contact other = new Contact(name, number);
		if (this.contacts.contains(other))
			throw new DuplicateContactException();
			
		this.contacts.remove(c);
		this.contacts.add(other);
		
	}
	
	/**
	 * Add a new contact to this contact set. If the contact already
	 * exists in the contact set then nothing happens.
	 * 
	 * @param c the non-null contact to add
	 */
	public void add(Contact c) {
		this.contacts.add(c);
	}
}
