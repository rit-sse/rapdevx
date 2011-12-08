package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.rit.se.sse.rapdevx.api.json.ShipClassIdConverter;

/**
 * POJO representing the status of a Game on the server side of things. Will be
 * annotated in order to have Jackson data bindings.
 * 
 * @author Ben Nicholas
 */
public class Units {

	private List<Unit> units;

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	/**
	 * } Creates and maps to an Status object.
	 * 
	 * @return The mapped Status as an Status object. or null if error.
	 */
	public static Units fromJSON(String incomingJson) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(ShipClass.class, new ShipClassIdConverter());
		
		Gson gson = builder.create();
		return gson.fromJson(incomingJson, Units.class);
	}

	/**
	 * Creates a JSON file from an Status object.
	 * 
	 * @param Status
	 */
	public void toJSON(Units units) {
		Gson gson = new Gson();
		String json = gson.toJson(units);

		try {
			FileWriter file = new FileWriter(new File("UnitsFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public Units() {

	}

	public Units(List<Unit> units) {
		this.units = units;
	}

}
