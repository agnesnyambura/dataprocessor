package adapter;

import java.net.URL;

/**
 * @author anjoroge
 *
 *This interface contains methods that are used to initialize and calculate statistics from a given URL
 *
 */
public interface DataProcessor {
	
	public void initialize(URL url);
	public void printAllWaterPoints();
	public void printWaterPointsInCommnunity(String communityName);
	public void printStatisticsForCommnunity(String communityName);
	public void calculateStats();
	public void printAllStats();
	public String getStats();
	
	
}
