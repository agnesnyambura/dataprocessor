package adapter;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import util.JSONDataParser;
import model.CommunityStatistics;
import model.WaterPoint;

/**
 * @author anjoroge
 *
 *         This is a proxy class. It is an abstract class that holds the
 *         attributes & methods necessary to instantiate water points records
 */
public abstract class ProxyDataProcessor {

	private LinkedHashMap<String, ArrayList<WaterPoint>> allData;
	private LinkedHashMap<String, CommunityStatistics> communityStats;

	/**
	 * This method initializes the hashmap with records of water points from a
	 * source URL
	 * 
	 * @param url
	 *            - The URL which is the source for the JSON file containing the
	 *            records in the data set
	 */
	public void initialize(URL url) {

		JSONDataParser jsonDataParser = new JSONDataParser();
		allData = jsonDataParser.initialize(url);

	}

	/**
	 * This method calculates the statistics for a given set of water point
	 * dataset The statistics calculated are: 1. Total num of waterpoints per
	 * community 2. Number of functional waterpoints per community 3. % of
	 * faulty waterpoints per community
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void calculateStats() {

		communityStats = new LinkedHashMap<>();
		for (Map.Entry<String, ArrayList<WaterPoint>> entry : allData
				.entrySet()) {
			CommunityStatistics stats = new CommunityStatistics();

			int allPoints = entry.getValue().size();
			stats.setNumOfWaterPoints(allPoints);

			int funtional = 0;
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).isFunctioning())
					funtional += 1;
			}

			stats.setNumFunctional(funtional);

			double percentBroken = 0.0;
			percentBroken = ((double) (allPoints - funtional) / allPoints) * 100.0;

			stats.setPercentOfBrokenPoints(percentBroken);

			if (!communityStats.containsKey(entry.getKey())) {
				communityStats.put(entry.getKey(), stats);
			}

		}
	}

	/**
	 * This method prints the water points records in the given community
	 * 
	 * @param communityName
	 *            -The community name whose records are required
	 */
	public void printWaterPointsInCommnunity(String communityName) {

		if (allData.containsKey(communityName)) {
			System.out.println();
			System.out.println("WaterPoints in Community: " + communityName
					+ "\n");
			System.out.println(Arrays.toString(allData.get(communityName)
					.toArray()));
		} else {
			System.out.println("Community does not exist in the data set");
		}
	}

	/**
	 * This method prints all the statistics for each of the water points per
	 * community for all the communities in the data set
	 */
	public void printAllStats() {
		for (Map.Entry<String, CommunityStatistics> entry : communityStats
				.entrySet()) {
			System.out.println(entry.getKey() + " : "
					+ entry.getValue().toString() + "\n");
		}
	}

	/**
	 * This method prints the statistics for a given community
	 * 
	 * @param communityName
	 *            - The community whose statistics are to be printed
	 */
	public void printStatisticsForCommnunity(String communityName) {
		if (communityStats.containsKey(communityName)) {
			System.out.println();
			System.out.println("Statistics for Community: " + communityName
					+ "\n");
			System.out.println(communityStats.get(communityName).toString());
		} else {
			System.out.println("Community does not exist in the data set");
		}
	}

	/**
	 * This method prints all the records of the water points per community for
	 * all the communities in the data set
	 */
	public void printAllWaterPoints() {
		for (Map.Entry<String, ArrayList<WaterPoint>> entry : allData
				.entrySet()) {
			System.out
					.println(entry.getKey() + " : " + entry.getValue() + "\n");
		}
	}

	/**
	 * This method returns a JSON String for the statistics from the data set
	 * 
	 * @return JSON String of the statistics for the data set
	 */
	public String getStats() {
		JsonFactory factory = new JsonFactory();
		StringWriter stringWriter = new StringWriter();
		JsonGenerator generator;
		try {
			generator = factory.createGenerator(stringWriter);

			generator.writeStartArray();

			for (Map.Entry<String, CommunityStatistics> entry : communityStats
					.entrySet()) {
				generator.writeStartObject();
				generator.writeStringField("community_name", entry.getKey());
				generator.writeNumberField("total_waterpoints", entry
						.getValue().getNumOfWaterPoints());
				generator.writeNumberField("functional_waterpoints", entry
						.getValue().getNumFunctional());
				generator.writeNumberField("percent_broken", entry.getValue()
						.getPercentOfBrokenPoints());
				generator.writeEndObject();
			}
			generator.writeEndArray();
			generator.close();

			String json = stringWriter.toString();
			stringWriter.close();

			return json;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
