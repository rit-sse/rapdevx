package edu.rit.se.sse.rapdevx.gui;

import java.awt.Rectangle;

public class Camera extends DrawableObject {
	
	public Camera(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}