package eecs2030.lab6;
/**
 * Temperature Sensor. The default rate of failure is 0.001
 * 
 */
public class TemperatureSensor extends WSensor {

	/*
	 * Your Task: Declare attributes to represent the measurement value.
	 */

	double temp;

	/**
	 * Initialize the temperature sensor with the given initial temperature.
	 * 
	 * @param temp
	 *            the initial temperature
	 */
	public TemperatureSensor(double temp) {
		/* Your Task */
		super(0.001);
		this.temp = temp;
	}

	/* See the method description in the parent class */
	@Override
	protected double get() {
		/* Your Task */
		// return 0;
		return temp;
	}

	/* See the method description in the parent class */
	@Override
	public void set(double value) {
		/* Your Task */
		temp = value;
	}

}
