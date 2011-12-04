/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import java.util.List;

import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;
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

	/**
	 * Convenience method for getting a ship's class name.
	 * 
	 * @return The class name of the ship.
	 */
	public String getClassName() {
		List<ShipClass> classes = AssetLibrary.getShipClasses();
		for (ShipClass c : classes) {
			if (c.getGid() == this.getClassID())
				return "Awesome Ship!";
		}

		return "Unknown Class";
	}
}
