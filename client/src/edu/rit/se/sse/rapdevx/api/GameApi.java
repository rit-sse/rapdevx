package edu.rit.se.sse.rapdevx.api;

import java.util.List;

import edu.rit.se.sse.rapdevx.api.dataclasses.AbilityUseOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.MovementOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;
import edu.rit.se.sse.rapdevx.api.dataclasses.Status;

/**
 * API access to the Status object on the server side
 * 
 * Approximates the ActiveRecord pattern
 * 
 * @author Ben Nicholas
 */
public class GameApi {
	public static List<Status> listGames() {
		return null;
	}

	public static Assets getAssets(Status inputGame, Session userSession) {
		return null;
	}

	public static Status getStatus(Status inputGame) {
		return null;
	}

	public static boolean setShipPlacement(Status inputGame, Session userSession,
			List<ShipPlacement> ships) {
		return false;
	}

	// Unit Move contents

	public static boolean submitMovementOrder(Status inputGame,
			Session userSession, int currentTurn, MovementOrder move) {
		return false;
	}

	public static List<MovementOrder> getCurrentMoves(Status inputGame,
			Session userSession, int currentTurn) {
		return null;
	}

	public static boolean removeMovementOrder(Status inputGame,
			Session userSession, int currentTurn, MovementOrder move) {
		return false;
	}

	// Unit Attack contents

	public static boolean submitAbilityUseOrder(Status inputGame,
			Session userSession, AbilityUseOrder attack) {
		return false;
	}

	public static List<AbilityUseOrder> getCurrentAttacks(Status inputGame,
			Session userSession, int currentTurn) {
		return null;
	}

	public static boolean removeAbilityUseOrder(Status inputGame,
			Session userSession, int currentTurn, MovementOrder move) {
		return false;
	}

	public static boolean finishedWithTurn(Status inputGame, Session userSession,
			int currentTurn) {
		return false;
	}

	public static List<MovementOrder> getResultMoves(Status inputGame,
			Session userSession, int currentTurn) {
		return null;
	}

	public static List<AbilityUseOrder> getResultAttacks(Status inputGame,
			Session userSession, int currentTurn) {
		return null;
	}
}
