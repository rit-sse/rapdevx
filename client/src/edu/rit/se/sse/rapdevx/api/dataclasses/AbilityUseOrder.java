package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing a Unit's Attack on the server side of things. Will
 * be annotate in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class AbilityUseOrder {

	private ObjectMapper mapper = new ObjectMapper();
	
	private String srcID;
	private String targetID;
	private String ability;
	private String gID;
	
	public String getSrcID() {
		return srcID;
	}
	public void setSrcID(String srcID) {
		this.srcID = srcID;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
	public String getgID() {
		return gID;
	}
	public void setgID(String gID) {
		this.gID = gID;
	}
	

	/**
	 * Creates and maps to an AbilityUseOrder object.
	 * 
	 * @return The mapped AbilityUseOrder as an AbilityUseOrder object. or null if error.
	 */
	public AbilityUseOrder fromJSON(){

		try {
			AbilityUseOrder abilityUseOrder = mapper.readValue(new File("AbilityUseOrderToJava.json"), AbilityUseOrder.class);
			return abilityUseOrder;
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
	 * Creates a JSON file from an AbilityUseOrder object.
	 * 
	 * @param AbilityUseOrder
	 */
	public void toJSON(AbilityUseOrder abilityUseOrder){
		try {
			mapper.writeValue(new File("AbilityUseOrderFromJava.json"), abilityUseOrder);
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
	
	public AbilityUseOrder(){
		
	}
	
	
}
