package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

public class ShipClass {
	
	private List<Ability>		abilities;
	private List<String>		types = new Vector<String>();
	private int					maxhp;
	private int					radius;
	private int					placement_cost;
	private String				imageid;
	private String				gid;
	
	private transient Image		image;

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPlacement_cost() {
		return placement_cost;
	}

	public void setPlacement_cost(int placement_cost) {
		this.placement_cost = placement_cost;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public Image getImage() {
		return image;
	}

	/**
	 * Creates and maps to an ShipClass object.
	 * 
	 * @return The mapped ShipClass as an ShipClass object. or null if error.
	 */
	public static ShipClass fromJSON(String incomingJson){
		Gson gson = new Gson();
		return gson.fromJson(incomingJson, ShipClass.class);
	}

	/**
	 * Creates a JSON file from an ShipClass object.
	 * 
	 * @param shipClass
	 */
	public void toJSON(ShipClass shipClass) {
		Gson gson = new Gson();
		String json = gson.toJson(shipClass);
		
		try {
			FileWriter file = new FileWriter(new File("ShipClassFromJava.json"));
			file.write(json);
			file.close();
		} catch(IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public ShipClass() {
		abilities	= new Vector<Ability>();
		
		//TODO load with json
		try {
			ImageIO.read(new File("assets/ship.png"));
		} catch (IOException e) {
			System.err.println("Unable to load ship image");
		}
	}
	
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public String getImageid() {
		return imageid;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public List<String> getTypes() {
		return types;
	}
	
	public boolean equals(Object other) {
		ShipClass ot = (ShipClass) other;
		return (abilities.equals(ot.getAbilities()) && (types.equals(ot.getTypes())) && (maxhp == ot.getMaxhp()) && (radius == ot.getRadius()) && (imageid.equals(ot.getImageid())) && (gid.equals(ot.getGid())));
	}
	
}
