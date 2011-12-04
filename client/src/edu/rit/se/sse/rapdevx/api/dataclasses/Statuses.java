package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
public class Statuses {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private List<Status> games;
	
    public List<Status> getGames() {
        return games;
    }
    public void setGames(List<Status> games) {
        this.games = games;
    }
	
	/**
	 * Creates and maps to an Status object.
	 * 
	 * @return The mapped Status as an Status object. or null if error.
	 */
	public static Statuses fromJSON(String incomingJson){

		try {
			Statuses statuses = mapper.readValue(incomingJson, Statuses.class);
			return statuses;
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
	public void toJSON(Statuses statuses){
		try {
			mapper.writeValue(new File("StatusesFromJava.json"), statuses);
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
	
}


