package eecs2030.lab6;
/**
 * Pressure Sensor. The default rate of failure is 0.0025
 * 
 */
public class PressureSensor extends WSensor {

	/*
	 * Your Task: Declare attributes to represent the measurement value.
	 */
	double press;
	
	/**
	 * Initialize the pressure sensor with the given initial pressure.
	 * 
	 * @param press
	 *            the initial pressure
	 */
	public PressureSensor(double press) {
		/* Your Task */
		super(0.0025);
		this.press = press;
	}

	/* See the method description in the parent class */
	@Override
	protected double get() {

		return press;
	}

	/* See the method description in the parent class */
	@Override
	public void set(double value) {
		press = value;
	}

}
