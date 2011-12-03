/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.Timer;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.SessionApi;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	Timer	timer	= new Timer();

	public StartingState() {
		this.nextState = UnitPlacementState.class;

		GameSession.setSession(SessionApi.createSession("nickname", null));
		Object assets = GameApi.getAssets(/* get the game from where? */null,
				GameSession.getSession());
	}
}
