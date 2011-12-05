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
public class AbilityUseOrders {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private List<AbilityUseOrder> orders;
	
    public List<AbilityUseOrder> getOrders() {
        return orders;
    }
    public void setGames(List<AbilityUseOrder> orders) {
        this.orders = orders;
    }
	
	/**
	 * Creates and maps to an Status object.
	 * 
	 * @return The mapped Status as an Status object. or null if error.
	 */
	public static AbilityUseOrders fromJSON(String incomingJson){

		try {
			AbilityUseOrders orders = mapper.readValue(incomingJson, AbilityUseOrders.class);
			return orders;
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
	public void toJSON(AbilityUseOrders orders){
		try {
			mapper.writeValue(new File("AbilityUseOrdersFromJava.json"), orders);
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



