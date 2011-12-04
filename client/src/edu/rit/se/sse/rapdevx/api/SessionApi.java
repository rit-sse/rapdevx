package edu.rit.se.sse.rapdevx.api;

// TODO: add Exceptions for unexpected situations, like the session being not found.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import edu.rit.se.sse.rapdevx.api.dataclasses.Session;

/**
 * API access to the Session object on the server side
 *
 * Approximates the ActiveRecord pattern
 *
 * @author Ben Nicholas
 */
public class SessionApi {
	public static String SERVER_URL = "localhost";
	
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
	 * @param gameID The game that the user would like to join.  If 
	 * there is no preference, pass in null.
	 *
	 * @return The initial session object, potentially populated with game_id.
	 * If no game_id is provided, periodically check for updates until the 
	 * session has been given a game.
	 * @throws Exception Talk to cody about this.
	 */
	public static Session createSession(String nickname, String gameID) throws Exception {
		// Connect to session
		Map<String, String> json = getJsonMap(
				getResponse("/game/" + gameID));
		String incomingJson = "";
		// pull values
		json.get("id"); // Don't know what to get
		
		try {
			//contents of the POST
			String data = URLEncoder.encode("nickname", "UTF-8") + "=" + URLEncoder.encode(nickname, "UTF-8");
		    
			//if there is a gameID
			if(gameID != null)data += "&" + URLEncoder.encode("gameID", "UTF-8") + "=" + URLEncoder.encode(gameID, "UTF-8");
			//url...
			URL url = new URL("http", SERVER_URL, 8080, "/sessions");
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");

            conn.connect();

		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();
			
		 // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        incomingJson += line;
		    }
		    wr.close();
		    rd.close();
		    
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(incomingJson.equals("")){
			throw new Exception("TALK TO CODY");
		}else{
			//need to pull the change for the below method.
			return Session.fromJSON(incomingJson);
		}
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
		String json = getResponse("/session/" + sessionToCheck.getgid());
		
		Session session = Session.fromJSON(json);
		
		return session;
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
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(SERVER_URL).openConnection());
			con.setDoInput(true);
			con.setRequestMethod("DELETE");
			
		 // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String line; String incomingJson = "";
		    while ((line = rd.readLine()) != null) {
		        incomingJson += line;
		    }
		    rd.close();
		    
		    if (!incomingJson.equals(""))
		    	return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		//createSession("Derp", null);
	}
}
