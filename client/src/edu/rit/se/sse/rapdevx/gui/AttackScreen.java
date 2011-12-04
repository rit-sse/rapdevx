package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;

public class AttackScreen extends Screen implements StateListener {
	
	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;
	
	/** A list of ships currently on the field **/
	private ArrayList<DrawableShip> shipList;
	private DrawableShip selectedShip;
	
	
	public AttackScreen(Camera camera, int width, int height) {
		super(width, height);
		this.camera = camera;
		
		shipList = new ArrayList<DrawableShip>();
		Ship ship = new Ship();
		ship.setX(150);
		ship.setY(150);
		shipList.add(new DrawableShip(ship, new Color(48, 129, 233)));
	}

	public void update(boolean hasFocus, boolean isVisible) {
		if (selectedShip != null)
			selectedShip.setSelected(true);
		
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
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof MoveState) {
			//TODO add a move screen, remove this one
		}
	}

}
