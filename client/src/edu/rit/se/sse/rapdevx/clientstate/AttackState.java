/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class AttackState extends StateBase {
	public AttackState() {
		this.nextState = DoneState.class;
	}
}
