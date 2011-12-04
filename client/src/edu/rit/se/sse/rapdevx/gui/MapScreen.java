package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class MapScreen extends Screen {
	
	public static final int CAMERA_SPEED = 25;
	
	private Camera camera;
	private Background background;
	
	public MapScreen(int width, int height) {
		super(width, height);
		
		background = new Background();
		camera = new Camera(0, 0, screenWidth, screenHeight);
	}
	
	public Camera getCamera() {
		return camera;
	}

	public void update(boolean hasFocus, boolean isVisible) {
		camera.update();
	}
	
	public void draw(Graphics2D gPen) {
		Rectangle2D cameraBounds = camera.getBounds();
		
		// Draw a black background
		gPen.setColor(Color.BLACK);
		gPen.fillRect(0, 0, screenWidth, screenHeight);
		
		gPen.translate(-cameraBounds.getX(), -cameraBounds.getY());
		
		// Draw the real background
		background.draw(gPen, cameraBounds);
		
		gPen.translate(cameraBounds.getX(), cameraBounds.getY());
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			camera.yVel = -CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			camera.yVel = CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			camera.xVel = -CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			camera.xVel = CAMERA_SPEED;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {
			camera.yVel = 0;
		} else if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			camera.xVel = 0;
		}
	}
	
}
