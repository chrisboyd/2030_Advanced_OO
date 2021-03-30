package test3;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents North American phone numbers. Every phone number has
 * 10 digits where the first 3 digits are the area code, the next three digits
 * are the exchange code, and the last four digits are the station code.
 * 
 * <p>
 * The class ensures that there is only one <code>PhoneNumber</code> instance
 * for each unique phone number. Clients should use the <code>getInstance</code>
 * method to obtain a <code>PhoneNumber</code> instance.
 * 
 */
public final class PhoneNumber implements Comparable<PhoneNumber> {
	private final short areaCode;
	private final short exchangeCode;
	private final short stationCode;

	private static final Map<String, PhoneNumber> instances = new HashMap<>();

	/**
	 * Initialize this phone number given its area code, its exchange code, and
	 * its station code.
	 * 
	 * @param areaCode
	 *            the area code; must be in the range 0-999
	 * @param exchangeCode
	 *            the exchange code; must be in the range 0-999
	 * @param stationCode
	 *            the station code; must be in the range 0-9999
	 */
	private PhoneNumber(int areaCode, int exchangeCode, int stationCode) {
		this.areaCode = (short) areaCode;
		this.exchangeCode = (short) exchangeCode;
		this.stationCode = (short) stationCode;
	}

	private static void rangeCheck(int num, int max, String name) {
		if (num < 0 || num > max) {
			throw new IllegalArgumentException(name + " : " + num);
		}
	}

	/**
	 * Get a phone number having the given area code, exchange code, and station
	 * code.
	 * 
	 * @param areaCode
	 *            the area code; must be in the range 0-999
	 * @param exchangeCode
	 *            the exchange code; must be in the range 0-999
	 * @param stationCode
	 *            the station code; must be in the range 0-9999
	 * @return a phone number with the given area, exchange, and station codes
	 * @throws IllegalArgumentException
	 *             if any of the area code, exchange code or station code are
	 *             out of range
	 */
	public static PhoneNumber getInstance(int areaCode, int exchangeCode, int stationCode) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(exchangeCode, 999, "exchange code");
		rangeCheck(stationCode, 9999, "station code");

		String key = areaCode + ":" + exchangeCode + ":" + stationCode;
		if (PhoneNumber.instances.containsKey(key)) {
			return PhoneNumber.instances.get(key);
		}
		PhoneNumber n = new PhoneNumber(areaCode, exchangeCode, stationCode);
		PhoneNumber.instances.put(key, n);
		return n;
	}

	/**
	 * Returns the area code.
	 * 
	 * @return the area code
	 */
	public short getAreaCode() {
		return this.areaCode;
	}

	/**
	 * Returns the exchange code.
	 * 
	 * @return the exchange code
	 */
	public short getExchangeCode() {
		return this.exchangeCode;
	}

	/**
	 * Returns the station code.
	 * 
	 * @return the station code
	 */
	public short getStationCode() {
		return this.stationCode;
	}

	/**
	 * Returns a string representation of this phone number. An example of a
	 * string representation of a phone number is "(416) 736-2100"
	 * 
	 * @return a string representation of this phone number
	 */
	@Override
	public String toString() {
		return String.format("(%1$03d) %2$03d-%3$04d", this.areaCode, this.exchangeCode, this.stationCode);
	}

	/**
	 * Returns a hash code for this phone number.
	 * 
	 * @return a hash code for this phone number
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + areaCode;
		result = prime * result + exchangeCode;
		result = prime * result + stationCode;
		return result;
	}

	/**
	 * Compares two phone numbers for equality. The result is true if and only
	 * if the argument is not null and is an PhoneNumber object that has the
	 * same area, exchange, and station code as this phone number.
	 * 
	 * @param obj
	 *            the object to compare this phone number against
	 * @return true if the given object represents an PhoneNumber equivalent to
	 *         this phone number, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (areaCode != other.areaCode)
			return false;
		if (exchangeCode != other.exchangeCode)
			return false;
		if (stationCode != other.stationCode)
			return false;
		return true;
	}

	/**
	 * Compares two phone numbers for order. The phone numbers are compared
	 * first by area code, then by exchange code, then by station code.
	 * 
	 * @param other
	 *            the phone number to be compared
	 * @return a negative integer if this phone number is less than the other
	 *         phone number, 0 if this phone number is equal to the other phone
	 *         number, or a positive integer if this phone number is greater
	 *         than the other phone number
	 */
	@Override
	public int compareTo(PhoneNumber other) {
		int result = this.getAreaCode() - other.getAreaCode();
		if (result == 0) {
			// area codes are the same; compare exchange codes
			result = this.getExchangeCode() - other.getExchangeCode();
			if (result == 0) {
				// area and exchange codes are the same; compare station codes
				result = this.getStationCode() - other.getStationCode();
			}
		}
		return 0;
	}

}
