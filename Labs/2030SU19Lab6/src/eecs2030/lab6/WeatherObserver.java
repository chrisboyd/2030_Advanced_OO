package eecs2030.lab6;

/**
 * A weather observer should be subscribed to some weather station and be kept
 * up to date whenever an update on the measures of weather parameters occurs.
 * 
 * DO NOT MODIFY THIS CLASS.
 */
public abstract class WeatherObserver {

	/**
	 * Update the reading of this weather observer.
	 */
	public abstract void update();

	/**
	 * Get the weather station to which the current weather observer is subscribed
	 * to.
	 * 
	 * @return the weather station to which the current weather observer is
	 *         subscribed to
	 */
	public abstract WeatherStation getWeatherStation();

	/**
	 * Subscribe the current weather observer to the input weather station ws.
	 * 
	 * @param ws a weather station
	 */
	public abstract void setWeatherStation(WeatherStation ws);
}
