package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ShipClass {

	private static ObjectMapper	mapper		= new ObjectMapper();
	private List<Ability>		abilities	= new Vector<Ability>();
	private int					maxhp;
	private int					radius;
	private int					placement_cost;
	private String				gid;

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

	/**
	 * Creates and maps to an ShipClass object.
	 * 
	 * @return The mapped ShipClass as an ShipClass object. or null if error.
	 */
	public static ShipClass fromJSON() {

		try {
			ShipClass shipClass = mapper.readValue(new File(
					"ShipClassToJava.json"), ShipClass.class);
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

	}

}
