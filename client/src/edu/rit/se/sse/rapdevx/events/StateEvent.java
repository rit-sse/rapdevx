package edu.rit.se.sse.rapdevx.events;

import java.util.EventObject;

import edu.rit.se.sse.rapdevx.clientstate.StateBase;

@SuppressWarnings("serial")
public class StateEvent extends EventObject {

	private StateBase oldState;
	private StateBase newState;
	
	public StateEvent(Object source, StateBase oldState, StateBase newState) {
		super(source);
		this.newState = newState;
		this.oldState = oldState;
	}

	public StateBase getNewState() {
		return newState;
	}
	
	public StateBase getOldState() {
		return oldState;
	}

}
