package eecs2030.lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * A weather station contains sets of weather sensors to measure various weather
 * parameters and publishes the readings to a list of subscribed weather
 * observers.
 * 
 * The weather station pulls the readings from the sensors. Usually there is a
 * set of redundant sensors for each weather parameter such as temperature and
 * pressure. The value reported by the weather station is the average consensus
 * of the readings, i.e. the average of the available measurements. In case any
 * sensor fails, its reading is eliminated from the average and this sensor will
 * be fixed. The weather station notifies the subscribed observers for any
 * updates in the weather parameters.
 * 
 */
public class WeatherStation {

	private List<WeatherObserver> observers;
	private List<TemperatureSensor> tempSensors;
	private List<PressureSensor> pressSensors;

	/**
	 * Initialize a new weather station with no sensors installed.
	 * 
	 */
	public WeatherStation() {
		observers = new ArrayList<WeatherObserver>();
		tempSensors = new ArrayList<TemperatureSensor>();
		pressSensors = new ArrayList<PressureSensor>();
	}

	/**
	 * Subscribe the input weather observer o as one of the observers of the current
	 * weather station.
	 * 
	 * @param o a weather observer
	 */
	public void subscribe(WeatherObserver o) {
		o.setWeatherStation(this);
		observers.add(o);
	}

	/**
	 * Unsubscribe the input weather observer o from the list of observers of the
	 * current weather station. Assume that the input o is an already-subscribed
	 * observer.
	 * 
	 * @param o a weather observer
	 */
	public void unsubscribe(WeatherObserver o) {
		o.setWeatherStation(null);
		observers.remove(o);
	}

	/**
	 * Get the list of subscribed weather observers.
	 * 
	 * @return an array of subscribed weather observers.
	 */
	public WeatherObserver[] getObservers() {
		WeatherObserver obsArray[] = new WeatherObserver[observers.size()];
		return observers.toArray(obsArray);
	}

	/**
	 * Publish the latest readings of weather data to all subscribed observers. That
	 * is, perform an update on each subscribed observer.
	 */
	public void publish() {
		for (WeatherObserver w : observers)
			w.update();
	}

	/**
	 * Adds temperature sensor to the corresponding sensor list
	 * 
	 * @param ts temperature sensor to be added
	 */
	public void addTempSensor(TemperatureSensor ts) {
		tempSensors.add(ts);
	}

	/**
	 * Adds pressure sensor to the corresponding sensor list
	 * 
	 * @param ps pressure sensor to be added
	 */
	public void addPressSensor(PressureSensor ps) {
		pressSensors.add(ps);
	}

	/**
	 * Removes the temperature sensor from the corresponding sensor list
	 * 
	 * @param ts temperature sensor to be removed
	 */
	public void removeTempSensor(TemperatureSensor ts) {
		tempSensors.remove(ts);
	}

	/**
	 * Removes the pressure sensor from the corresponding sensor list
	 * 
	 * @param ps pressure sensor to be removed
	 */
	public void removePressSensor(PressureSensor ps) {
		pressSensors.remove(ps);
	}

	/**
	 * Get the consensus of temperature measurements.
	 * 
	 * @return latest temperature measure
	 */
	public double getTemperature() {
		double total = 0.0;
		int i = 0;
		for (TemperatureSensor t : tempSensors) {
			total += t.get();
			i++;
		}
		return total / i;
	}

	/**
	 * Get the consensus of pressure measurements.
	 * 
	 * @return latest pressure measure
	 */
	public double getPressure() {
		double total = 0.0;
		int i = 0;
		for (PressureSensor p : pressSensors) {
			total += p.get();
			i++;
		}
		return total / i;
	}
}