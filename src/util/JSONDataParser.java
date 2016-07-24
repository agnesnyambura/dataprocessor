package util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import model.WaterPoint;

public class JSONDataParser {

	/**
	 * This method initializes the records for the waterpoints from a source URL
	 * @param url - The URL which is the source of the JSON data set to be parsed
	 * @return A LinkedHashMap containing the records of the water points per community. 
	 * 			Key: The community name
	 * 			Value: ArrayList of WaterPoint objects for that community
	 */
	public LinkedHashMap<String, ArrayList<WaterPoint>> initialize(URL url) {
		ArrayList<WaterPoint> waterPoints;
		LinkedHashMap<String, ArrayList<WaterPoint>> allData = new LinkedHashMap<>();

		JsonFactory factory = new JsonFactory();

		try {
			JsonParser parser = factory.createParser(url);

			while (!parser.isClosed()) {

				JsonToken jsonToken = parser.nextToken();
				waterPoints = new ArrayList<WaterPoint>();
				
				if (JsonToken.FIELD_NAME.equals(jsonToken)) {
					String fieldName = parser.getCurrentName();

					WaterPoint waterPoint = new WaterPoint();

					while (!jsonToken.END_OBJECT.equals(jsonToken)) {

						jsonToken = parser.nextToken();
						fieldName = parser.getCurrentName();

						if ("communities_villages".equals(fieldName)) {
							waterPoint.setCommunityVillages(parser
									.getValueAsString());

						} else if ("_uuid".equals(fieldName)) {
							waterPoint.setUuid(parser.getValueAsString());

							fieldName = parser.getCurrentName();

						} else if ("water_functioning".equals(fieldName)) {
							if (parser.getValueAsString().equalsIgnoreCase(
									"yes"))
								waterPoint.setFunctioning(true);
							else
								waterPoint.setFunctioning(false);
						}

					}

					if (allData.containsKey(waterPoint.getCommunityVillages())) {
						waterPoints = allData.get(waterPoint
								.getCommunityVillages());
						waterPoints.add(waterPoint);

						allData.put(waterPoint.getCommunityVillages(),
								waterPoints);

					} else {
						waterPoints.add(waterPoint);

						allData.put(waterPoint.getCommunityVillages(),
								waterPoints);

					}

				}

			}

		} catch (JsonParseException e) {

			System.out
					.println("The URL does not contain valid JSON.");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out
			.println("Could not connect to the URL. Check if you have an internet connection.");
			//e.printStackTrace();
		}

		return allData;
	}

}
