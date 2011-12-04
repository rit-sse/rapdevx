/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.Timer;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.MovementOrder;

/**
 * @author Cody Krieger
 * 
 */
public class MoveState extends StateBase {
	private Timer	timer	= new Timer();
	private int		phaseNum;

	public MoveState() {
		this.nextState = AttackState.class;

		// TODO move/redraw enemy ships according to newly received layout
	}

	/**
	 * Make a move and send it to the server.
	 */
	public void makeMove(MovementOrder order) {
		GameApi.submitMovementOrder(GameSession.get().getSession(), 0, order);
	}

	/**
	 * Call this when the player is done with their move stack.
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
