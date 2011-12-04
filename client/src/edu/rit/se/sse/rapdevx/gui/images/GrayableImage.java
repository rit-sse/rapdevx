package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Graphics2D;

import edu.rit.se.sse.rapdevx.gui.Sprite;

/**
 * Represents an image on the screen that can be grayed out.
 *   Paint according to location given at construction time.
 *   
 * @author Brian
 *
 */
public class GrayableImage implements IGrayableImage {

	private Sprite baseImage;
	private int x, y;
	private boolean hovering;
	
	public GrayableImage(String asset, int x, int y) {
		this.baseImage = new Sprite(asset);
		this.x = x;
		this.y = y;
		this.hovering = false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void draw(Graphics2D gPen) {
		if (this.hovering) {
			// TODO
		}
		
		this.baseImage.draw(gPen, this.x, this.y);
	}
	
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
}
