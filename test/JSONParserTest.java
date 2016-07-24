import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import model.WaterPoint;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


/**
 * @author anjoroge
 *
 *This Test case checks that the JSON returned from the URL is valid.
 *Valid JSON must contain the 2 required fields "water_functioning" & "communities_villages"
 *
 */
public class JSONParserTest {

	@Test
	public void test() {
		JsonFactory factory = new JsonFactory();
		String validFields = "";
		try {
			JsonParser parser = factory
					.createParser(new URL(
							"https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json"));
	
			while (!parser.isClosed()) {

				JsonToken jsonToken = parser.nextToken();

				if (JsonToken.FIELD_NAME.equals(jsonToken)) {
					String fieldName = parser.getCurrentName();

					while (!jsonToken.END_OBJECT.equals(jsonToken)) {
						jsonToken = parser.nextToken();
						fieldName = parser.getCurrentName();

						if ("water_functioning".equals(fieldName)
								|| "communities_villages".equals(fieldName)) {
							validFields += fieldName + " ";
						}
					}
				}
			}
			if (validFields.contains("water_functioning")
					&& validFields.contains("communities_villages")) {

				System.out.println("Fields valid");
			} else {
				System.out
						.println("The required fields water_functioning & communities_villages are not in the JSON");
				fail("The required fields water_functioning & communities_villages are not in the JSON");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
