/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import java.util.Vector;

import edu.rit.se.sse.rapdevx.api.dataclasses.Unit;

/**
 * @author Cody Krieger
 * 
 */
public class Player {
	private Vector<Unit>	units	= new Vector<Unit>();

	/**
	 * @return the ships
	 */
	public Vector<Unit> getUnits() {
		return units;
	}

	/**
	 * @param s
	 */
	public void addUnit(Unit s) {
		units.add(s);
	}

	/**
	 * @param s
	 */
	public void removeUnit(Unit s) {
		units.remove(s);
	}
}
