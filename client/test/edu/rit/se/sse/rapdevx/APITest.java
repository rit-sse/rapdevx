package edu.rit.se.sse.rapdevx;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.SessionApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;
import edu.rit.se.sse.rapdevx.clientstate.GameSession;

public class APITest {
	@Test
	public void testAPI() {
		Session p1 = null;
		Session p2 = null;
		try {
			p1 = SessionApi.createSession("Player1", null);
			p2 = SessionApi.createSession("Player2", null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//assertNotNull(p1);
		//assertNotNull(p2);
		
		p1 = SessionApi.updateSession(p1);
		
		GameApi.setReady(p1);
		GameApi.setReady(p2);
		
		Assets al = AssetLibrary.getAssets();
		
		
		
	}
}
