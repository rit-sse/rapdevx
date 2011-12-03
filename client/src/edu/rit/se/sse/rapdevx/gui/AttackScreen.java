package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;

public class AttackScreen extends Screen implements StateListener {
	
	public static final int CAMERA_SPEED = 25;
	
	/** A reference to the base map containing the grid and camera **/
	private MapScreen mapScreen;
	
	/** A list of ships currently on the field **/
	private ArrayList<DrawableShip> shipList;
	
	
	public AttackScreen(MapScreen mapScreen, int width, int height) {
		super(width, height);
		this.mapScreen = mapScreen;
		
		shipList = new ArrayList<DrawableShip>();
		shipList.add(new DrawableShip(300, 300));
	}

	public void update(boolean hasFocus, boolean isVisible) {
		for (DrawableShip ship : shipList) {
			ship.update();
		}
	}
	
	public void draw(Graphics2D gPen) {
		// Translate the coordinates based on the camera
		Rectangle2D cameraBounds = mapScreen.getCamera().getBounds();
		gPen.translate(cameraBounds.getX(), cameraBounds.getY());
		
		/** Draw things based on the camera here **/
		
		// Draw all the ships on the map
		for (DrawableShip ship : shipList) {
			ship.draw(gPen, cameraBounds);
		}
		
		// Change the drawing back to screen based coordinates
		gPen.translate(-cameraBounds.getX(), -cameraBounds.getY());
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		shipList.get(0).setCenter(e.getX(), e.getY());
		e.consume();
	}
	
	public void stateChanged(StateEvent e) {
		//TODO Switch to move phase, etc
	}

}
