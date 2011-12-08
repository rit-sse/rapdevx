/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;

/**
 * @author Cody Krieger
 * 
 */
public class LoadingState extends StateBase {
	
	public LoadingState() {
		this.nextState = UnitPlacementState.class;
		
		// Load assets from the server
		try {
			AssetLibrary.setAssets(GameApi.getAssets(GameSession.get().getSession()));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to pull down assets");
		}
		
		// yay, we're ready!
		poll();
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
