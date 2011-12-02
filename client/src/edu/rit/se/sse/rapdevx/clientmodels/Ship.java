/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

/**
 * @author Cody Krieger
 * 
 */
public class Ship /* this will eventually inherit from the API's Unit class */{
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
