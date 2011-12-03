/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import java.util.List;

import edu.rit.se.sse.rapdevx.api.dataclasses.Unit;

/**
 * @author Cody Krieger
 * 
 */
public class ShipFactory {

	/**
	 * Factory method for creating a single Ship from an API Ship.
	 * 
	 * @param ship
	 *            The Ship to convert.
	 * @param location
	 *            The location of the API Ship.
	 * @return A client Ship.
	 */
	public static Ship makeShip(Unit ship) {
		Ship newShip = new Ship();

		// newShip.setX(location.getX());
		// newShip.setY(location.getY());

		return newShip;
	}

	/**
	 * Ship factory for a list of API Ships.
	 * 
	 * @param ships
	 *            List of Ship classes from the API.
	 * @return A List of client Ship classes.
	 */
	public static List<Ship> makeShips(List<Unit> ships) {
		return null;
	}

}
