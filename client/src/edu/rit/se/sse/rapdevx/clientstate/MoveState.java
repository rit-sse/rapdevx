/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class MoveState extends StateBase {
	public MoveState() {
		this.nextState = AttackState.class;
	}
}
