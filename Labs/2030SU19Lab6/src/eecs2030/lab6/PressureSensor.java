package eecs2030.lab6;

/**
 * Pressure Sensor. The default rate of failure is 0.0025
 * 
 */
public class PressureSensor extends WSensor {
	private static final double FAILURE_RATE = 0.0025;
	private double pressure;

	/**
	 * Initialize the pressure sensor with the given initial pressure.
	 * 
	 * @param press the initial pressure
	 */
	public PressureSensor(double press) {
		super(FAILURE_RATE);
		this.pressure = press;
	}

	/**
	 * Get the measurement of the sensor regardless of its state.
	 * 
	 * @return the measurement of the sensor regardless of its state.
	 **/
	@Override
	protected double get() {
		return this.pressure;
	}

	/**
	 * Sets the measurement value of the sensor regardless of the state.
	 * 
	 * @param value the value of the measurement
	 **/
	@Override
	public void set(double value) {
		this.pressure = value;
	}

}
