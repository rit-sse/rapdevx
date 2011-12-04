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
public abstract class StateBase {

	protected Class<?>	nextState;

	private Timer		timer	= new Timer();
	private int			phaseNum;

	/**
	 * @return the nextState
	 */
	public Class<?> getNextState() {
		return nextState;
	}

	public StateBase advance() {
		try {
			return (StateBase) nextState.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void poll() {
		phaseNum = Integer.parseInt(GameApi.getStatus(
				GameSession.get().getSession()).getPhase());

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (Integer.parseInt(GameApi.getStatus(
						GameSession.get().getSession()).getPhase()) != phaseNum) {
					this.cancel();
					finishedPolling();
				}
			}

		}, 0, 1000);
	}

	protected abstract void finishedPolling();
}
