package edu.rit.se.sse.rapdevx.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;

public class DrawableShip extends DrawableObject {
	
	public static final Color SELECT_COLOR = new Color(48, 129, 233);
	
	private Ship ship;
	private boolean isSelected;
	
	private Sprite shipImage;
	
	public DrawableShip(Ship ship) {
		super(ship.getX() * 2, ship.getY() * 2, 64, 64);
		this.isSelected = false;
		
		shipImage = new Sprite("assets/ship.png");
	}

	public void update() {
		this.x += xVel;
		this.y += yVel;
	}
	
	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		if (isSelected) {
			gPen.setColor(SELECT_COLOR);
			gPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			gPen.setStroke(new BasicStroke(2.0f));
			gPen.draw(getBounds());
			gPen.setStroke(new BasicStroke());
		}
		shipImage.draw(gPen, x, y, 4);
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public Ellipse2D getBounds() {
		// TODO get ship radius (2 * scale)
		return new Ellipse2D.Double(x - 6, y + 2, 68, 68);
	}

}
