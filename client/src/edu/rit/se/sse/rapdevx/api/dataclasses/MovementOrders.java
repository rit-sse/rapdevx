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
public class MovementOrders {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private List<MovementOrder> orders;
	
    public List<MovementOrder> getOrders() {
        return orders;
    }
    public void setGames(List<MovementOrder> orders) {
        this.orders = orders;
    }
	
	/**
	 * Creates and maps to an Status object.
	 * 
	 * @return The mapped Status as an Status object. or null if error.
	 */
	public static MovementOrders fromJSON(String incomingJson){

		try {
			MovementOrders statuses = mapper.readValue(incomingJson, MovementOrders.class);
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
	public void toJSON(MovementOrders orders){
		try {
			mapper.writeValue(new File("MovementOrdersFromJava.json"), orders);
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

