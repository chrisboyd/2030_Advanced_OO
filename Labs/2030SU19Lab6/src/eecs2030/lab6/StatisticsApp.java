package eecs2030.lab6;

/**
 * A weather statistics app calculates the minimum, maximum, and average
 * temperature read so far from its subscribed weather station, if any.
 */
public class StatisticsApp extends WeatherObserver {

	private double max = Double.NaN;
	private double min = Double.NaN;
	private double total = 0.0;
	private int readings = 0;
	private WeatherStation station = null;

	/**
	 * Update the reading of this weather observer. Update the current temperature
	 * and the maximum, minimum, and average accordingly.
	 */
	public void update() {
		double temp = station.getTemperature();
		readings++;
		total += temp;
		
		if (readings == 1) {
			max = temp;
			min = temp;
		}
		
		if (temp > max)
			max = temp;
		if (temp < min)
			min = temp;
	}

	/**
	 * Get the weather station to which the current weather observer is subscribed
	 * to.
	 * 
	 * @return the weather station to which the current weather observer is
	 *         subscribed to
	 */
	public WeatherStation getWeatherStation() {
		return station;
	}

	/**
	 * Subscribe the current weather observer to the input weather station ws.
	 * 
	 * @param ws a weather station
	 */
	public void setWeatherStation(WeatherStation ws) {
		station = ws;
	}

	/**
	 * Get the minimum temperature based on the readings so far.
	 * 
	 * @return minimum temperature based on the readings so far
	 */
	public double getMinTemperature() {
		return min;
	}

	/**
	 * Get the maximum temperature based on the readings so far.
	 * 
	 * @return maximum temperature based on the readings so far
	 */
	public double getMaxTemperature() {
		return max;
	}

	/**
	 * Get the average temperature based on the readings so far.
	 * 
	 * @return average temperature based on the readings so far
	 */
	public double getAverageTemperature() {
		return total / readings;
	}
}
