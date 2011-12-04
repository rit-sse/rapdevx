package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class AttackResults {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private List<AbilityUseOrder> results = new LinkedList<AbilityUseOrder>();

	public List<AbilityUseOrder> getResults() {
		return results;
	}

	public void setResults(List<AbilityUseOrder> results) {
		this.results = results;
	}
	


	/**
	 * Creates and maps to an AttackResults object.
	 * 
	 * @return The mapped AttackResults as an AttackResults object. or null if error.
	 */
	public static AttackResults fromJSON(){

		try {
			AttackResults attackResults = mapper.readValue(new File("AttackResultsToJava.json"), AttackResults.class);
			return attackResults;
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
	 * Creates a JSON file from an AttackResults object.
	 * 
	 * @param attackResults
	 */
	public void toJSON(AttackResults attackResults){
		try {
			mapper.writeValue(new File("AttackResultsFromJava.json"), attackResults);
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
	
	public AttackResults(){
		
	}
	
	
}
