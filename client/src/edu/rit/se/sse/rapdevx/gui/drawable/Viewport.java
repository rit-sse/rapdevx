package edu.rit.se.sse.rapdevx.gui.drawable;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.rit.se.sse.rapdevx.gui.drawable.DrawableObject;

public class Viewport extends DrawableObject {

	public Viewport(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public int convertXToWorldSpace(int screenSpaceX) {
		return screenSpaceX + x;
	}
	
	public int convertXToScreenSpace(int worldSpaceX) {
		return worldSpaceX - x;
	}
	
	public int convertYToWorldSpace(int screenSpaceY) {
		return screenSpaceY + y;
	}
	
	public int convertYToScreenSpace(int worldSpaceY) {
		return worldSpaceY - y;
	}

	public Point convertToWorldSpace(Point screenSpaceLocation) {
		return new Point(screenSpaceLocation.x + x, screenSpaceLocation.y + y);
	}

	public Point convertToScreenSpace(Point worldSpaceLocation) {
		return new Point(worldSpaceLocation.x - x, worldSpaceLocation.y - y);
	}

	public void translateToWorldSpace(Graphics2D gPen) {
		gPen.translate(-x, -y);
	}

	public void translateToScreenSpace(Graphics2D gPen) {
		gPen.translate(x, y);
	}

}