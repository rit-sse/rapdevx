package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;

public class AttackScreen extends Screen implements StateListener
{
	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;

	/** A list of ships currently on the field **/
	private ArrayList<DrawableShip> shipList = new ArrayList<DrawableShip>();;
	private DrawableShip selectedShip;

	/** Current attack path */
	private DrawableAttack currAttack = null;

	public AttackScreen(Camera camera, int width, int height)
	{
		super(width, height);
		this.camera = camera;

		Ship ship = new Ship();
		ship.setX(150);
		ship.setY(150);

		shipList.add(new DrawableShip(ship, new Color(48, 129, 233)));
		
		Ship ship2 = new Ship();
		ship2.setX(300);
		ship2.setY(300);
		shipList.add(new DrawableShip(ship2, new Color(100,255,130)));


	}

	public void update(boolean hasFocus, boolean isVisible)
	{
		if (selectedShip != null)
			selectedShip.setSelected(true);

		for (DrawableShip ship : shipList)
		{
			ship.update();
		}
	}

	public void draw(Graphics2D gPen)
	{
		// Translate the coordinates based on the camera
		Rectangle cameraBounds = camera.getBounds();
		gPen.translate(-cameraBounds.getX(), -cameraBounds.getY());

		/** Draw things based on the camera here **/

		// Draw all the ships on the map
		for (DrawableShip ship : shipList)
		{
			ship.draw(gPen, cameraBounds);
		}
		if (currAttack != null)
		{
			currAttack.draw(gPen);
		}

		// Change the drawing back to screen based coordinates
		gPen.translate(cameraBounds.getX(), cameraBounds.getY());
	}

	public void mouseReleased(MouseEvent e)
	{
		// Check to see if one of the ships was clicked
		for (DrawableShip ship : shipList)
		{
			if (new Area(ship.getBounds()).contains(e.getX() + camera.getX(),
					e.getY() + camera.getY()))
			{
				// Select a non-selected ship and deselect a selected ship
				if (ship == selectedShip)
				{
					selectedShip.setSelected(false);
					selectedShip = null;
				}
				else
				{
					if (selectedShip != null)
					{
						selectedShip.setSelected(false);
					}
					ship.setSelected(true);
					selectedShip = ship;
				}
				// If no ship is clicked, move any selected ship to the mouse
				// coordinates
			}
			else if (selectedShip != null)
			{
				selectedShip.setCenter(e.getX() + camera.getX(), e.getY()
						+ camera.getY());
			}
		}

		currAttack = selectedShip != null ? new DrawableAttack(selectedShip, this) : null;
		
		e.consume();
	}

	public void mouseMoved(MouseEvent e)
	{
		if (selectedShip != null)
		{
			currAttack.setMouseLocation(e.getPoint());
		}
		
		// Check all ships to see if the mouse is hovered over it
		// TODO utilize for locking
		/*
		 * for (DrawableShip ship : shipList)
		 * {
		 * if (ship != selectedShip)
		 * {
		 * ship.setSelected(false);
		 * }
		 * 
		 * if (new Area(ship.getBounds()).contains(e.getX() + camera.getX(),
		 * e.getY() + camera.getY()))
		 * {
		 * ship.setSelected(true);
		 * }
		 * }
		 */

		e.consume();
	}

	public void stateChanged(StateEvent e)
	{
		// TODO Switch to move phase, etc
	}
	
	/**
	 * get the shipList, should only be used by DrawableAttack at this time
	 * @return
	 */
	public ArrayList<DrawableShip> getShipList()
	{
		return shipList;
	}
	
	/**
	 * get the Camera, should only be used by DrawableAttack at this time
	 * @return
	 */
	public Camera getCamera()
	{
		return camera;
	}
}