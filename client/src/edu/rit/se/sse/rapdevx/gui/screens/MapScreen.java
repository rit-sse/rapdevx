package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import edu.rit.se.sse.rapdevx.gui.Background;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Viewport;

public class MapScreen extends Screen {
	
	public static final int VIEWPORT_SPEED = 25;
	
	private Viewport viewport;
	private Background background;
	
	public MapScreen(Viewport viewport, int width, int height) {
		super(width, height);
		
		background = new Background();
		this.viewport = viewport;
	}

	public void update(boolean hasFocus, boolean isVisible) {
		viewport.update();
	}
	
	public void draw(Graphics2D gPen) {
		// Draw a black background
		gPen.setColor(Color.BLACK);
		gPen.fillRect(0, 0, screenWidth, screenHeight);
		
		viewport.translateToWorldSpace(gPen);
		
		// Draw the real background
		background.draw(gPen, viewport.getBounds());
		
		viewport.translateToScreenSpace(gPen);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			viewport.setyVel(-VIEWPORT_SPEED);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			viewport.setyVel(VIEWPORT_SPEED);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			viewport.setxVel(-VIEWPORT_SPEED);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			viewport.setxVel(VIEWPORT_SPEED);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			OptionsScreen options = new OptionsScreen(screenWidth, screenHeight);
			ScreenStack.get().addScreen(options);
			options.init();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {
			viewport.setyVel(0);
		} else if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			viewport.setxVel(0);
		}
	}
	
}
