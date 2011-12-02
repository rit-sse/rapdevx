package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Graphics2D;

import edu.rit.se.sse.rapdevx.gui.Screen;

public class ExampleScreen extends Screen {

	@Override
	public void draw(Graphics2D gPen) {
		// Draw things here
	}

	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		// Move things here
			// hasFocus tells you if the screen accepts input currently (paused / unpaused)
			// isVisible tells you if any thing can be seen on screen
	}

	@Override
	public void updateTransition(double position, int direction) {
		// Called when we are transitioning from one screen to another
		// You can set up cool effects here.
	}
	
	

}
