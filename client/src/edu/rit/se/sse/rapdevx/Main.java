package edu.rit.se.sse.rapdevx;

import edu.rit.se.sse.rapdevx.gui.Window;

/**
 * Stand-in Java source file
 * 
 * @author Sean Congden
 */
public class Main {

	public static final int UPDATES_PER_SECOND = 40;
	public static final int UDATE_TICK = 1000 / UPDATES_PER_SECOND;
	public static final int MAX_UPDATE_SKIP = 5;
	
	public static final int DRAWS_PER_SECOND = 40;
	public static final int DRAW_TICK = 1000 / DRAWS_PER_SECOND;
	
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
		Window window = new Window("RapdevX", false);
		
		// Save the current system time
		long nextUpdate = System.currentTimeMillis();
		long nextDraw = nextUpdate;
		
		// The main game loop
		while (!done) {
			// Catch up on update loops
			int loops = 0;
			while (System.currentTimeMillis() > nextUpdate && loops < MAX_UPDATE_SKIP) {
				// Update any movements and animations
				window.update();
				
				nextUpdate += UDATE_TICK;
				loops++;
			}
			
			// Draw the screen
			if (System.currentTimeMillis() >= nextDraw) {
				window.render();
				nextDraw = DRAW_TICK + System.currentTimeMillis();
			}
			
			// Wait until about the next update time
			try {
				Thread.sleep(nextDraw - System.currentTimeMillis());
			} catch (InterruptedException e) {}
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
