/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class GameSession {

	private StateBase	currentState	= new StartingState();

	/**
	 * Advance the state!
	 */
	public void advanceState() {
		currentState = currentState.advance();
	}
}
