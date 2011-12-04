/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;

/**
 * @author Cody Krieger
 * 
 */
public class GameSession {

	private static GameSession						instance		= new GameSession();
	private ConcurrentLinkedQueue<StateListener>	listeners		= new ConcurrentLinkedQueue<StateListener>();

	private StateBase								currentState	= new MoveState();
	private Session									session;

	private List<ShipClass>							shipClasses		= new Vector<ShipClass>();

	private GameSession() {

	}

	public static GameSession get() {
		return instance;
	}

	/**
	 * Advance the state!
	 */
	public void advanceState() {
		StateBase oldState = currentState;
		currentState = currentState.advance();
		notifyStateListeners(oldState, currentState);
	}

	/**
	 * Get the current state of the state machine.
	 * 
	 * @return The current state.
	 */
	public StateBase getCurrentState() {
		return currentState;
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
	public synchronized void addStateListener(StateListener listener) {
		listeners.add(listener);
	}

	/**
	 * @param observer
	 *            the observer to remove
	 */
	public synchronized void removeStateListener(StateListener listener) {
		listeners.remove(listener);
	}

	private synchronized void notifyStateListeners(StateBase oldState,
			StateBase newState) {
		for (StateListener listener : listeners) {
			StateEvent event = new StateEvent(this, oldState, newState);
			listener.stateChanged(event);
		}
	}

	/**
	 * @return the shipClasses
	 */
	public List<ShipClass> getShipClasses() {
		return shipClasses;
	}

	/**
	 * Add a ShipClass to the game session.
	 * 
	 * @param s
	 *            The ShipClass to add.
	 */
	public void addShipClass(ShipClass s) {
		shipClasses.add(s);
	}

	/**
	 * Remove a ShipClass from the game session.
	 * 
	 * @param s
	 *            The ShipClass to remove.
	 */
	public void removeShipClass(ShipClass s) {
		shipClasses.remove(s);
	}

	/**
	 * Find a Ship class given a class id
	 * 
	 * @param id
	 *            the ship class id
	 * @return the ship class associated with this id
	 */
	public ShipClass findByClassId(String id) {
		for (ShipClass shipClass : shipClasses) {
			if (shipClass.getGid().equals(id)) {
				return shipClass;
			}
		}
		return null;
	}
}
