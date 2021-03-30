package eecs2030.lab6;

/**
 * Temperature Sensor. The default rate of failure is 0.001
 * 
 */
public class TemperatureSensor extends WSensor {
	private static final double FAILURE_RATE = 0.001;
	private double temp;

	/**
	 * Initialize the temperature sensor with the given initial temperature.
	 * 
	 * @param temp the initial temperature
	 */
	public TemperatureSensor(double temp) {
		super(FAILURE_RATE);
		this.temp = temp;
	}

	/**
	 * Get the measurement of the sensor regardless of its state.
	 * 
	 * @return the measurement of the sensor regardless of its state.
	 **/
	@Override
	protected double get() {
		return this.temp;
	}

	/**
	 * Sets the measurement value of the sensor regardless of the state.
	 * 
	 * @param value the value of the measurement
	 **/
	@Override
	public void set(double value) {
		this.temp = value;
	}

}
