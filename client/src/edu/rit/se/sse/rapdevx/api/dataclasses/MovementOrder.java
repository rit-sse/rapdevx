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
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private String unitID;
	private LinkedList<Hashtable<Integer,Integer>> PATH = new LinkedList<Hashtable<Integer,Integer>>();
	private String gid;
	
	public String getUnitID() {
		return unitID;
	}
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
	public LinkedList<Hashtable<Integer,Integer>> getPATH() {
		return PATH;
	}
	public void setPATH(LinkedList<Hashtable<Integer,Integer>> pATH) {
		PATH = pATH;
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
	public MovementOrder fromJSON(){

		try {
			MovementOrder movementOrder = mapper.readValue(new File("MovementOrderToJava.json"), MovementOrder.class);
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

