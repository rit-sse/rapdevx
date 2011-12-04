/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.Timer;
import java.util.TimerTask;

import edu.rit.se.sse.rapdevx.api.GameApi;

/**
 * @author Cody Krieger
 * 
 */
public class MoveState extends StateBase {
	private Timer	timer	= new Timer();
	private int		phaseNum;

	public MoveState() {
		this.nextState = AttackState.class;
	}

	/**
	 * Make a move and send it to the server.
	 */
	public void makeMove(/* TODO take in a move from the GUI */) {
		// TODO : GameApi.submitMovementOrder(userSession, currentTurn, move)
	}

	/**
	 * Call this when the player is done with their move stack.
	 */
	public void ready() {
		phaseNum = Integer.parseInt(GameApi.getStatus(GameSession.get().getSession()).getPhase());

		GameApi.setReady(GameSession.get().getSession());

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (Integer.parseInt(GameApi.getStatus(GameSession.get().getSession())
						.getPhase())!= phaseNum) {
					this.cancel();
					readyReady();
				}
			}

		}, 0, 1000);
	}

	private void readyReady() {
		GameSession.get().advanceState();
	}
}
