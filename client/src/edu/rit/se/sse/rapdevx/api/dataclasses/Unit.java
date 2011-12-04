package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing an instance of a unit/ship.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class Unit {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private int player_num;
	private int hp;
	private String classID;
	private String gID;
	
	public int getPlayer_num() {
		return player_num;
	}
	public void setPlayer_num(int player_num) {
		this.player_num = player_num;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getgID() {
		return gID;
	}
	public void setgID(String gID) {
		this.gID = gID;
	}
	

	/**
	 * Creates and maps to an Unit object.
	 * 
	 * @return The mapped Unit as an Unit object. or null if error.
	 */
	public static Unit fromJSON(String incomingJson){

		try {
			Unit unit = mapper.readValue(incomingJson, Unit.class);
			return unit;
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
	 * Creates a JSON file from an Unit object.
	 * 
	 * @param Unit
	 */
	public void toJSON(Unit unit){
		try {
			mapper.writeValue(new File("UnitFromJava.json"), unit);
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
	
	public Unit(){
		
	}
	
}

