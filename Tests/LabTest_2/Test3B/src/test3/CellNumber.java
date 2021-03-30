package test3;


/**
 * A class that represents North American cell phone numbers. Every cell number has
 * 10 digits where the first 3 digits are the area code, the next three digits
 * are the exchange code, and the last four digits are the station code.
 * 
 */
public final class CellNumber implements Comparable<CellNumber> {
	private final int areaCode;
	private final int exchangeCode;
	private final int stationCode;

	
	/**
	 * Initialize this cell number given its area code, its exchange code, and
	 * its station code.
	 * 
	 * @param areaCode
	 *            the area code; must be in the range 0-999
	 * @param exchangeCode
	 *            the exchange code; must be in the range 0-999
	 * @param stationCode
	 *            the station code; must be in the range 0-9999
	 * @throws IllegalArgumentException
	 *             if any of the area code, exchange code or station code are
	 *             out of range
	 */
	public CellNumber(int areaCode, int exchangeCode, int stationCode) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(exchangeCode, 999, "exchange code");
		rangeCheck(stationCode, 9999, "station code");
		
		this.areaCode = areaCode;
		this.exchangeCode = exchangeCode;
		this.stationCode = stationCode;
	}

	private static void rangeCheck(int num, int max, String name) {
		if (num < 0 || num > max) {
			throw new IllegalArgumentException(name + " : " + num);
		}
	}

	/**
	 * Initialize this cell number by copying the area, exchange, and
	 * station codes of another cell number.
	 * 
	 * @param other the cell number to copy
	 */
	public CellNumber(CellNumber other) {
		this.areaCode = other.areaCode;
		this.exchangeCode = other.exchangeCode;
		this.stationCode = other.stationCode;
	}
	
	/**
	 * Returns the area code.
	 * 
	 * @return the area code
	 */
	public int getAreaCode() {
		return this.areaCode;
	}

	/**
	 * Returns the exchange code.
	 * 
	 * @return the exchange code
	 */
	public int getExchangeCode() {
		return this.exchangeCode;
	}

	/**
	 * Returns the station code.
	 * 
	 * @return the station code
	 */
	public int getStationCode() {
		return this.stationCode;
	}

	/**
	 * Returns a string representation of this cell number. An example of a
	 * string representation of a cell number is "(416) 736-2100"
	 * 
	 * @return a string representation of this cell number
	 */
	@Override
	public String toString() {
		return String.format("(%1$03d) %2$03d-%3$04d", 
				this.areaCode, this.exchangeCode, this.stationCode);
	}

	/**
	 * Returns a hash code for this cell number.
	 * 
	 * @return a hash code for this cell number
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
	 * Compares two cell numbers for equality. The result is true if and only
	 * if the argument is not null and is an CellNumber object that has the
	 * same area, exchange, and station code as this cell number.
	 * 
	 * @param obj
	 *            the object to compare this cell number against
	 * @return true if the given object represents an CellNumber equivalent to
	 *         this cell number, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CellNumber other = (CellNumber) obj;
		if (areaCode != other.areaCode)
			return false;
		if (exchangeCode != other.exchangeCode)
			return false;
		if (stationCode != other.stationCode)
			return false;
		return true;
	}

	/**
	 * Compares two cell numbers for order. The cell numbers are compared
	 * first by area code, then by exchange code, then by station code.
	 * 
	 * @param other
	 *            the cell number to be compared
	 * @return a negative integer if this cell number is less than the other
	 *         cell number, 0 if this cell number is equal to the other phone
	 *         number, or a positive integer if this phone number is greater
	 *         than the other phone number
	 */
	@Override
	public int compareTo(CellNumber other) {
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
