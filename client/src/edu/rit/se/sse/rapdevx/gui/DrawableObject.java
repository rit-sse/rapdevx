package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class DrawableObject implements Drawable {

	protected int x, y, width, height;
	protected int xVel, yVel;
	
	public DrawableObject() {
		this(0, 0, 0, 0);
	}
	
	public DrawableObject(int x, int y) {
		this(x, y, 0, 0);
	}
	
	public DrawableObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the x position of the object
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x position to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y position of the object
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y position to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Positions the object so that the object's center is located at the
	 * provided coordinates.
	 * 
	 * @param x the x position to set
	 * @param y the y position to set
	 */
	public void setCenter(int x, int y) {
		this.x = x - (width/2);
		this.y = y - (height/2);
	}

	/**
	 * @return the width of the object
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height of the object
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the x velocity of the object
	 */
	public int getxVel() {
		return xVel;
	}

	/**
	 * @param xVel the x velocity to set
	 */
	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	/**
	 * @return the y velocity of the object
	 */
	public int getyVel() {
		return yVel;
	}

	/**
	 * @param yVel the y velocity to set
	 */
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}
	
	/**
	 * Default update for a drawable object.  Moves the object according
	 * to the current x and y velocities that are set.
	 */
	public void update() {
		this.x += xVel;
		this.y += yVel;
	}
	
	public void draw(Graphics2D gPen) {
		draw(gPen, new Rectangle2D.Double(x, y, width, height));
	}
	
	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		
	}
}
