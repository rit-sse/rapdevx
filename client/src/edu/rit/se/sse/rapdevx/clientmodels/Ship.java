/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import edu.rit.se.sse.rapdevx.api.dataclasses.Unit;

/**
 * @author Cody Krieger
 * 
 */
public class Ship extends Unit {
	private int	X;
	private int	Y;

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
