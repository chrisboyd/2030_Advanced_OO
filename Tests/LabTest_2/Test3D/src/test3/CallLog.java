package test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that manages information regarding phone calls.
 * A call log records information about the time when a call
 * occurred and the phone number associated with the call.
 * 
 * <p>
 * A call log has a name and a list of call log entries.
 * The call log and its name form an aggregation. The call
 * log and its list of entries form a composition.
 * 
 * <p>
 * A call log ensures that no two log entries have dates that
 * are equal.
 *
 */
public class CallLog {

	/**
	 * The name of this call log. DO NOT CHANGE THE NAME OR TYPE OF THIS FIELD.
	 */
	private String name;
	
	
	/**
	 * The list of entries. DO NOT CHANGE THE NAME OR TYPE OF THIS FIELD.
	 */
	private List<CallLogEntry> log;
	
	
	public CallLog() {
		try {
    		Test3DUtils.initialize(this);
    	}
    	catch (Exception x) {
    		System.out.println("something went wrong");
    		throw new NullPointerException();
    	}
	}
	
	
	/**
	 * Initializes this call log to have the given name and
	 * zero entries.
	 * 
	 * @param name the non-null name of this call log
	 */
	public CallLog(String name) {
		this.name = name;
		this.log = new ArrayList<CallLogEntry>();
	}
	
	
	/**
	 * Initializes this call log to have the given name and a size
	 * equal to 1. The call log contains one <code>CallLogEntry</code>
	 * whose date is equal to <code>callDate</code> and whose
	 * phone number is equal to <code>number</code>.
	 * 
	 * @param name the non-null name of this call log
	 * @param callDate the non-null date at which the call occurred
	 * @param number the non-null phone number of the call
	 */
	public CallLog(String name, Date callDate, PhoneNumber number) {
		this(name);
		Date d = new Date(callDate.getTime());
		this.log.add(new CallLogEntry(d, number));
	}
	
	
	/**
	 * Return the name of this call log.
	 * 
	 * @return the name of this call log
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name of this call log.
	 * 
	 * @param name a non-null name for this call log
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the number of entries in this call log.
	 * 
	 * @return the number of entries in this call log
	 */
	public int size() {
		return this.log.size();
	}
	
	
	/**
	 * Returns a set of all of the phone numbers in this call log.
	 * There are no guarantees on the order of the phone numbers in
	 * the returned set.
	 * 
	 * @return a set of all of the phone numbers in this call log
	 */
	public Set<PhoneNumber> getPhoneNumbers() {
		Set<PhoneNumber> numbers = new HashSet<PhoneNumber>();
		for (CallLogEntry entry : log) 
			numbers.add(entry.getNumber());
		return numbers;
	}
	
	/**
	 * Adds an entry to this call log. Throws a <code>SameDateException</code>
	 * if the call log already contains an entry equal to
	 * <code>entry.getDate()</code>
	 * 
	 * @param entry an entry to add to this call log
	 * @throws SameDateException if the call log already contains an
	 * entry equal to entry.getDate()
	 */
	public void add(CallLogEntry entry) {
		for (CallLogEntry e : this.log)
			if (e.getDate().equals(entry.getDate()))
				throw new SameDateException();
		CallLogEntry d = new CallLogEntry(new Date(entry.getDate().getTime()), entry.getNumber());
		this.log.add(d);
	}
	
	/**
	 * Returns a sorted list of all of the entries in this call log
	 * that occurred after the given date. The entries in the list
	 * are new independent copies of the entries in this call log.
	 * 
	 * @param after a non-null date
	 * @return a sorted list of all of the entries in this call log
	 * that occurred after the given date
	 */
	public List<CallLogEntry> callsAfter(Date after) {
		List<CallLogEntry> callsAfter = new ArrayList<CallLogEntry>();
		for (CallLogEntry entry : this.log) 
			if (entry.getDate().after(after))
				callsAfter.add(entry);
		Collections.sort(callsAfter);
		return callsAfter;
	}
}
