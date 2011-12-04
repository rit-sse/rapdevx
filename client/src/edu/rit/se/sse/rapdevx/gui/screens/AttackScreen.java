package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.clientstate.GameSession;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Camera;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableAttack;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;

public class AttackScreen extends Screen implements StateListener {
	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;

	/** A list of ships currently on the field */
	private ArrayList<DrawableShip> shipList = new ArrayList<DrawableShip>();
	private DrawableShip selectedShip;

	/** A list of all the attack paths on the field */
	private ArrayList<DrawableAttack> attackList = new ArrayList<DrawableAttack>();
	private DrawableAttack curAttack = null;

	public AttackScreen(Camera camera, int width, int height) {
		super(width, height);
		this.camera = camera;

		GameSession.get().addStateListener(this);
		
		Ship ship = new Ship();
		ship.setX(150);
		ship.setY(150);

		shipList.add(new DrawableShip(ship, new Color(48, 129, 233)));
		
		ship = new Ship();
		ship.setX(20);
		ship.setY(50);

		shipList.add(new DrawableShip(ship, new Color(48, 129, 233)));

		Ship ship2 = new Ship();
		ship2.setX(300);
		ship2.setY(300);
		shipList.add(new DrawableShip(ship2, new Color(100, 255, 130)));
		
		ship2 = new Ship();
		ship2.setX(10);
		ship2.setY(200);
		shipList.add(new DrawableShip(ship2, new Color(100, 255, 130)));
	}

	public void update(boolean hasFocus, boolean isVisible) {
		// Make sure the selected ship shows a selected indicator
		if (selectedShip != null)
			selectedShip.setSelected(true);

		// Update all the ships on screen
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
		
		// Draw all the attack targets
		if (curAttack != null) {
			curAttack.draw(gPen, cameraBounds);
		}
		
		for (DrawableAttack attack : attackList)
			attack.draw(gPen, cameraBounds);

		// Change the drawing back to screen based coordinates
		gPen.translate(cameraBounds.getX(), cameraBounds.getY());
	}

	public void mouseReleased(MouseEvent e) {
		// Check to see if one of the ships was clicked
		for (DrawableShip ship : shipList) {
			if (new Area(ship.getBounds()).contains(e.getX() + camera.getX(),
					e.getY() + camera.getY()))
			{
				if (ship == selectedShip) {
					// This ship was previously selected.  Deselect it and stop the attack.
					selectedShip.setSelected(false);
					selectedShip = null;
					curAttack = null;
				} else if (selectedShip != null) {
					// Another ship was selected, complete the attack and deselect the ship
					curAttack.setTarget(ship, camera.getBounds());
					selectedShip.setSelected(false);
					selectedShip = null;
					
					// Save the completed attack
					attackList.add(curAttack);
					curAttack = null;
				} else {
					// No other ship was selected, select this one and start an attack
					selectedShip = ship;
					selectedShip.setSelected(true);
					curAttack = new DrawableAttack(ship);
				}
			}
		}

		
		if (selectedShip == null)
			curAttack = null;
		else if (curAttack == null)
			curAttack = new DrawableAttack(selectedShip);

		e.consume();
	}

	public void mouseMoved(MouseEvent e) {
		if (curAttack != null) {
			// Snap the reticle to a ship if we are hovering over one
			for (DrawableShip ship : shipList) {
				if (ship == selectedShip)
					continue;
				
				if (new Area(ship.getBounds()).contains(e.getX() + camera.getX(),
						e.getY() + camera.getY()))
				{
					curAttack.setMouseLocation(ship.getCenter());
					curAttack.setSnapped(true);
					
					e.consume();
					return;
				}
			}
			
			// No ship under the mouse, clear snapping
			curAttack.setSnapped(false);
			
			// If there is an attack selection in progress, update the reticle
			curAttack.setMouseLocation(new Point(e.getX() + camera.getX(),
					e.getY() + camera.getY()));
		}

		e.consume();
	}

	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof MoveState) {
			ScreenStack.get().addScreenAfter(this, new MoveScreen(camera, screenWidth, screenHeight));
			ScreenStack.get().removeScreen(this);
			GameSession.get().removeStateListener(this);
		}
	}
	
}
