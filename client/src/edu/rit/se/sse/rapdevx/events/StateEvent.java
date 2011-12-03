package edu.rit.se.sse.rapdevx.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public class StateEvent extends EventObject {

	public StateEvent(Object source) {
		super(source);
		// TODO hold new state and old state references / types?
	}

}
