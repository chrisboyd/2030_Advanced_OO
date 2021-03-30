package test3;

import java.util.Objects;

/**
 * A class that represents a telephone contact. Every contact has a name and a
 * telephone number. 
 *  
 */
public class Contact implements Comparable<Contact> {
    private String name;
    private PhoneNumber number;

    /**
     * Initialize this contact to have the specified name and telephone number.
     * 
     * @param name
     *            the name of this contact
     * @param number
     *            the phone number of this contact
     */
    public Contact(String name, PhoneNumber number) {
        this.setNumber(number);
        this.setName(name);
    }

    /**
     * Initialize this contact to have the same name and telephone number as
     * another contact.
     * 
     * @param other
     *            the contact to copy
     */
    public Contact(Contact other) {
        this(other.name, other.number);
    }

    /**
     * Returns the name of this contact.
     * 
     * @return the name the name of this contact
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this contact.
     * 
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the telephone number of this contact. 
     * 
     * @return the telephone number of this contact
     */
    public PhoneNumber getNumber() {
        return this.number;
    }

    /**
     * Set the phone number for this contact.
     * 
     * @param number
     *            the phone number of this contact
     */
    public final void setNumber(PhoneNumber number) {
        this.number = number;
    }

    /**
     * Returns a hash code for this contact. The hash code is computed
     * using the name and phone number of this contact.
     * 
     * @return a hash code for this contact
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.number);
    }

    /**
     * Compares two contacts for equality. The result is true if and only if the
     * argument is not null and is a Contact object that has the same name and
     * telephone number as this contact.
     * 
     * @param obj
     *            the object to compare this item against
     * @return true if the given object represents a Contact equivalent to this
     *         contact, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Contact other = (Contact) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.number.equals(other.number)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this contact with another contact for order. Contacts are
     * compared by the name of the contact using the String version of
     * compareTo.
     * 
     * @param other
     *            a contact to compare with
     * @return the value 0 if the argument contact name is equal to this contact
     *         name; a value less than 0 if this contact name is
     *         lexicographically less than the argument contact name; and a
     *         value greater than 0 if this contact name is lexicographically
     *         greater than the argument contact name.
     */
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Returns a string representation of this contact. The returned string
     * is the name of the contact followed by a space followed by the telephone
     * number of the contact. The string representation of the telephone number
     * is the area code in parentheses, followed by a space,
     * followed by the first three digits of the phone number, followed by a
     * hyphen, followed by the last four digits of the phone number.
     * 
     * @return a string representation of the contact
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name + " " + this.number;
    }
}
