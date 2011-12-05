package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.rit.se.sse.rapdevx.gui.Sprite;

public class HoverableImage implements IGrayableImage {
	
	private Sprite baseImage;
	private Sprite hoverImage;
	private Sprite pressImage;
	
	private int x, y;
	private boolean hovering;
	private boolean pressed;
	private Rectangle bounds;
	
	public HoverableImage(String assetName, int x, int y) {
		this.baseImage = new Sprite(assetName + ".png");
		this.hoverImage = new Sprite(assetName + "-hover.png");
		this.pressImage = new Sprite(assetName + "-press.png");
		
		this.x = x;
		this.y = y;
		
		this.hovering = false;
		this.pressed = false;
		this.bounds = new Rectangle(this.baseImage.getImageWidth(),
				this.baseImage.getImageHeight());
		this.bounds.x = this.x;
		this.bounds.y = this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void draw(Graphics2D gPen) {
		if (this.pressed) {
			this.pressImage.draw(gPen, this.x, this.y);
		} else if (this.hovering) {
			this.hoverImage.draw(gPen, this.x, this.y);
		} else {
			this.baseImage.draw(gPen, this.x, this.y);
		}
	}
	
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean containsPoint(Point point) {
		return this.bounds.contains(point);
	}
	
}
