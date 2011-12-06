/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

import java.util.TimerTask;

import edu.rit.se.sse.rapdevx.api.SessionApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	
	private Session session;
	
	public StartingState() {
		this.nextState = LoadingState.class;
		
		// Get an initial session.  We can poll later to get
		// a game match to play with someone.
		
		try {
			// TODO here we'll need to include some "game picking" logic -- passing
			// in null will, in effect, request matchmaking
			this.session = SessionApi.createSession("nickname", null);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to create session");
		}
	}
	
	public void init() {
		// Poll for a game to join if we don't have one yet.
		// Otherwise, move to the asset loading state
		if (session.getgame_id() == null)
			poll();
		else
			finishedPolling();
	}
	
	protected void poll() {
		// If we don't have a game yet, poll the server
		try {
			session = SessionApi.updateSession(session);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to pull down assets");
		}

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				try {
					session = SessionApi.updateSession(session);
					if (session.getgame_id() != null)
						this.cancel();
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Failed to pull down assets");
				}
			}

		}, 0, 1000);
	}

	/*
	 * (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.clientstate.StateBase#finishedPolling()
	 */
	@Override
	protected void finishedPolling() {
		GameSession.get().advanceState();
	}

	public Session getSession() {
		return session;
	}
	
}
