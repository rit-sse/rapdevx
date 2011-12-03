/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.SessionApi;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	public StartingState() {
		this.nextState = UnitPlacementState.class;

		GameSession.setSession(SessionApi.createSession("nickname", null));
	}
}
