package edu.rit.se.sse.rapdevx.api.dataclasses;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Ability {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private int radius;
	private String name;
	private int default_damage;
	private Map<String,Integer> special_damages = new Hashtable<String,Integer>();
	private String gid;
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String,Integer> getSpecial_damages() {
		return special_damages;
	}
	public void setSpecial_damages(Map<String,Integer> special_damages) {
		this.special_damages = special_damages;
	}
	public int getDefault_damage() {
		return default_damage;
	}
	public void setDefault_damage(int default_damage) {
		this.default_damage = default_damage;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	

	/**
	 * Creates and maps to an Ability object.
	 * 
	 * @return The mapped Ability as an Ability object. or null if error.
	 */
	public static Ability fromJSON(String incomingJson){

		try {
			Ability ability = mapper.readValue(incomingJson, Ability.class);
			return ability;
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
	 * Creates a JSON file from an Ability object.
	 * 
	 * @param Ability
	 */
	public void toJSON(Ability ability){
		try {
			mapper.writeValue(new File("AbilityFromJava.json"), ability);
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
	
	public Ability(){
		
	}
	
}
