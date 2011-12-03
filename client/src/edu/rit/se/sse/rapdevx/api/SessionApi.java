package edu.rit.se.sse.rapdevx.api;

// TODO: add Exceptions for unexpected situations, like the session being not found.

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import edu.rit.se.sse.rapdevx.api.dataclasses.Game;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;

/**
 * API access to the Session object on the server side
 *
 * Approximates the ActiveRecord pattern
 *
 * @author Ben Nicholas
 */
public class SessionApi {
	public static String SERVER_URL = "http://localhost:8080";
	
	private static HttpClient HTTP_CLIENT = new DefaultHttpClient();
	private static ResponseHandler<String> HANDLER = new BasicResponseHandler();
	private static ObjectMapper MAPPER = new ObjectMapper(new JsonFactory());
	private static TypeReference<Map<String, String>> STRING_MAP_TYPE =
			new TypeReference<Map<String, String>>() { };
	
	/**
	 * Create a new session, optionally specifying a game to join.  If no game 
	 * is provided, the session will be registered into the matchmaking pool 
	 * on the server side and be assigned to a game.
	 *
	 * @param requestedGame The game that the user would like to join.  If 
	 * there is no preference, pass in null.
	 *
	 * @return The initial session object, potentially populated with game_id.
	 * If no game_id is provided, periodically check for updates until the 
	 * session has been given a game.
	 */
	public static Session createSession(String nickname, Game requestedGame) {
		// Connect to session
		Map<String, String> json = getJsonMap(
				getResponse("/game/" + requestedGame.getId()));
		
		// pull values
		json.get("id"); // Don't know what to get
		
		// encode into request
		
		return new Session(); // Don't know
	}

	/**
	 * Check for updates to the given session, primarily used while waiting for 
	 * a game assignment. 
	 *
	 * @param sessionToCheck The session to check for updates(session_id is the 
	 * only important part)
	 *
	 * @return A newly allocated session object, with the server's current view 
	 * of the provided session.
	 */
	public static Session updateSession(Session sessionToCheck) {
		
		
		return null;
	}

	/**
	 * "Log out" of your current game session.
	 *
	 * @param sessionToDestroy The session to be destroyed(session_id is the 
	 * only important part)
	 *
	 * @return A boolean representing whether or not the destroy operation was 
	 * successful.
	 */
	public static boolean destroySession(Session sessionToDestroy) {
		HttpDelete delete =
				new HttpDelete(SERVER_URL + "/session/" + sessionToDestroy.getId());
		
		return false;
	}
	
	/**
	 * Helper method.
	 * 
	 * Gets body for request.
	 * 
	 * @param args Part after domain (like /game or /session/1)
	 * @return
	 */
	private static String getResponse(String args) {
		HttpGet get = new HttpGet(SERVER_URL + args);
		String response;
		
		try {
			return HTTP_CLIENT.execute(get, HANDLER);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static Map<String, String> getJsonMap(String content) {
		try {
			return MAPPER.readValue(content, STRING_MAP_TYPE);
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
	
	public static void main(String[] argv) {
		createSession("Derp", null);
	}
}
