package eecs2030.lab6;

/**
 * This is an abstract class to represent a weather sensor. Weather sensors
 * report weather parameters to a weather station. Sensor readings are pulled by
 * calling the method read. Each sensor has a fixed failure rate that switches
 * the sensor state to damaged and needs to be fixed. A damaged sensor will
 * throw an exception when trying to get its reading.
 *
 */
public abstract class WSensor {
	/*
	 * Your Task: Declare attributes to represent failure rate and damaged state.
	 */
	protected double rof;
	private boolean isDamaged;
	
	/**
	 * Initialize the sensor with the given failure rate.
	 * @param failureRate
	 */
	public WSensor(double failureRate) {
		/* Your Task */
		rof = failureRate;
	}

	/**
	 * Reads the measurement from the sensor and accounts for the sensor failures. A
	 * random number is utilized to simulate the probability of failures. Once the
	 * probability is less than the failure rate the sensor state is switched to
	 * damaged.
	 * 
	 * @return the measurement of the sensor.
	 * @throws SensorFailedException
	 *             if the sensor is damaged.
	 */
	public final double read() throws SensorFailedException {
		/* Your Task */
		if (Math.random() < rof)
			isDamaged = true;

		if (isDamaged)
			throw new SensorFailedException(this);

		return get();
	}

	/**
	 * Get the measurement of the sensor regardless of its state.
	 * 
	 * @return the measurement of the sensor regardless of its state.
	 */
	protected abstract double get();

	/**
	 * Sets the measurement value of the sensor regardless of the state.
	 * 
	 * @param value
	 *            the value of the measurement
	 */
	public abstract void set(double value);

	/**
	 * Fixes a damaged sensor
	 */
	public final void fix() {
		/* Your Task */
		isDamaged = false;
	}

}
