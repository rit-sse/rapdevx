/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import java.util.Vector;

/**
 * @author Cody Krieger
 * 
 */
public class Player {
	private Vector<Ship>	ships	= new Vector<Ship>();

	/**
	 * @return the ships
	 */
	public Vector<Ship> getShips() {
		return ships;
	}

	public void addShip(Ship s) {
		ships.add(s);
	}

	public void removeShip(Ship s) {
		ships.remove(s);
	}
}
