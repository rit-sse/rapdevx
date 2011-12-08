package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

/**
 * To and from JSON for the Assets.
 * 
 * @author Paul Cassidy
 * 
 */
public class Assets {

	private int					width;
	private int					height;
	private List<ShipClass>		ship_classes;
	private List<AssetImage>	images;
	private List<Ability>		abilities;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<ShipClass> getShip_classes() {
		return ship_classes;
	}

	public void setShipClasses(List<ShipClass> ship_classes) {
		this.ship_classes = ship_classes;
	}

	public List<AssetImage> getImages() {
		return images;
	}

	public void setImages(List<AssetImage> images) {
		this.images = images;
	}

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}

	/**
	 * Creates and mapps to an assets object.
	 * 
	 * @return The mapped Assets as an Assets object. or null if error.
	 */
	public static Assets fromJSON(String incomingJson) {
		Gson gson = new Gson();
		return gson.fromJson(incomingJson, Assets.class);
	}

	/**
	 * Creates a JSON file from an Assets object.
	 * 
	 * @param assets
	 */
	public void toJSON(Assets assets) {
		Gson gson = new Gson();
		String json = gson.toJson(assets);

		try {
			FileWriter file = new FileWriter(new File("AssetsFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public Assets() {
		ship_classes = new LinkedList<ShipClass>();
		images = new LinkedList<AssetImage>();
		abilities = new LinkedList<Ability>();
	}

}
