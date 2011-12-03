/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import java.util.List;

/**
 * @author Cody Krieger
 * 
 */
public class ShipFactory {

	public static Ship makeShip(/* ShipClass shipClass */) {
		return (new Ship());
	}

	public static List<Ship> makeShips(
			List<edu.rit.se.sse.rapdevx.api.dataclasses.Ship> ships) {
		return null;
	}

}
