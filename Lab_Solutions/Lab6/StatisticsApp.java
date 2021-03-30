package eecs2030.lab6;

/**
 * A weather statistics app calculates the 
 * minimum, maximum, and average temperature 
 * read so far from its subscribed weather station, if any.
 */
public class StatisticsApp extends WeatherObserver {

	/*
	 * Your Task: Declare required fields to have a functioning class
	 */
	private boolean initialized; 
	private WeatherStation weatherStation;
	private double currentTemp;
	private double maxTemp;
	private double minTemp;
	private double sumSoFar;
	private int numberOfReadings;

	/**
	 * Update the reading of this weather observer.
	 * Update the current temperature and the 
	 * maximum, minimum, and average accordingly.
	 */
	public void update() {
		/* Your Task */
		currentTemp = weatherStation.getTemperature();
		numberOfReadings = numberOfReadings + 1;
		sumSoFar = sumSoFar + currentTemp;

		if (initialized) {
			if (currentTemp > maxTemp) {
				maxTemp = currentTemp;
			}
			if (currentTemp < minTemp) {
				minTemp = currentTemp;
			}
		}
		else {
			maxTemp = currentTemp;
			minTemp = currentTemp;
			initialized = true;
		}
	}

	/* See the method description in the parent class */
	public WeatherStation getWeatherStation() {
		/* Your Task */
		return weatherStation;
	}

	/* See the method description in the parent class */
	public void setWeatherStation(WeatherStation ws) {
		/* Your Task */
		weatherStation = ws;
	}

	/**
	 * Get the minimum temperature based on the readings so far.
	 * @return minimum temperature based on the readings so far
	 */
	public double getMinTemperature() {
		/* Your Task */
		return minTemp;
	}

	/**
	 * Get the maximum temperature based on the readings so far.
	 * @return maximum temperature based on the readings so far
	 */
	public double getMaxTemperature() {
		/* Your Task */
		return maxTemp;
	}

	/**
	 * Get the average temperature based on the readings so far.
	 * @return average temperature based on the readings so far
	 */
	public double getAverageTemperature() {
		/* Your Task */
		return sumSoFar / numberOfReadings;
	}
}

