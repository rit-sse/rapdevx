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

	private static ObjectMapper mapper = new ObjectMapper();
	
	private String srcid;
	private String targetid;
	private String ability;
	private String gid;
	
	public String getSrcid() {
		return srcid;
	}
	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getgid() {
		return gid;
	}
	public void setgid(String gid) {
		this.gid = gid;
	}
	

	/**
	 * Creates and maps to an AbilityUseOrder object.
	 * 
	 * @return The mapped AbilityUseOrder as an AbilityUseOrder object. or null if error.
	 */
	public static AbilityUseOrder fromJSON(String incomingJson){

		try {
			AbilityUseOrder abilityUseOrder = mapper.readValue(incomingJson, AbilityUseOrder.class);
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

