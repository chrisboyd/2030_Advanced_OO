package eecs2030.lab6;

/**
 * Custom exception thrown when a weather sensor fails to report a measurement.
 * It keeps a reference to the failed sensor.
 *
 */
public class SensorFailedException extends Exception {
	private WSensor failed;

	public SensorFailedException(WSensor s) {
		failed = s;
	}

	/**
	 * Returns a reference to the failed sensor
	 * 
	 * @return a reference to the failed sensor
	 */
	public WSensor getSensor() {
		return failed;
	}
}
