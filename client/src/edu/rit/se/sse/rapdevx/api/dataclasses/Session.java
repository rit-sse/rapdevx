package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * POJO representing a Session on the server side of things. Will be annotated
 * in order to have Jackson data bindings.
 * 
 * @author Ben Nicholas
 */
public class Session {

	private static ObjectMapper	mapper	= new ObjectMapper();

	private String				session_id;
	private String				nickname;
	private String				game_id;
	private boolean				active;
	private int					player_num;

	public String getsession_id() {
		return session_id;
	}

	public void setsession_id(String gid) {
		this.session_id = gid;
	}

	public String getnickname() {
		return nickname;
	}

	public void setnickname(String nickName) {
		this.nickname = nickName;
	}

	public String getgame_id() {
		return game_id;
	}

	public void setgame_id(String gameID) {
		this.game_id = gameID;
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
	public static Session fromJSON(String incomingJson) {

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
	public String toJSON(Session session) {
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

	public Session() {

	}

	/**
	 * @return the player_num
	 */
	public int getPlayer_num() {
		return player_num;
	}

	/**
	 * @param player_num
	 *            the player_num to set
	 */
	public void setPlayer_num(int player_num) {
		this.player_num = player_num;
	}

}
