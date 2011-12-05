/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.SessionApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	
	private Session session;
	
	public StartingState() {
		this.nextState = UnitPlacementState.class;
		
		// TODO here we'll need to include some "game picking" logic -- passing
		// in null will, in effect, request matchmaking
		try {
			this.session = SessionApi.createSession("nickname", null);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to create session");
		}
	}
	
	public void init() {
		try {
			AssetLibrary.setAssets(GameApi.getAssets(session));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to pull down assets");
		}

		poll();

		// yay, we're ready!
		GameApi.setReady(session);
	}

	/*
	 * (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.clientstate.StateBase#finishedPolling()
	 */
	@Override
	protected void finishedPolling() {
		GameSession.get().advanceState();
	}

	public Session getSession() {
		return session;
	}
}
