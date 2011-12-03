package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Graphics2D;
import java.awt.Point;

import edu.rit.se.sse.rapdevx.clientmodels.Path;
import edu.rit.se.sse.rapdevx.gui.DrawablePath;
import edu.rit.se.sse.rapdevx.gui.Screen;

public class PathTestScreen extends Screen {
	
	DrawablePath path;

	public PathTestScreen(int width, int height) {
		
		super(width, height);
		
		Path path = new Path(new Point(0, 0));
		path.addPoint(new Point(300, 00));
		path .addPoint(new Point(800, 400));
		
		this.path = new DrawablePath(path);
	}

	public void update(boolean hasFocus, boolean isVisible) {
		
	}

	public void draw(Graphics2D gPen) {
		gPen.fillRect(0, 0, screenWidth, screenHeight);
		path.draw(gPen);
		
		
	}
	
	

}
