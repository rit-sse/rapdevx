package edu.rit.se.sse.rapdevx.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import edu.rit.se.sse.rapdevx.api.dataclasses.AbilityUseOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.AbilityUseOrders;
import edu.rit.se.sse.rapdevx.api.dataclasses.AttackResults;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.MovementOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.MovementOrders;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacements;
import edu.rit.se.sse.rapdevx.api.dataclasses.Status;
import edu.rit.se.sse.rapdevx.api.dataclasses.Statuses;
import edu.rit.se.sse.rapdevx.api.dataclasses.Results;

/**
 * API access to the Status object on the server side
 * 
 * Approximates the ActiveRecord pattern
 * 
 * @author Ben Nicholas
 */
public class GameApi {
	public static String	SERVER_URL	= "localhost";

	public static Statuses listGames() throws Exception {
		String incomingJson = "";

		try {
			URL url = new URL("http", SERVER_URL, 8080, "/games");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data recieved");
		} else {
			return Statuses.fromJSON(incomingJson);
		}
	}

	public static Assets getAssets(Session userSession) throws Exception {
		String incomingJson = "";

		try {
			String game_id = userSession.getgame_id();
			String session_id = userSession.getsession_id();

			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ game_id
							+ "/assets?session_id="
							+ session_id);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data received");
		} else {
			return Assets.fromJSON(incomingJson);
		}
	}

	public static Status getStatus(Session userSession) throws Exception {
		String incomingJson = "";

		try {
			String game_id = userSession.getgame_id();
			String session_id = userSession.getsession_id();

			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ game_id
							+ "?session_id="
							+ session_id);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data received");
		} else {
			return Status.fromJSON(incomingJson);
		}
	}

	public static boolean setShipPlacement(Session userSession,
			List<ShipPlacement> ships) {
		ShipPlacements ships_obj = new ShipPlacements(ships);
		ships_obj.toJSON(ships_obj);
		int respcode = 0;

		try {
			String shipsJsonString = readFileAsString("ShipPlacementFromJava.json");

			String data = "session_id"
					+ "="
					+ userSession.getsession_id()
					+ "&"
					+ "unit_layout"
					+ "="
					+ shipsJsonString;

			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ userSession.getgame_id()
							+ "/ships");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			conn.connect();

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (respcode == 200) {
			return true;
		} else {
			return false;
		}
	}

	// Unit Move contents

	public static boolean submitMovementOrder(Session userSession,
			int currentTurn, MovementOrder move) {
		move.toJSON(move);
		int respcode = 0;

		try {
			String movejsonstring = readFileAsString("MovementOrderFromJava.json");

			String data = "session_id"
					+ "="
					+ userSession.getsession_id()
					+ "&"
					+ "movement_order"
					+ "="
					+ movejsonstring;

			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ userSession.getgame_id()
							+ "/turns/"
							+ currentTurn
							+ "/moves");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			conn.connect();

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (respcode == 200) {
			return true;
		} else {
			return false;
		}
	}

	public static MovementOrders getCurrentMoves(Session userSession,
			int currentTurn) throws Exception {
		String incomingJson = "";

		try {
			URL url = new URL("http", SERVER_URL, 8080,
					"/game/" + userSession.getgame_id() + "/turns/" + 
                    currentTurn + "/moves");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data recieved");
		} else {
			return MovementOrders.fromJSON(incomingJson);
		}
	}

	public static boolean removeMovementOrder(Session userSession,
			int currentTurn, MovementOrder move) {
		int respcode = 0;

		try {
			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ userSession.getgame_id()
							+ "/turns/"
							+ currentTurn
							+ "/moves/"
							+ move.getGid());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("DELETE");

			conn.connect();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (respcode == 200) {
			return true;
		} else {
			return false;
		}
	}

	// Unit Attack contents

	public static boolean submitAbilityUseOrder(Session userSession,
			int currentTurn, AbilityUseOrder attack) {
		attack.toJSON(attack);
		int respcode = 0;

		try {
			String movejsonstring = readFileAsString("AbilityUseOrderFromJava.json");

			String data = "session_id"
					+ "="
					+ userSession.getsession_id()
					+ "&"
					+ "attack_order"
					+ "="
					+ movejsonstring;

			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ userSession.getgame_id()
							+ "/turns/"
							+ currentTurn
							+ "/attacks");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			conn.connect();

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (respcode == 200) {
			return true;
		} else {
			return false;
		}
	}

	public static AbilityUseOrders getCurrentAttacks(Session userSession,
			int currentTurn) throws Exception {
		String incomingJson = "";

		try {
			URL url = new URL("http", SERVER_URL, 8080,
					"/game/" + userSession.getgame_id() + "/turns/" + 
                    currentTurn + "/attacks");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data recieved");
		} else {
			return AbilityUseOrders.fromJSON(incomingJson);
		}
	}

	public static boolean removeAbilityUseOrder(Session userSession,
			int currentTurn, MovementOrder move) {
		int respcode = 0;

		try {
			URL url = new URL("http", SERVER_URL, 8080,
					"/game/"
							+ userSession.getgame_id()
							+ "/turns/"
							+ currentTurn
							+ "/moves/"
							+ move.getGid());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("DELETE");

			conn.connect();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (respcode == 200) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean finishedWithTurn(Session userSession, int currentTurn) {
		int respcode = 0;

		try {
			String data = "session_id"
					+ "="
					+ userSession.getsession_id();

			URL url = new URL("http", SERVER_URL, 8080,
                            "/game/"
							+ userSession.getgame_id()
							+ "/turns/"
							+ currentTurn
							+ "/ready");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			conn.connect();

			OutputStreamWriter wr = new OutputStreamWriter(
            conn.getOutputStream());
			wr.write(data);
			wr.flush();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (respcode == 200) {
			return true;
		} else {
			return false;
		}
	}

	public static Results getResultMoves(Session userSession,
			int currentTurn) throws Exception {
		String incomingJson = "";

		try {
			URL url = new URL("http", SERVER_URL, 8080,
					"/game/" + 
                    userSession.getgame_id() + 
                    "/turns/" + 
                    currentTurn + 
                    "/moves/results");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data recieved");
		} else {
			return Results.fromJSON(incomingJson);
		}
	}

	public static AttackResults getResultAttacks(Session userSession,
			int currentTurn) throws Exception {
		String incomingJson = "";

		try {
			URL url = new URL("http", SERVER_URL, 8080,
					"/game/" + 
                    userSession.getgame_id() + 
                    "/turns/" + 
                    currentTurn + 
                    "/attacks/results");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			conn.connect();

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				incomingJson += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (incomingJson.equals("")) {
			throw new Exception("No data recieved");
		} else {
			return AttackResults.fromJSON(incomingJson);
		}
	}

	// General calls

	public static void setReady(Session userSession) {
		int respcode = 0;

		try {
			String data = "session_id"
					+ "="
					+ userSession.getsession_id()
                    + "&"
                    + "ready=true";

			URL url = new URL("http", SERVER_URL, 8080,
                            "/game/"
							+ userSession.getgame_id());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			conn.connect();

			OutputStreamWriter wr = new OutputStreamWriter(
            conn.getOutputStream());
			wr.write(data);
			wr.flush();

			respcode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		if (respcode == 200) {
			return;
		} else {
			return;
		}
	}

	private static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}
}
