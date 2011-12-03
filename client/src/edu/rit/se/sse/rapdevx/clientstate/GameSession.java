/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.dataclasses.Session;

/**
 * @author Cody Krieger
 * 
 */
public class GameSession {

	private static StateBase	currentState	= new StartingState();

	private static Session		session;

	/**
	 * Advance the state!
	 */
	public static void advanceState() {
		currentState = currentState.advance();
	}

	/**
	 * @return the session
	 */
	public static Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public static void setSession(Session newSession) {
		session = newSession;
	}
}
