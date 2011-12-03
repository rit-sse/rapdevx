package edu.rit.se.sse.rapdevx.gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import edu.rit.se.sse.rapdevx.clientmodels.Path;

public class DrawablePath extends DrawableObject {
	
	Path path;
	
	public DrawablePath(Path path) {
		
		this.path = path;
		
	}
	
	public void draw(Graphics2D gPen, int x, int y) {
		
		if (path.getPath().isEmpty()) {
			
			path.addPoint(new Point(x,y));
			
		} else {
			
			path.addPoint(new Point(x,y));
			gPen.drawLine(path.getLastPoint().x, path.getLastPoint().y, x, y);
			
		}
		
	}
	
	private void tempPath(Graphics2D gPen, int x, int y) {
		
		gPen.setStroke(new BasicStroke((float) 1.0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		gPen.drawLine(path.getLastPoint().x, path.getLastPoint().y, x, y);
		gPen.setStroke(new BasicStroke());
		
	}

}
