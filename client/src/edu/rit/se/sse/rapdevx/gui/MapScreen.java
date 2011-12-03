package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MapScreen extends Screen {
	
	public static final int CAMERA_SPEED = 25;
	
	private Camera camera;
	private Background background;
	private ArrayList<DrawableShip> shipList;

	public MapScreen(int width, int height) {
		super(width, height);
		
		background = new Background(0, 0, screenWidth, screenHeight);
		camera = new Camera(0, 0, screenWidth, screenHeight);
		
		shipList = new ArrayList<DrawableShip>();
		shipList.add(new DrawableShip(300, 300));
	}

	public void update(boolean hasFocus, boolean isVisible) {
		for (DrawableShip ship : shipList) {
			ship.update();
		}
		
		camera.update();
	}
	
	public void draw(Graphics2D gPen) {
		Rectangle2D cameraBounds = camera.getBounds();
		
		// Draw a black background
		gPen.setColor(Color.BLACK);
		gPen.fillRect(0, 0, screenWidth, screenHeight);
		
		// Draw the real background
		background.draw(gPen, cameraBounds);
		
		// Draw all the ships on the map
		for (DrawableShip ship : shipList) {
			ship.draw(gPen, cameraBounds);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			camera.yVel = CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			camera.yVel = -CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			camera.xVel = CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			camera.xVel = -CAMERA_SPEED;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {
			camera.yVel = 0;
		} else if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			camera.xVel = 0;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		shipList.get(0).setCenter(e.getX(), e.getY());
		e.consume();
	}

}
