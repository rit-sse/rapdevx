package edu.rit.se.sse.rapdevx.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawableAttack extends DrawableObject
{
	private Point mouseLocation;
	private DrawableShip source;

	private AttackScreen parent;

	private DrawableShip selectedShip;

	public DrawableAttack(DrawableShip source, AttackScreen parent)
	{
		this.source = source;
		this.mouseLocation = null;
		this.parent = parent;
	}

	public void draw(Graphics2D gPen)
	{
		if (selectedShip != null)
		{
			setMouseLocation(selectedShip.getCenter());
		}

		float[] f1 = { 10.0f };
		gPen.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, f1, 0.0f));

		// Draw a line between the last coordinate and the mouse cursor
		if (mouseLocation != null && source != null)
		{
			Point start = source.getCenter();
			gPen.setColor(new Color(0, 255, 0));

			Area outside = new Area(new Rectangle2D.Double(0, 0, parent.screenWidth,
					parent.screenHeight));
			outside.subtract(new Area(source.getBounds()));
			gPen.setClip(outside);

			gPen.drawLine(start.x, start.y, mouseLocation.x, mouseLocation.y);
			drawRecticule(gPen, mouseLocation.x, mouseLocation.y);
		}

		gPen.setStroke(new BasicStroke());
		gPen.setClip(null);
	}

	public void setMouseLocation(Point location)
	{
		this.mouseLocation = location;
	}

	/**
	 * draws a reticle at the specified location
	 * 
	 * @param gPen
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordinate
	 */
	private void drawRecticule(Graphics2D gPen, int x, int y)
	{
		selectedShip = null;
		gPen.setStroke(new BasicStroke(2.0f));

		// check if overlaying ship
		for (DrawableShip ship : parent.getShipList())
		{
			if (new Area(ship.getBounds()).contains(mouseLocation.x,
					mouseLocation.y) && ship != source)
			{
				gPen.setColor(new Color(255, 0, 0));
				selectedShip = ship;
			}
		}

		// draw circle
		gPen.draw(new Ellipse2D.Double(x - 34, y - 34, 68, 68));

		// draw plus
		gPen.drawLine(x - 50, y, x + 50, y);
		gPen.drawLine(x, y - 50, x, y + 50);

		// reset color
		gPen.setColor(new Color(0, 255, 0));
	}
}
