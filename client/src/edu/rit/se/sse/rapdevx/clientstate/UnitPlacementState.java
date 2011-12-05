/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.List;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;

/**
 * @author Cody Krieger
 * 
 */
public class UnitPlacementState extends StateBase {
	public UnitPlacementState() {
		this.nextState = MoveState.class;
	}

	/**
	 * Call this when the player is done making up their initial ship layout.
	 */
	public void ready(List<ShipPlacement> shipPlacements) {
		poll();
		GameApi.setShipPlacement(GameSession.get().getSession(), shipPlacements);
	}

	/*
	 * (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.clientstate.StateBase#finishedPolling()
	 */
	@Override
	protected void finishedPolling() {
		GameSession.get().advanceState();
	}
}
