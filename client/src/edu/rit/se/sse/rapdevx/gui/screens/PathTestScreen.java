package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Graphics2D;
import java.awt.Point;

import edu.rit.se.sse.rapdevx.clientmodels.Path;
import edu.rit.se.sse.rapdevx.gui.DrawablePath;
import edu.rit.se.sse.rapdevx.gui.Screen;

public class PathTestScreen extends Screen{
	
	DrawablePath path;

	public PathTestScreen(int width, int height) {
		
		super(width, height);
		
		this.path = new DrawablePath(new Path(new Point(10,5)));
		
	}

	public void update(boolean hasFocus, boolean isVisible) {
		
		
		
	}

	public void draw(Graphics2D gPen) {
		
		
		
	}
	
	

}
