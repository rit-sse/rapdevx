package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing a Unit's Move on the server side of things. Will
 * be annotate in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class MovementOrder {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private String unitID;
	private LinkedList<Hashtable<Integer,Integer>> path = new LinkedList<Hashtable<Integer,Integer>>();
	private String gid;
	
	public String getUnitID() {
		return unitID;
	}
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
	public LinkedList<Hashtable<Integer,Integer>> getPath() {
		return path;
	}
	public void setPath(LinkedList<Hashtable<Integer,Integer>> path) {
		this.path = path;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	

	/**
	 * Creates and maps to an MovementOrder object.
	 * 
	 * @return The mapped MovementOrder as an MovementOrder object. or null if error.
	 */
	public static MovementOrder fromJSON(String incomingJson){

		try {
			MovementOrder movementOrder = mapper.readValue(incomingJson, MovementOrder.class);
			return movementOrder;
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
	 * Creates a JSON file from an MovementOrder object.
	 * 
	 * @param MovementOrder
	 */
	public void toJSON(MovementOrder movementOrder){
		try {
			mapper.writeValue(new File("MovementOrderFromJava.json"), movementOrder);
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
	
	public MovementOrder(){
		
	}
	
	
	
}

