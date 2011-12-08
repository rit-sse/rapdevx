package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.rit.se.sse.rapdevx.api.json.ShipClassIdConverter;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;
import edu.rit.se.sse.rapdevx.gui.Sprite;

/**
 * POJO representing the location of a ship on the server side of things. Will
 * be annotated in order to have Jackson data bindings.
 * 
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class ShipPlacement implements Cloneable {
	
	private int x;
	private int y;
	private ShipClass shipClass;
	
	//TODO actually get from asset library
	private final transient Sprite image = new Sprite("assets/ship.png");
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getClassid() {
		return shipClass.getGid();
	}

	public void setClassid(String classid) {
		this.shipClass = null;
		
		for (ShipClass shipClass : AssetLibrary.getShipClasses()) {
			if (shipClass.getGid().equals(classid)) {
				this.shipClass = shipClass;
				return;
			}
		}
		
		//TODO throw/print error
	}
	
	public void draw(Graphics2D gPen) {
		image.draw(gPen, x, y, 4);
	}

	/**
	 * Creates and maps to an ShipPlacement object.
	 * 
	 * @return The mapped ShipPlacement as an ShipPlacement object. or null if
	 *         error.
	 */
	public static ShipPlacement fromJSON(String incomingJson) {
		// Load the json by looking up a ship class by id in the asset library
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(ShipClass.class, new ShipClassIdConverter());
		
		Gson gson = builder.create();
		return gson.fromJson(incomingJson, ShipPlacement.class);
	}

	/**
	 * Creates a JSON file from an ShipPlacement object.
	 * 
	 * @param ShipPlacement
	 */
	public void toJSON(ShipPlacement shipPlacement) {
		Gson gson = new Gson();
		String json = gson.toJson(shipPlacement);

		try {
			FileWriter file = new FileWriter(new File("ShipPlacementFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public ShipPlacement() {

	}
	
	public ShipPlacement(int x, int y, ShipClass shipClass) {
		this.x = x;
		this.y = y;
		this.shipClass = shipClass;
	}
	
	public boolean equals(Object other) {
		ShipPlacement ot = (ShipPlacement) other;
		return (x == ot.getX()) && (y == ot.getY()) && (shipClass.getGid().equals(ot.getClassid()));
	}
	
	public ShipPlacement clone() {
		return new ShipPlacement(x, y, shipClass);
	}

}
