package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ShipClass {

	private static ObjectMapper	mapper		= new ObjectMapper();
	private List<Ability>		abilities;
	private List<String>		types = new Vector<String>();
	private int					maxhp;
	private int					radius;
	private int					placement_cost;
	private String				imageid;
	private String				gid;
	
	private Image				image;

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

		try {
			ShipClass shipClass = mapper.readValue(incomingJson, ShipClass.class);
			return shipClass;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates a JSON file from an ShipClass object.
	 * 
	 * @param shipClass
	 */
	public void toJSON(ShipClass shipClass) {
		try {
			mapper.writeValue(new File("ShipClassFromJava.json"), shipClass);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
