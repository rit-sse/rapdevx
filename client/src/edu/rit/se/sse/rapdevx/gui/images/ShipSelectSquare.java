package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Color;
import java.awt.Graphics2D;

import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;

public class ShipSelectSquare extends RectangleBackground {
	
	// TODO Probably don't want to use drawable ship...
	private DrawableShip ship;
	private ShipClass shipClass;
	
	public ShipSelectSquare(ShipClass shipClass, int x, int y) {
		super(x, y, 72, 72);
		this.shipClass = shipClass;
		this.ship = new DrawableShip(x + 5, y + 5, 64, 64, new Color(48, 129, 233));
	}
	
	public void draw(Graphics2D gPen) {
		super.draw(gPen);
		
		ship.draw(gPen);
	}

	public ShipClass getShipClass() {
		return shipClass;
	}
	
}
