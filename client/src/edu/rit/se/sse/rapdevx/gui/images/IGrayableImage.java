package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Graphics2D;
import java.awt.Point;

public interface IGrayableImage {

	public void draw(Graphics2D gPen);
		
	public void setHovering(boolean hovering);
	
	public boolean containsPoint(Point point);
	
}
