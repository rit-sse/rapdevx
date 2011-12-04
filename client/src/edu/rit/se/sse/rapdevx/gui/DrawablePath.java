package edu.rit.se.sse.rapdevx.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.rit.se.sse.rapdevx.clientmodels.Path;

public class DrawablePath extends DrawableObject {
	
	private Path path;
	private Point mouseLocation;
	
	public DrawablePath(Path path) {
		this.path = path;
		this.mouseLocation = null;
	}
	
	public void draw(Graphics2D gPen) {
		// Draw a line between the last coordinate and the mouse cursor
		if (mouseLocation != null && path.getLastPoint() != null) {
			Point lastLocation = path.getLastPoint();
			gPen.setColor(new Color(0, 255, 0));
			gPen.drawLine(lastLocation.x, lastLocation.y,
					mouseLocation.x, mouseLocation.y);
		}
		
		// Draw all of the saved path segments
		Point prevLocation = null;
		
		gPen.setColor(new Color(255, 0, 0));
		for (Point location : path.getPath()) {
			// Draw a square marking the point
			gPen.fillRect(location.x - 4, location.y - 4, 8, 8);
			
			// Draw a line between the points
			if (prevLocation != null)
				gPen.drawLine(location.x, location.y, prevLocation.x, prevLocation.y);
			
			prevLocation = location;
		}
	}
	
	public Path getPath() {
		return path;
	}
	
	public void setMouseLocation(Point location) {
		this.mouseLocation = location;
	}

}
