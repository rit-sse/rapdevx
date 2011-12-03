package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing the status of a Game on the server side of 
 * things. Will be annotated in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 */
public class Status {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private int turn;
	private int phase;
	private int me;
	
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getMe() {
		return me;
	}
	public void setMe(int me) {
		this.me = me;
	}
	
	/**
	 * Creates and maps to an Status object.
	 * 
	 * @return The mapped Status as an Status object. or null if error.
	 */
	public Status fromJSON(){

		try {
			Status status = mapper.readValue(new File("StatusToJava.json"), Status.class);
			return status;
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
	 * Creates a JSON file from an Status object.
	 * 
	 * @param Status
	 */
	public void toJSON(Status status){
		try {
			mapper.writeValue(new File("StatusFromJava.json"), status);
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
	
	public Status(){
		
	}
	
}

