package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Hashtable;
import java.util.LinkedList;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Results {

	private ObjectMapper mapper = new ObjectMapper();
	
	private Map<Integer,LinkedList<Integer>> results = new Hashtable<Integer,LinkedList<Integer>>();

	public Map<Integer,LinkedList<Integer>> getResults() {
		return results;
	}

	public void setResults(Map<Integer,LinkedList<Integer>> results) {
		this.results = results;
	}
	

	/**
	 * Creates and maps to an Results object.
	 * 
	 * @return The mapped Results as an Results object. or null if error.
	 */
	public Results fromJSON(){

		try {
			Results results = mapper.readValue(new File("ResultsToJava.json"), Results.class);
			return results;
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
	 * Creates a JSON file from an Results object.
	 * 
	 * @param Results
	 */
	public void toJSON(Results results){
		try {
			mapper.writeValue(new File("ResultsFromJava.json"), results);
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
	
	public Results(){
		
	}
	
	
	
}
