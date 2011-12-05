/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.AbilityUseOrder;

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
	public void makeAttack(AbilityUseOrder attack) {
		GameApi.submitAbilityUseOrder(GameSession.get().getSession(),0, attack);
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
