/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class UnitPlacementState extends StateBase {
	public UnitPlacementState() {
		this.nextState = MoveState.class;
	}
}
