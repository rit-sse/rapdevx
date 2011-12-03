package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawableShip extends DrawableObject {
	
	Sprite shipImage;
	
	public DrawableShip(int x, int y) {
		super(x, y, 64, 64);
		shipImage = new Sprite("assets/ship.png");
	}

	public void update() {
		this.x += xVel;
		this.y += yVel;
	}
	
	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		if (bounds.intersects(x + bounds.getX(), y + bounds.getY(), 64, 64)) {
			shipImage.draw(gPen, x + (int)bounds.getX(), y + (int)bounds.getY(), 4);
		}
		
	}	

}
