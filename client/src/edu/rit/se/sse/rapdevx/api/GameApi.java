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

	public static Assets getAssets(Session userSession) {
		return null;
	}

	public static Status getStatus(Session userSession) {
		return null;
	}

	public static boolean setShipPlacement(Session userSession,
			List<ShipPlacement> ships) {
		return false;
	}

	// Unit Move contents

	public static boolean submitMovementOrder(Session userSession, 
            int currentTurn, MovementOrder move) {
		return false;
	}

	public static List<MovementOrder> getCurrentMoves(Session userSession, 
            int currentTurn) {
		return null;
	}

	public static boolean removeMovementOrder(Session userSession, 
            int currentTurn, MovementOrder move) {
		return false;
	}

	// Unit Attack contents

	public static boolean submitAbilityUseOrder(Session userSession, 
            AbilityUseOrder attack) {
		return false;
	}

	public static List<AbilityUseOrder> getCurrentAttacks(Session userSession, 
            int currentTurn) {
		return null;
	}

	public static boolean removeAbilityUseOrder(Session userSession, 
            int currentTurn, MovementOrder move) {
		return false;
	}

	public static boolean finishedWithTurn(Session userSession, int currentTurn) {
		return false;
	}

	public static List<MovementOrder> getResultMoves(Session userSession, 
            int currentTurn) {
		return null;
	}

	public static List<AbilityUseOrder> getResultAttacks(Session userSession, 
            int currentTurn) {
		return null;
	}
}
