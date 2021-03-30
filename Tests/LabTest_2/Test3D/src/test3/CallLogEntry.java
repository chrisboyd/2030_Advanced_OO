package test3;

import java.util.Date;

/**
 * An entry in a call log. A <code>CallLogEntry</code> is an aggregation
 * of a <code>Date</code> and a <code>PhoneNumber</code>.
 *
 */
public class CallLogEntry implements Comparable<CallLogEntry> {

	private Date date;
	private PhoneNumber number;
	
	/**
	 * Initialize this call log entry to the given date and phone number.
	 * 
	 * @param date the date of this entry
	 * @param number the phone number of this entry
	 */
	public CallLogEntry(Date date, PhoneNumber number) {
		this.date = date;
		this.number = number;
	}
	
	
	/**
	 * Returns the date of this entry.
	 * 
	 * @return the date of this entry
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Returns the phone number of this entry.
	 * 
	 * @return the phone number of this entry
	 */
	public PhoneNumber getNumber() {
		return this.number;
	}

	/**
	 * Returns a hash code for this entry.
	 * 
	 * @return a hash code for this entry
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	/**
	 * Compares two entries for equality. The result is true if and only
	 * if the argument is not null and is an <code>CallLogEntry</code> object that has the
	 * same date and phone number as this entry.
	 * 
	 * @param obj
	 *            the object to compare this entry against
	 * @return true if the given object represents an CallLogEntry equivalent to
	 *         this entry, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallLogEntry other = (CallLogEntry) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	/**
	 * Compares two call log entries for order. The entries are compared
	 * by their dates only.
	 * 
	 * @param other the call log entry to compare against
	 * @return a negative integer if this entry has an earlier date than
	 * the other entry, 0 if this entry has the same date as the other
	 * entry, or a positive integer if this entry has a later date than
	 * the other entry
	 */
	@Override
	public int compareTo(CallLogEntry other) {
		return this.date.compareTo(other.date);
	}
	
	/**
	 * Returns a string representation for this entry. The returned
	 * string is the date followed by " : " followed by the phone number.
	 * 
	 * @return a string representation for this entry
	 */
	public String toString() {
		return this.date + " : " + this.number;
	}
	
}
