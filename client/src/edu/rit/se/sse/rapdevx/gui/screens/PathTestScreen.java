package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import edu.rit.se.sse.rapdevx.clientmodels.Path;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawablePath;

public class PathTestScreen extends Screen {
	
	DrawablePath path;

	public PathTestScreen(int width, int height) {
		
		super(width, height);
		
		Path path = new Path(new Point(50, 50));
		path.addPoint(new Point(100, 50));
		path.addPoint(new Point(200, 100));
		path.addPoint(new Point(500, 30));
		
		this.path = new DrawablePath(path);
	}

	public void update(boolean hasFocus, boolean isVisible) {
		
	}

	public void draw(Graphics2D gPen) {
		gPen.fillRect(0, 0, screenWidth, screenHeight);
		path.draw(gPen);
		
		
	}
	
	public void mouseMoved(MouseEvent e) {
		path.setMouseLocation(e.getPoint());
		
		e.consume();
	}
	
	public void mouseReleased(MouseEvent e) {
		path.getPath().addPoint(e.getPoint());
	}

}
