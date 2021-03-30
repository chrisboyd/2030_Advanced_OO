package eecs2030.lab6;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestWeatherStation {

	private WeatherStation ws;
	private TemperatureSensor[] tempS;
	private PressureSensor[] presS;

	/*
	 * Create a new weather station with three temperature sensors have the average
	 * temperature of 23 degree C and four pressure sensors having average of 1017
	 * mb
	 */
	private void initilizeWS() {
		ws = new WeatherStation();
		tempS = new TemperatureSensor[3];
		tempS[0] = new TemperatureSensor(23.2);
		tempS[1] = new TemperatureSensor(22.8);
		tempS[2] = new TemperatureSensor(23.0);
		for (int i = 0; i < 3; i++)
			ws.addTempSensor(tempS[i]);

		presS = new PressureSensor[4];
		presS[0] = new PressureSensor(1016.6);
		presS[1] = new PressureSensor(1016.0);
		presS[2] = new PressureSensor(1018.0);
		presS[3] = new PressureSensor(1017.4);
		for (int i = 0; i < 4; i++)
			ws.addPressSensor(presS[i]);
	}

	/*
	 * Temperature drops and pressure increases.
	 */
	private void change1() {
		tempS[0].set(22);
		tempS[1].set(22.5);
		tempS[2].set(21.5);

		presS[0].set(1019);
		presS[1].set(1018);
		presS[2].set(1018.5);
		presS[3].set(1019.5);
	}

	/*
	 * Temperature increases and pressure decreases.
	 */
	private void change2() {
		tempS[0].set(26);
		tempS[1].set(26.2);
		tempS[2].set(25.8);

		presS[0].set(1014);
		presS[1].set(1013);
		presS[2].set(1012);
		presS[3].set(1011);
	}

	@Test
	public void testWeatherStation1() {
		/* test get temperature and pressure */
		initilizeWS();
		assertEquals(23.0, ws.getTemperature(), 0.01);
		assertEquals(1017.0, ws.getPressure(), 0.01);
	}

	@Test
	public void testWeatherStation11() {
		/* test get temperature and pressure */
		initilizeWS();
		ws.removeTempSensor(tempS[1]);
		assertEquals(23.1, ws.getTemperature(), 0.01);
		ws.removePressSensor(presS[1]);
		ws.removePressSensor(presS[3]);
		assertEquals(1017.3, ws.getPressure(), 0.01);
	}

	@Test
	public void testWeatherStation2() {
		initilizeWS();
		change1();
		assertEquals(22.0, ws.getTemperature(), 0.01);
		assertEquals(1018.75, ws.getPressure(), 0.01);
	}

	@Test
	public void testWeatherStation3() {
		initilizeWS();
		WeatherObserver obs1 = new StatisticsApp();
		WeatherObserver obs2 = new ForecastApp();

		/* Both observers not yet subscribed to the weather station */
		assertNull(obs1.getWeatherStation());
		assertNull(obs2.getWeatherStation());
		WeatherObserver[] observers = ws.getObservers();
		assertEquals(0, observers.length);

		/* Subscribe both observers to the weather station */
		ws.subscribe(obs1);
		ws.subscribe(obs2);
		assertTrue(ws == obs1.getWeatherStation());
		assertTrue(ws == obs2.getWeatherStation());
		observers = ws.getObservers();
		assertEquals(2, observers.length);
		assertTrue(observers[0] == obs1);
		assertTrue(observers[1] == obs2);

		/* Unsubscribe an observer */
		ws.unsubscribe(obs1);
		assertNull(obs1.getWeatherStation());
		assertTrue(ws == obs2.getWeatherStation());
		observers = ws.getObservers();
		assertEquals(1, observers.length);
		assertTrue(observers[0] == obs2);

		/* Unsubscribe an observer */
		ws.unsubscribe(obs2);
		assertNull(obs1.getWeatherStation());
		assertNull(obs2.getWeatherStation());
		observers = ws.getObservers();
		assertEquals(0, observers.length);
	}

	@Test
	public void testWeatherStation4() {
		initilizeWS();
		StatisticsApp obs1 = new StatisticsApp();

		/* Subscribe an observer to the weather station */
		ws.subscribe(obs1);

		/* publish the latest readings to subscribed observers */
		ws.publish();

		/* Getting observer calculations based on one reading */
		assertEquals(23.0, obs1.getMinTemperature(), 0.01);
		assertEquals(23.0, obs1.getMaxTemperature(), 0.01);
		assertEquals(23.0, obs1.getAverageTemperature(), 0.01);

		/*
		 * Temperature drops and pressure increases.
		 */
		change1();

		/* publish the latest readings to both observers */
		ws.publish();

		/* Getting observer calculations based on two readings */
		assertEquals(22.0, obs1.getMinTemperature(), 0.01);
		assertEquals(23.0, obs1.getMaxTemperature(), 0.01);
		assertEquals(22.5, obs1.getAverageTemperature(), 0.01);
	}

	@Test
	public void testWeatherStation5() {
		initilizeWS();
		ForecastApp obs2 = new ForecastApp();

		/* Subscribe an observer to the weather station */
		ws.subscribe(obs2);

		/* publish the latest readings to subscribed observers */
		ws.publish();

		/* Getting observer calculations based on one reading */
		assertEquals(1017.0, obs2.getCurrentPressure(), 0.01);
		assertEquals(1017.0, obs2.getLastPressure(), 0.01);
		assertFalse(obs2.isLikelyToRain());

		/*
		 * Temperature drops and pressure drops.
		 */
		change1();

		/* publish the latest readings to both observers */
		ws.publish();

		/* Getting observer calculations based on two readings */
		assertEquals(1018.75, obs2.getCurrentPressure(), 0.01);
		assertEquals(1017.0, obs2.getLastPressure(), 0.01);
		assertFalse(obs2.isLikelyToRain());

		change2();

		/* publish the latest readings to both observers */
		ws.publish();

		assertEquals(1012.5, obs2.getCurrentPressure(), 0.01);
		assertEquals(1018.75, obs2.getLastPressure(), 0.01);
		assertTrue(obs2.isLikelyToRain());
	}

	@Test
	public void testWeatherStation6() {
		WSensor s = new TemperatureSensor(20);
		int max_iter = 10000;
		/* read the temperature many times until the sensor fails */
		int i = 0;
		while (i < max_iter) {
			i++;
			try {
				s.read();
			} catch (SensorFailedException sfe) {
				System.out.println(sfe.getSensor().getClass().getSimpleName() + " has failed");
				break;
			}
		}
		assertFalse("The temperature sensor shall have failed", i == max_iter);
		s.fix();
		try {
			s.read();
			System.out.println("... and now is fixed");
		} catch (SensorFailedException sfe) {
			fail("The temperature sensor was not fixed!");
		}

		s = new PressureSensor(1020);
		/* read the temperature many times until the sensor fails */
		while (i < max_iter) {
			i++;
			try {
				s.read();
			} catch (SensorFailedException sfe) {
				System.out.println(sfe.getSensor().getClass().getSimpleName() + " has failed");
				break;
			}
		}
		assertFalse("The pressure sensor shall have failed", i == max_iter);
		s.fix();
		try {
			s.read();
			System.out.println("... and now is fixed");
		} catch (SensorFailedException sfe) {
			fail("The pressure sensor was not fixed!");
		}
	}
}
