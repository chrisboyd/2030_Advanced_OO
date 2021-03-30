package eecs2030.lab6;
/**
 * A weather forecast app determines if the pressure readings from 
 * its subscribed weather station, if any, indicate it is likely to rain
 * due to a reduction on the pressure level.
 */
public class ForecastApp extends WeatherObserver {
	
	/*
	 * Your Task: Declare required fields to have a functioning class
	 */
	private boolean initialized;
	private double currentPressure;
	private double lastPressure;
	private WeatherStation weatherStation;
	
	/**
	 * Update the reading of this weather observer.
	 * Update the current and last readings of pressure.
	 */
	public void update() {
		/* Your Task */
		if (initialized) {
			lastPressure = currentPressure;
			currentPressure = weatherStation.getPressure();
		}
		else {
			currentPressure = weatherStation.getPressure();
			lastPressure = currentPressure;
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
	 * Get the pressure level read from the last update.
	 * @return pressure level read from the last update
	 */
	public double getCurrentPressure() {
		/* Your Task */
		return currentPressure;
	}
	
	/**
	 * Get the pressure level read from the second last update.
	 * @return pressure level read from the second last update
	 */
	public double getLastPressure() {
		/* Your Task */
		return lastPressure;
	}
	
	/**
	 * Based on the last and second last readings of the pressure level,
	 * it is determined as likely to rain if there is a strict reduction 
	 * on the pressure level; otherwise it is unlikely to rain.
	 * @return whether or not it is likely to rain.
	 */
	public boolean isLikelyToRain() {
		/* Your Task */
		return currentPressure < lastPressure;
	}
}
