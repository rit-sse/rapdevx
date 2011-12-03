/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientstate;

/**
 * @author Cody Krieger
 * 
 */
public class StateBase {

	protected Class<?>	nextState;

	/**
	 * @return the nextState
	 */
	public Class<?> getNextState() {
		return nextState;
	}

	public StateBase advance() {
		try {
			return (StateBase) nextState.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
