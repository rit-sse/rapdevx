package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing the location of a ship on the server side of 
 * things. Will be annotated in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class ShipPlacement {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private int x;
	private int y;
	private String classid;
	
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
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	

	/**
	 * Creates and maps to an ShipPlacement object.
	 * 
	 * @return The mapped ShipPlacement as an ShipPlacement object. or null if error.
	 */
	public static ShipPlacement fromJSON(String incomingJson){

		try {
			ShipPlacement shipPlacement = mapper.readValue(incomingJson, ShipPlacement.class);
			return shipPlacement;
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
	 * Creates a JSON file from an ShipPlacement object.
	 * 
	 * @param ShipPlacement
	 */
	public void toJSON(ShipPlacement shipPlacement){
		try {
			mapper.writeValue(new File("ShipPlacementFromJava.json"), shipPlacement);
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
	
	public ShipPlacement(){
		
	}
	
}

