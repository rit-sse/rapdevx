package edu.rit.se.sse.rapdevx.api.dataclasses;

/**
 * POJO representing a Game on the server side of things. Will be
 * annotated in order to have Jackson data bindings.
 *
 * @author Ben Nicholas
 */
public class Game {

	private String name;
	private int id;
	
	public Game() {
		
	}
	
	public int getID() {
		return 1; // Ha. Implementation awaits.
	}
}