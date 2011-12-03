package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;

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
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
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
	 * @return the height
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
	 * @return the xVel
	 */
	public int getxVel() {
		return xVel;
	}

	/**
	 * @param xVel the xVel to set
	 */
	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	/**
	 * @return the yVel
	 */
	public int getyVel() {
		return yVel;
	}

	/**
	 * @param yVel the yVel to set
	 */
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}
	
	public void update() {
		this.x += xVel;
		this.y += yVel;
	}
	
	public void draw(Graphics2D gPen) {
		
	}
}
