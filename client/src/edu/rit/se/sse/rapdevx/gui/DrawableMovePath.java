package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import edu.rit.se.sse.rapdevx.clientmodels.Path;
import edu.rit.se.sse.rapdevx.clientmodels.Ship;

/**
 * 
 * @author Steven Brunwasser
 * 
 * Keeps track of a ship and its path created.
 *
 */
public class DrawableMovePath extends DrawableObject {
	
//	private Ship ship;
	/** The path for the move */
	private Path path;
	
	/** The Drawable Path and Ship */
	private DrawablePath drawablePath;
	private DrawableShip drawableShip;
	
	/** Does the MovePath accept new input? */
	private boolean acceptInput;
	
	private Point mouseLocation;
	
//	public DrawableMovePath( Ship givenShip ) {
//		ship = givenShip;
//		path = new Path( new Point( ship.getX(), ship.getY() ) );
//		
//		drawablePath = new DrawablePath( path );
//	}
	
	/**
	 * constructor
	 * 
	 * @param	givenShip	- the drawable ship that we are maing the path for
	 */
	public DrawableMovePath( DrawableShip givenShip ) {
		drawableShip = givenShip;
		path = new Path( new Point( drawableShip.getX() + drawableShip.getWidth() / 2, 
				drawableShip.getY() + drawableShip.getHeight() / 2 ) );
		
		drawablePath = new DrawablePath( path, drawableShip.getColor() );
		
		acceptInput = true;
	}
	
	/**
	 * get the path
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}
	
	/**
	 * add a move to the path
	 * @param point - the point to add to the path
	 */
	public void addMove( Point point ) {
		if ( acceptInput )
			path.addPoint( point );
	}
	
	/**
	 * remove the last move added to the path
	 * 
	 * @pre we are accepting input and the path has more than one point
	 */
	public void removeLastMove() {
		if ( acceptInput && !this.hasInitialPath() )
			path.removePoint();
	}
	
	/**
	 * draw the path to the graphics
	 * 
	 * @param	gPen	- the 2D Graphics
	 */
	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		drawablePath.draw( gPen );
		if ( mouseLocation != null )
			drawableShip.drawClear( gPen, bounds, mouseLocation );
	}
	
	/**
	 * set the current mouse location to make a line follow the mouse
	 * @param mouseLocation - the point where the mouse lies
	 * @pre	we are accepting input
	 * @post if we are not accepting input, then the mouseLocation is set to null
	 */
	public void setMouseLocation( Point mouseLocation ) {
		if ( acceptInput ) {
			drawablePath.setMouseLocation( mouseLocation );
			this.mouseLocation = mouseLocation;
		} else {
			drawablePath.setMouseLocation( null );
			this.mouseLocation = null;
		}
	}
	
	/**
	 * is the given point relatively close to the previously added point?
	 * @param point - the point to compare to
	 * @param range - the range of values that could trigger a return of true
	 * @return true if the points are close, false otherwise
	 */
	public boolean hasPointCloseToPrevious( Point point, int range ) {
		Point lastPoint = path.getLastPoint();
		if ( lastPoint.getX() < point.getX() + range && lastPoint.getX() > point.getX() - range ) {
			if ( lastPoint.getY() < point.getY() + range  && lastPoint.getY() > point.getY() - range ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * stop accepting input
	 * @post we are no longer accepting input
	 * 		 sets the ship's path.
	 * 		 (this is perminant to the object)
	 */
	public void stopInput() {
		acceptInput = false;
		drawableShip.setPath( drawablePath );
	}
	
	/**
	 * are we accepting input?
	 * @return true if we are accepting input
	 */
	public boolean isAcceptingInput() {
		return acceptInput;
	}
	
	/**
	 * do we have the initial path?
	 * @return  true if the path is of size 1, false otherwise.
	 * 			the path will never be less than 1, guaranteed.
	 */
	public boolean hasInitialPath() {
		if ( path.getPath().size() == 1 )
			return true;
		else
			return false;
	}

}
