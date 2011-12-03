package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;

public class AttackScreen extends Screen implements StateListener {
	
	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;
	
	/** A list of ships currently on the field **/
	private ArrayList<DrawableShip> shipList;
	
	
	public AttackScreen(Camera camera, int width, int height) {
		super(width, height);
		this.camera = camera;
		
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
		Rectangle cameraBounds = camera.getBounds();
		gPen.translate(-cameraBounds.getX(), -cameraBounds.getY());
		
		/** Draw things based on the camera here **/
		
		// Draw all the ships on the map
		for (DrawableShip ship : shipList) {
			ship.draw(gPen, cameraBounds);
		}
		
		// Change the drawing back to screen based coordinates
		gPen.translate(cameraBounds.getX(), cameraBounds.getY());
	}
	
	public void mouseClicked(MouseEvent e) {
		shipList.get(0).setCenter(e.getX() + camera.getX(), e.getY() + camera.getY());
		e.consume();
	}
	
	public void stateChanged(StateEvent e) {
		//TODO Switch to move phase, etc
	}

}
