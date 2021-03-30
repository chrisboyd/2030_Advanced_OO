package eecs2030.lab6;
/**
 * Custom exception thrown when a weather sensor fails to report a measurement.
 * It keeps a reference to the failed sensor.
 *
 */
public class SensorFailedException extends Exception {
	/*
	 * Your Task: Declare required fields to have a functioning class
	 */
	private WSensor s;
	public SensorFailedException(WSensor s) {
		/* Your Task */
		this.s = s;
	}

	/**
	 * Returns a reference to the failed sensor
	 * 
	 * @return a reference to the failed sensor
	 */
	public WSensor getSensor() {
		/* Your Task */
		return s;
	}
}
