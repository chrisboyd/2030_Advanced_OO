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

	/*
	 * Your Task: Declare an attribute for the list of subscribed weather observers.
	 */
	ArrayList<WeatherObserver> observers;

	/*
	 * Your Task: Declare attributes for the sets of weather sensors.
	 */
	private ArrayList<WSensor> tempSensors;
	private ArrayList<WSensor> presSensors;

	/**
	 * Initialize a new weather station with no sensors installed.
	 * 
	 */
	public WeatherStation() {
		/* Your Task */
		observers = new ArrayList<WeatherObserver>();
		tempSensors = new ArrayList<>();
		presSensors = new ArrayList<>();
	}

	/**
	 * Subscribe the input weather observer o as one of the observers of the current
	 * weather station.
	 * 
	 * @param o
	 *            a weather observer
	 */
	public void subscribe(WeatherObserver o) {
		/* Your Task */
		observers.add(o);
		o.setWeatherStation(this);
	}

	/**
	 * Unsubscribe the input weather observer o from the list of observers of the
	 * current weather station. Assume that the input o is an already-subscribed
	 * observer.
	 * 
	 * @param o
	 *            a weather observer
	 */
	public void unsubscribe(WeatherObserver o) {
		/* Your Task */
		observers.remove(o);
		o.setWeatherStation(null);
	}

	/**
	 * Get the list of subscribed weather observers.
	 * 
	 * @return an array of subscribed weather observers.
	 */
	public WeatherObserver[] getObservers() {
		/* Your Task */
		WeatherObserver[] obs = new WeatherObserver[observers.size()];
		for (int i = 0; i < observers.size(); i++) {
			obs[i] = observers.get(i);
		}
		return obs;
	}

	/**
	 * Publish the latest readings of weather data to all subscribed observers. That
	 * is, perform an update on each subscribed observer.
	 */
	public void publish() {
		/* Your Task */
		for (WeatherObserver o : observers) {
			o.update();
		}
	}

	/**
	 * Adds temperature sensor to the corresponding sensor set
	 * 
	 * @param ts
	 *            temperature sensor to be added
	 */
	public void addTempSensor(TemperatureSensor ts) {
		tempSensors.add(ts);
	}

	/**
	 * Adds pressure sensor to the corresponding sensor set
	 * 
	 * @param ps
	 *            pressure sensor to be added
	 */
	public void addPressSensor(PressureSensor ps) {
		presSensors.add(ps);
	}

	/**
	 * Removes the temperature sensor from the corresponding sensor sets
	 * 
	 * @param ts
	 *            temperature sensor to be removed
	 */
	public void removeTempSensor(TemperatureSensor ts) {
		if (tempSensors.contains(ts))
			tempSensors.remove(ts);
	}

	/**
	 * Removes the pressure sensor from the corresponding sensor sets
	 * 
	 * @param ps
	 *            pressure sensor to be removed
	 */
	public void removePressSensor(PressureSensor ps) {
		if (presSensors.contains(ps))
			presSensors.remove(ps);
	}

	/**
	 * 
	 * @param wsl
	 * @return
	 */
	private static double getConsensus(List<WSensor> wsl) {
		double sum = 0;
		int n = 0;
		for (WSensor s : wsl) {
			try {
				sum += s.read();
				n++;
			} catch (SensorFailedException sfe) {
				s.fix();
			}
		}
		return sum / n;
	}

	/**
	 * Get the consensus of temperature measurements.
	 * 
	 * @return latest temperature measure
	 */
	public double getTemperature() {
		/* Your Task */
		return getConsensus(tempSensors);
	}

	/**
	 * Get the consensus of pressure measurements.
	 * 
	 * @return latest pressure measure
	 */
	public double getPressure() {
		/* Your Task */
		return getConsensus(presSensors);
	}
}
