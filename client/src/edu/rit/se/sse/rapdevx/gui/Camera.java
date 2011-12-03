package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Camera extends DrawableObject {
	
	public Camera(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}
	
	public void draw(Graphics2D gPen) {
		// TODO Auto-generated method stub
	}
	
}