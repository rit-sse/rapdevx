package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Color;
import java.awt.Graphics2D;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;

public class ShipSelectSquare extends RectangleBackground {
	
	// TODO Probably don't want to use drawable ship...
	private DrawableShip ship;
	
	public ShipSelectSquare(int x, int y) {
		super(x, y, 72, 72);
		
		Ship ship = new Ship();
		ship.setX((x / 2) + 3);
		ship.setY((y / 2) + 3);
		ship.setHp(ship.getMaxHp());
		this.ship = new DrawableShip(ship, new Color(48, 129, 233));
	}
	
	public void draw(Graphics2D gPen) {
		super.draw(gPen);
		
		ship.draw(gPen);
	}
	
	public int magicMoveConstant = 80;
	
	public void moveUp() {
		y -= magicMoveConstant;
		ship.setX(x + 6);
		ship.setY(y + 6);
	}
	
	public void moveDown() {
		y += magicMoveConstant;
		ship.setX(x + 6);
		ship.setY(y + 6);
	}

	public Ship getShip() {
		return ship.getShip();
	}
	
}
