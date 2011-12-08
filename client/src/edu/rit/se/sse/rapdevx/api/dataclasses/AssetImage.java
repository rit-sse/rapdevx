package edu.rit.se.sse.rapdevx.api.dataclasses;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * POJO representing the asset image on the server side of 
 * things. Will be annotated in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class AssetImage {
	
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
		Gson gson = new Gson();
		return gson.fromJson(incomingJson, AssetImage.class);
	}
	
	/**
	 * Creates a JSON file from an Assets object.
	 * 
	 * @param assets
	 */
	public void toJSON(AssetImage assetImage){
		Gson gson = new Gson();
		String json = gson.toJson(assetImage);

		try {
			FileWriter file = new FileWriter(new File("AssetImageFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}
	
	public AssetImage(){
		
	}
	
	public boolean equals(Object other) {
		AssetImage ot = (AssetImage) other;
		return file.equals(ot.getFile()) && gid.equals(ot.getGid());
	}
	
}
