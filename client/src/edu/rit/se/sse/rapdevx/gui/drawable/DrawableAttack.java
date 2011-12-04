package edu.rit.se.sse.rapdevx.gui.drawable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import edu.rit.se.sse.rapdevx.clientmodels.Attack;

public class DrawableAttack extends DrawableObject {

	private Point targetLocation;

	private Attack attack;
	private DrawableShip sourceShip;
	private DrawableShip targetShip;
	
	private boolean snapped;

	public DrawableAttack(DrawableShip sourceShip) {
		attack = new Attack();
		this.attack.setSourceShip(sourceShip.getShip());

		this.sourceShip = sourceShip;
		this.targetLocation = null;
		this.snapped = false;
	}

	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		// Create a dashed line
		float[] f1 = { 10.0f };
		gPen.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, f1, 0.0f));

		// Draw a line between the last coordinate and the mouse cursor
		if (targetLocation != null) {
			// Set the target red if there is a target, green otherwise
			if (snapped || targetShip != null)
				gPen.setColor(new Color(255, 0, 0));
			else
				gPen.setColor(new Color(0, 255, 0));

			// Clip the inside of the ship circle
			Area outside = new Area(bounds);
			outside.subtract(new Area(sourceShip.getBounds()));
			gPen.setClip(outside);

			// Draw the line and reticle
			gPen.drawLine(sourceShip.getCenter().x, sourceShip.getCenter().y,
					targetLocation.x, targetLocation.y);
			drawRecticule(gPen, targetLocation.x, targetLocation.y);
		}

		gPen.setStroke(new BasicStroke());
		gPen.setClip(null);
	}

	public void setMouseLocation(Point location) {
		// Set the mouse location as the target location
		// unless we are locked on to a ship
		if (attack.getTargetShip() == null)
			this.targetLocation = location;
	}
	
	public void setSnapped(boolean snapped) {
		this.snapped = snapped;
	}

	/**
	 * draws a reticle at the specified location
	 * 
	 * @param gPen
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	private void drawRecticule(Graphics2D gPen, int x, int y) {
		// Set the line thickness
		gPen.setStroke(new BasicStroke(2.0f));

		// Draw the circle
		gPen.draw(new Ellipse2D.Double(x - 34, y - 34, 68, 68));

		// Draw the crosshairs
		gPen.drawLine(x - 50, y, x + 50, y);
		gPen.drawLine(x, y - 50, x, y + 50);

		// Reset pen
		gPen.setColor(new Color(0, 255, 0));
		gPen.setStroke(new BasicStroke());
	}

	public void setTarget(DrawableShip target, Rectangle2D cameraBounds) {
		this.targetShip = target;
		this.attack.setTargetShip(target.getShip());
		this.targetLocation = target.getCenter();
	}
	
}
