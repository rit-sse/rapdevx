/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class DoneState extends StateBase {
	public DoneState() {
		// intentionally don't set nextState - we want it to be null
	}

	/*
	 * (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.clientstate.StateBase#finishedPolling()
	 */
	@Override
	protected void finishedPolling() {
	}
}
