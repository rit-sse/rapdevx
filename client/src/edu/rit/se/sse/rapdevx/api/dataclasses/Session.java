package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing a Session on the server side of things. Will be 
 * annotated in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 */
public class Session {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private String gid;
	private String nickName;
	private String gameID;
	private boolean active;
	
	
	public String getgid() {
		return gid;
	}
	public void setgid(String gid) {
		this.gid = gid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGameID() {
		return gameID;
	}
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}



	/**
	 * Creates and maps to an Session object.
	 * 
	 * @return The mapped Session as an Session object. or null if error.
	 */
	public static Session fromJSON(String incomingJson){

		try {
			Session session = mapper.readValue(incomingJson, Session.class);
			return session;
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
	 * Creates a JSON file from an Session object.
	 * 
	 * @param Session
	 */
	public String toJSON(Session session){
		try {
			mapper.writeValue(new File("SessionFromJava.json"), session);
			return mapper.writeValueAsString(session);
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
		
		return null;
	}
	
	public Session(){
		
	}
	
}

