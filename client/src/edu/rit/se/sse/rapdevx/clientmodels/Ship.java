/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

/**
 * @author Cody Krieger
 * 
 */
public class Ship extends edu.rit.se.sse.rapdevx.api.dataclasses.Ship {
	private int	X;
	private int	Y;

	public Ship() {
	}

	public Ship(edu.rit.se.sse.rapdevx.api.dataclasses.Ship ship, int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return X;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		X = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		Y = y;
	}
}
