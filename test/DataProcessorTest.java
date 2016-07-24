import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import adapter.BuildDataProcessor;
import adapter.DataProcessor;

public class DataProcessorTest {

	final String url = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";

	@Test
	public void test() {

		DataProcessor dataProcessor = new BuildDataProcessor();
		try {
			dataProcessor.initialize(new URL(url));

			dataProcessor.printAllWaterPoints();
			//dataProcessor.printWaterPointsInCommnunity("Selinvoya");

			dataProcessor.calculateStats();
			//dataProcessor.printAllStats();

			String stats = dataProcessor.getStats();
			
			System.out.println(stats);
			assertNotNull(stats);
			assertTrue(stats.length() > 0);

		} catch (MalformedURLException e) {

			System.out
					.println("Invalid URL. \nValid URLs start with 'http://www...' and so on. ");

		}

	}

}
