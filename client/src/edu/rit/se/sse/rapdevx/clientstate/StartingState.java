/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.SessionApi;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	public StartingState() {
		this.nextState = UnitPlacementState.class;

		// TODO here we'll need to include some "game picking" logic -- passing
		// in null will, in effect, request matchmaking
		try {
			GameSession.get().setSession(
					SessionApi.createSession("nickname", null));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to create session");
		}
		try {
			AssetLibrary.setAssets(GameApi.getAssets(GameSession.get()
					.getSession()));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to pull down assets");
		}

		poll();

		// yay, we're ready!
		GameApi.setReady(GameSession.get().getSession());
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
