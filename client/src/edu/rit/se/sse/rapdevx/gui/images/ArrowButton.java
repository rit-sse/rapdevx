package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class ArrowButton extends RectangleBackground {

	public enum Direction { UP, DOWN, LEFT, RIGHT };
	
	private Polygon arrow;
	private boolean isEnabled;
	
	public ArrowButton(int x, int y, int width, int height, Direction direction) {
		super(x, y, width, height);
		this.isEnabled = true;
		
		//TODO remove placement screen specifics
		arrow = new Polygon();
		if (direction == Direction.UP) {
			arrow.addPoint(x + 7, y + 26);
			arrow.addPoint(x + 65, y + 26);
			arrow.addPoint(x + 37, y + 5);
		} else {
			arrow.addPoint(x + 7, y + 5);
			arrow.addPoint(x + 65, y + 5);
			arrow.addPoint(x + 37, y + 26);
		}
	}
	
	public void draw(Graphics2D gPen) {
		if (!isEnabled) {
			this.setHovering(false);
			this.setPressed(false);
		}
		
		super.draw(gPen);
		
		if (isEnabled) {
			gPen.setColor(Color.LIGHT_GRAY);
			gPen.fillPolygon(arrow);
		}
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

}
