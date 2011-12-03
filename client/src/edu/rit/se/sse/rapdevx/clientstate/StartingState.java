/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class StartingState extends StateBase {
	public StartingState() {
		this.nextState = UnitPlacementState.class;
	}
}
