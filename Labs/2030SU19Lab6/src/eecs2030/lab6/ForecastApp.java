package eecs2030.lab6;

/**
 * A weather forecast app determines if the pressure readings from its
 * subscribed weather station, if any, indicate it is likely to rain due to a
 * reduction on the pressure level.
 */
public class ForecastApp extends WeatherObserver {

	private double currPressure = 0.0;
	private double lastPressure = 0.0;
	private WeatherStation station = null;

	/**
	 * Update the reading of this weather observer. Update the current and last
	 * readings of pressure.
	 */
	public void update() {
		if ((currPressure == 0.0) && (lastPressure == 0.0)) {
			lastPressure = station.getPressure();
			currPressure = lastPressure;
		}
			
		lastPressure = currPressure;
		currPressure = station.getPressure();
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
	 * Get the pressure level read from the last update.
	 * 
	 * @return pressure level read from the last update
	 */
	public double getCurrentPressure() {
		//this.update();
		
		return currPressure;
	}

	/**
	 * Get the pressure level read from the second last update.
	 * 
	 * @return pressure level read from the second last update
	 */
	public double getLastPressure() {
		//this.update();
		return lastPressure;
	}

	/**
	 * Based on the last and second last readings of the pressure level, it is
	 * determined as likely to rain if there is a strict reduction on the pressure
	 * level; otherwise it is unlikely to rain.
	 * 
	 * @return whether or not it is likely to rain.
	 */
	public boolean isLikelyToRain() {
		//this.update();
		return currPressure < lastPressure;
	}
}