package edu.rit.se.sse.rapdevx.gui.drawable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import edu.rit.se.sse.rapdevx.clientmodels.Path;

/**
 * 
 * @author Steven Brunwasser
 * 
 *         Keeps track of a ship and its path created.
 * 
 */
public class DrawableMovePath extends DrawableObject {

	/** The path for the move */
	private Path path;

	/** The Drawable Path and Ship */
	private DrawableShip drawableShip;

	/** Does the MovePath accept new input? */
	private boolean acceptInput;

	private Point mouseLocation;
	private boolean mouseLocationValid;

	/**
	 * constructor
	 * 
	 * @param givenShip
	 *            the drawable ship that we are making the path for
	 */
	public DrawableMovePath(DrawableShip givenShip) {
		drawableShip = givenShip;
		path = new Path(new Point(drawableShip.getUnit().getX(), drawableShip
				.getUnit().getY()));

		acceptInput = true;
		mouseLocationValid = true;
	}

	/**
	 * get the path
	 * 
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	public DrawableShip getDrawableShip() {
		return drawableShip;
	}

	/**
	 * add a move to the path
	 * 
	 * @param point
	 *            the point to add to the path
	 */
	public void addMove(Point point) {
		if (acceptInput && mouseLocationValid)
			path.addPoint(point);
	}

	/**
	 * remove the last move added to the path
	 * 
	 * @pre we are accepting input and the path has more than one point
	 */
	public void removeLastMove() {
		if (acceptInput && !this.hasInitialPath())
			path.removePoint();
	}

	/**
	 * draw the path to the graphics
	 * 
	 * @param gPen
	 *            the 2D Graphics
	 */
	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		gPen.setColor(drawableShip.getColor());
		
		// Draw a line between the last coordinate and the mouse cursor
		if (mouseLocation != null && path.getLastPoint() != null) {
			Point lastLocation = path.getLastPoint();
			
			if ( !mouseLocationValid )
				gPen.setColor( Color.RED );
			
			// Create a dashed line
			float[] f1 = { 10.0f };
			gPen.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, f1, 0.0f));
			
			gPen.drawLine(lastLocation.x, lastLocation.y,
					mouseLocation.x, mouseLocation.y);
			
			// Reset the line style
			gPen.setStroke(new BasicStroke());
			
			if ( !mouseLocationValid )
				gPen.setColor(drawableShip.getColor());
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
		
		if (mouseLocation != null)
			drawableShip.drawClear(gPen, bounds, mouseLocation);
	}

	/**
	 * set the current mouse location to make a line follow the mouse
	 * 
	 * @param mouseLocation
	 *            the point where the mouse lies
	 * @pre we are accepting input
	 * @post if we are not accepting input, then the mouseLocation is set to
	 *       null
	 */
	public void setMouseLocation(Point mouseLocation) {
		if (acceptInput) {
			this.mouseLocation = mouseLocation;
		} else {
			this.mouseLocation = null;
		}
	}

	/**
	 * is the given point relatively close to the previously added point?
	 * 
	 * @param point
	 *            the point to compare to
	 * @param range
	 *            the range of values that could trigger a return of true
	 * @return true if the points are close, false otherwise
	 */
	public boolean hasPointCloseToPrevious(Point point, int range) {
		Point lastPoint = path.getLastPoint();
		if (lastPoint.getX() < point.getX() + range
				&& lastPoint.getX() > point.getX() - range) {
			if (lastPoint.getY() < point.getY() + range
					&& lastPoint.getY() > point.getY() - range) {
				return true;
			}
		}
		return false;
	}

	/**
	 * stop accepting input
	 * 
	 * @post we are no longer accepting input sets the ship's path. (this is
	 *       permanent to the object)
	 */
	public void stopInput() {
		acceptInput = false;
	}

	/**
	 * are we accepting input?
	 * 
	 * @return true if we are accepting input
	 */
	public boolean isAcceptingInput() {
		return acceptInput;
	}

	/**
	 * do we have the initial path?
	 * 
	 * @return true if the path is of size 1, false otherwise. the path will
	 *         never be less than 1, guaranteed.
	 */
	public boolean hasInitialPath() {
		if (path.getPath().size() == 1)
			return true;
		else
			return false;
	}

	/**
	 * this makes the mouse line red and disables the ability to add points.
	 */
	public void setMouseLocationInvalid() {
		mouseLocationValid = false;
	}

	/**
	 * this makes the mouse line the pathColor and enables the ability to add
	 * points.
	 */
	public void setMouseLocationValid() {
		mouseLocationValid = true;
	}

}
