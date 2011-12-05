/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.GameApi;

/**
 * @author Cody Krieger
 * 
 */
public class AttackState extends StateBase {
	public AttackState() {
		this.nextState = MoveState.class;

		// TODO when one team is out of ships, we actually need to set our
		// nextState to DoneState.class
	}

	/**
	 * Make an attack and send it to the server.
	 */
	public void makeAttack(/* TODO take in an attack from the GUI */) {
		// TODO GameApi.submitAbilityUseOrder(userSession, attack)
	}

	/**
	 * Call this when the player is done with their attack stack.
	 */
	public void ready() {
		poll();
		GameApi.setReady(GameSession.get().getSession());
	}

	/*
	 * (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.clientstate.StateBase#finishedPolling()
	 */
	@Override
	protected void finishedPolling() {
		GameSession.get().advanceState();
	}
}
