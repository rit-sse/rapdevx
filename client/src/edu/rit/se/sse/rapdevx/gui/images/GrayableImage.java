package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.rit.se.sse.rapdevx.gui.Sprite;

/**
 * Represents an image on the screen that can be grayed out.
 *   Paint according to location given at construction time.
 *   
 * @author Brian
 *
 */
public class GrayableImage implements IGrayableImage {

	private static BufferedImage grayOverlay;
	
	private Sprite baseImage;
	private int x, y;
	private boolean hovering;
	private Rectangle rect;
	
	public GrayableImage(String asset, int x, int y) {
		this.baseImage = new Sprite(asset);
		this.x = x;
		this.y = y;
		this.hovering = false;
		this.rect = new Rectangle(this.baseImage.getImageWidth(),
				this.baseImage.getImageHeight());
		this.rect.x = this.x;
		this.rect.y = this.y;
		
		if (grayOverlay == null) {
			grayOverlay = new BufferedImage(
					baseImage.getImageWidth(),
					baseImage.getImageHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics g = grayOverlay.getGraphics();
			g.setColor(new Color(125, 125, 125, 125));
			g.fillRect(0, 0, baseImage.getImageWidth(),
					baseImage.getImageHeight());
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void draw(Graphics2D gPen) {
		this.baseImage.draw(gPen, this.x, this.y);
		
		if (this.hovering) {
			gPen.drawImage(grayOverlay, this.x, this.y,
					baseImage.getImageWidth(), baseImage.getImageHeight(),
					null);
		}
	}
	
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	public void setPressed(boolean pressed) {
		
	}
	
	public boolean containsPoint(Point point) {
		return this.rect.contains(point);
	}
	
}
