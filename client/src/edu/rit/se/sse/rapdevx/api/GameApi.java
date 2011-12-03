package edu.rit.se.sse.rapdevx.api;

import java.util.List;

import edu.rit.se.sse.rapdevx.api.dataclasses.AbilityUseOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.Game;
import edu.rit.se.sse.rapdevx.api.dataclasses.MovementOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;
import edu.rit.se.sse.rapdevx.api.dataclasses.Status;

/**
 * API access to the Game object on the server side
 * 
 * Approximates the ActiveRecord pattern
 * 
 * @author Ben Nicholas
 */
public class GameApi {
	public static List<Game> listGames() {
		return null;
	}

	public static Assets getAssets(Game inputGame, Session userSession) {
		return null;
	}

	public static Status getStatus(Game inputGame) {
		return null;
	}

	public static boolean setShipPlacement(Game inputGame, Session userSession,
			List<ShipPlacement> ships) {
		return false;
	}

	// Unit Move contents

	public static boolean submitMovementOrder(Game inputGame,
			Session userSession, int currentTurn, MovementOrder move) {
		return false;
	}

	public static List<MovementOrder> getCurrentMoves(Game inputGame,
			Session userSession, int currentTurn) {
		return null;
	}

	public static boolean removeMovementOrder(Game inputGame,
			Session userSession, int currentTurn, MovementOrder move) {
		return false;
	}

	// Unit Attack contents

	public static boolean submitAbilityUseOrder(Game inputGame,
			Session userSession, AbilityUseOrder attack) {
		return false;
	}

	public static List<AbilityUseOrder> getCurrentAttacks(Game inputGame,
			Session userSession, int currentTurn) {
		return null;
	}

	public static boolean removeAbilityUseOrder(Game inputGame,
			Session userSession, int currentTurn, MovementOrder move) {
		return false;
	}

	public static boolean finishedWithTurn(Game inputGame, Session userSession,
			int currentTurn) {
		return false;
	}

	public static List<MovementOrder> getResultMoves(Game inputGame,
			Session userSession, int currentTurn) {
		return null;
	}

	public static List<AbilityUseOrder> getResultAttacks(Game inputGame,
			Session userSession, int currentTurn) {
		return null;
	}
}
