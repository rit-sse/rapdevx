package edu.rit.se.sse.rapdevx.events;

import java.util.EventObject;

public class StateEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

	public StateEvent(Object source) {
		super(source);
		//TODO hold new state and old state references / types?
	}

}
