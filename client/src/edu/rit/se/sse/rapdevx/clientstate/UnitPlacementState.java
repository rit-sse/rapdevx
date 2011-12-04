/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;

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
	public void ready(List<ShipPlacement> shipPlacements) {
		phaseNum = Integer.parseInt(GameApi.getStatus(GameSession.get().getSession()).getPhase());

		GameApi.setShipPlacement(GameSession.get().getSession(), shipPlacements);

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (Integer.parseInt(GameApi.getStatus(GameSession.get().getSession())
						.getPhase()) != phaseNum) {
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
