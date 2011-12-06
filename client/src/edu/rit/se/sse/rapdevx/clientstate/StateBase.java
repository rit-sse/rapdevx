/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.Timer;
import java.util.TimerTask;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;

/**
 * @author Cody Krieger
 * 
 */
public abstract class StateBase {

	protected Class<?> nextState;

	protected Timer timer = new Timer();
	private String phase;

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
		try {
			Session session = GameSession.get().getSession();

			phase = GameApi.getStatus(session).getPhase();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't get session");
		}

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {
					if (GameApi.getStatus(GameSession.get().getSession())
							.getPhase() != phase) {
						this.cancel();
						finishedPolling();
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Couldn't get session");
					this.cancel();
				}
			}

		}, 0, 1000);
	}

	protected abstract void finishedPolling();
}
