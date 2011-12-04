package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawableAttack extends DrawableObject
{
	private Point mouseLocation;
	private DrawableShip source;

	public DrawableAttack(DrawableShip source)
	{
		this.source = source;
		this.mouseLocation = null;
	}

	public void draw(Graphics2D gPen)
	{
		// Draw a line between the last coordinate and the mouse cursor
		if (mouseLocation != null && source != null)
		{
			Point start = source.getCenter();
			gPen.setColor(new Color(0, 255, 0));

			/*Area outside = new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
			outside.subtract(source.getBounds());
			
			gPen.setClip(outside);*/
			gPen.draw(source.getBounds());

			gPen.drawLine(start.x, start.y, mouseLocation.x, mouseLocation.y);
			
			drawRecticule(gPen, mouseLocation.x, mouseLocation.y);
		}
	}

	public void setMouseLocation(Point location)
	{
		this.mouseLocation = location;
	}
	
	/**
	 * draws a reticle at the specified location
	 * @param gPen
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	private void drawRecticule(Graphics2D gPen, int x, int y)
	{
		gPen.draw(new Ellipse2D.Double(x - 34, y - 34, 68, 68));
		
		gPen.drawLine(x - 50, y, x + 50, y);
		gPen.drawLine(x, y - 50, x, y + 50);
	}
}
