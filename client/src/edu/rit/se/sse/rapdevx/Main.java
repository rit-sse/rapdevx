package edu.rit.se.sse.rapdevx;

import edu.rit.se.sse.rapdevx.gui.Window;

/**
 * Stand-in Java source file
 * 
 * @author Sean Congden
 */
public class Main {

	public static final int UPDATES_PER_SECOND = 25;
	public static final int UDATE_TICKS = 1000 / UPDATES_PER_SECOND;
	public static final int MAX_UPDATE_SKIP = 5;
	
	private boolean done = false;
	
	/**
	 * Exits the game on the next render loop.
	 */
	public void exit() {
		done = true;
	}

	
	/**
	 * The main game loop. Creates a GUI window, updates objects on screen, and
	 * renders the screen.
	 */
	public void run() {
		// Create a new window
		Window window = new Window("RapdevX");
		
		// Save the current system time
		long nextUpdate = System.currentTimeMillis();
		
		// The main game loop
		while (!done) {
			int loops = 0;
			while (System.currentTimeMillis() > nextUpdate && loops < MAX_UPDATE_SKIP) {
				// Update any movements and animations
				window.update();
				
				// Update any movements and animations
				window.render();
				
				nextUpdate += UDATE_TICKS;
				loops++;
			}
		}
	}

	/**
	 * Sample main method.  Starts the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

}
