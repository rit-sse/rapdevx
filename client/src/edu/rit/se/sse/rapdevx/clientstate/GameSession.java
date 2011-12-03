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

	private static StateBase	currentState;

	private static Session		session;

	public static void init() {
		currentState = new StartingState();
		// TODO notify observers of state change (from null to starting state)
	}

	/**
	 * Advance the state!
	 */
	public static void advanceState() {
		// TODO implement our own version of observable and notify observers
		// here on state change
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
