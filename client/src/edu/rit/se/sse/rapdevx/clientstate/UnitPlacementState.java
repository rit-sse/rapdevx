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
public class UnitPlacementState extends StateBase {
	private Timer	timer	= new Timer();
	private int		phaseNum;

	public UnitPlacementState() {
		this.nextState = MoveState.class;
	}

	/**
	 * Call this when the player is done making up their initial ship layout.
	 */
	public void ready(/*
					 * TODO this should take in some kind of layout we can pass
					 * to the server -- will need to get this called as a result
					 * of hitting "submit" in the GUI
					 */) {
		phaseNum = GameApi.getStatus(GameSession.get().getSession()).getPhase();

		// TODO tell the server our layout
		// GameApi.setShipPlacement(GameSession.get().getSession(), ships)

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (GameApi.getStatus(GameSession.get().getSession())
						.getPhase() != phaseNum) {
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
