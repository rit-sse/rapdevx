package edu.rit.se.sse.rapdevx.api.dataclasses;
import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing the asset image on the server side of 
 * things. Will be annotated in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class AssetImage {

	private static ObjectMapper mapper = new ObjectMapper();
	private String file;
	private String gid;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	/**
	 * Creates and maps to an AssetImage object.
	 * 
	 * @return The mapped AssetImage as an AssetImage object. or null if error.
	 */
	public static AssetImage fromJSON(String incomingJson){

		try {
			AssetImage assets = mapper.readValue(incomingJson, AssetImage.class);
			return assets;
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
	 * Creates a JSON file from an Assets object.
	 * 
	 * @param assets
	 */
	public void toJSON(AssetImage assets){
		try {
			mapper.writeValue(new File("AssetImageFromJava.json"), assets);
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
	
	public AssetImage(){
		
	}
	
}
