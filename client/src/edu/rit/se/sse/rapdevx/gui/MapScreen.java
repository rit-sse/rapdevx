package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MapScreen extends Screen {
	
	public static final int CAMERA_SPEED = 10;
	
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
		// TODO Auto-generated method stub
		for (DrawableShip ship : shipList) {
			ship.update();
		}
		
		camera.update();
	}

	public void updateTransition(double position, int direction) {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics2D gPen) {
		// Draw a black background
		background.draw(gPen);
		
		// Draw all the ships on the map
		Rectangle2D cameraBounds = camera.getBounds();
		for (DrawableShip ship : shipList) {
			ship.draw(gPen, cameraBounds);
		}
	}
	
	public boolean keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			camera.yVel = -CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			camera.yVel = CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			camera.xVel = -CAMERA_SPEED;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			camera.xVel = CAMERA_SPEED;
		}
		
		return true;
	}
	
	public boolean keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {
			camera.yVel = 0;
		} else if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			camera.xVel = 0;
		}
		
		return true;
	}

}
