/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.Timer;
import java.util.TimerTask;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.SessionApi;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	private Timer	timer	= new Timer();

	public StartingState() {
		this.nextState = UnitPlacementState.class;

		GameSession.setSession(SessionApi.createSession("nickname", null));
		AssetLibrary.setAssets(GameApi.getAssets(
		/* get the game from where? */null, GameSession.getSession()));

		// TODO set ready here

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO poll for server state change to 'unit placement'

				// once state has changed to unit placement on server, we're
				// ready to change states
				this.cancel();
				ready();
			}

		}, 0, 1);
	}

	private void ready() {
		GameSession.advanceState();
	}
}
