package edu.rit.se.sse.rapdevx.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.rit.se.sse.rapdevx.clientmodels.Path;

public class DrawablePath extends DrawableObject {
	
	private Path path;
	private Point mouseLocation;
	
	private Color pathColor;
	
	public DrawablePath( Path path ) {
		this.path = path;
		this.mouseLocation = null;
		
		pathColor = new Color(255, 0, 0);
	}
	
	public DrawablePath( Path path, Color color ) {
		this( path );
		
		pathColor = color;
	}
	
	public void setColor( Color newColor ) {
		pathColor = newColor;
	}
	
	public Color getColor() {
		return pathColor;
	}
	
	public void draw(Graphics2D gPen) {
		gPen.setColor( pathColor );
		
		// Draw a line between the last coordinate and the mouse cursor
		if (mouseLocation != null && path.getLastPoint() != null) {
			Point lastLocation = path.getLastPoint();
			
			// Create a dashed line
			float[] f1 = { 10.0f };
			gPen.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, f1, 0.0f));
			
			gPen.drawLine(lastLocation.x, lastLocation.y,
					mouseLocation.x, mouseLocation.y);
			
			// Reset the line style
			gPen.setStroke(new BasicStroke());
		}
		
		// Draw all of the saved path segments
		Point prevLocation = null;
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
