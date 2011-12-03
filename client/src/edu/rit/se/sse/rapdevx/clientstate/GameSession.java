/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;

/**
 * @author Cody Krieger
 * 
 */
public class GameSession {
	
	private static GameSession instance = new GameSession();

	private StateBase	currentState	= new StartingState();
	private Session		session;
	
	private ArrayList<StateListener> listeners = new ArrayList<StateListener>();
	
	private GameSession() {
		
	}
	
	public static GameSession get() {
		return instance;
	}

	/**
	 * Advance the state!
	 */
	public void advanceState() {
		currentState = currentState.advance();
		notifyStateListeners();
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session newSession) {
		session = newSession;
	}

	/**
	 * @param observer
	 *            the observer to add
	 */
	public void addStateListener(StateListener listener) {
		listeners.add(listener);
	}

	/**
	 * @param observer
	 *            the observer to remove
	 */
	public void removeStateListener(StateListener listener) {
		listeners.add(listener);
	}
	
	private void notifyStateListeners() {
		for (StateListener listener : listeners)
			listener.stateChanged(new StateEvent(this));
	}
}
