package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.rit.se.sse.rapdevx.clientstate.GameSession;
import edu.rit.se.sse.rapdevx.clientstate.UnitPlacementState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Text;
import edu.rit.se.sse.rapdevx.gui.drawable.Viewport;
import edu.rit.se.sse.rapdevx.gui.images.TextButton;
import edu.rit.se.sse.rapdevx.gui.screens.menus.Menu;

public class WaitingScreen extends Screen implements ActionListener,
		StateListener {

	private Viewport viewport;
	
	private Menu menu;
	private Text waitingText;

	public WaitingScreen(Viewport viewport, int width, int height) {
		super(width, height);

		this.viewport = viewport;
		GameSession.get().addStateListener(this);

		menu = new Menu(width / 2 - 100, height / 2 - 50);
		menu.addButton("Exit");
		for(TextButton button: menu.getButtons()){
			button.addActionListener(this);
		}

		waitingText = new Text("Waiting for another player...", 0,
				height / 2 - 200, 3.0, Color.LIGHT_GRAY);
		
		waitingText.setX((width - waitingText.getSizeOnScreen())/2);
	}

	public void init() {
		ScreenStack.get().addScreen(menu);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO fix this
		System.exit(0);
	}

	public void update(boolean hasFocus, boolean isVisible) {
		
	}

	public void draw(Graphics2D gPen) {
		gPen.setColor(Color.BLACK);
		gPen.fillRect(0, 0, screenWidth, screenHeight);

		waitingText.draw(gPen);
	}

	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof UnitPlacementState) {
			// Start with the move phase on the map
			MapScreen mapScreen = new MapScreen(viewport, screenWidth, screenHeight);
			ScreenStack.get().addScreen(mapScreen);

			OverlayScreen overlay = new OverlayScreen(screenWidth, screenHeight);

			// Start with a unit placement screen
			PlacementScreen placement = new PlacementScreen(overlay,
					viewport, screenWidth, screenHeight);
			ScreenStack.get().addScreen(placement);

			// Add the overlay last
			ScreenStack.get().addScreen(overlay);

			GameSession.get().removeStateListener(this);
			ScreenStack.get().removeScreen(menu);
			ScreenStack.get().removeScreen(this);
		}
	}
	
}
