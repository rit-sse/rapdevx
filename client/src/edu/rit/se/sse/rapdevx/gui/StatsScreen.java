package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;

/**
 * The stats screen that shows up when you hover
 * 
 * @author Kristen Mills
 * 
 */
public class StatsScreen extends Screen {

	private int x;
	private int y;
	private Ship ship;

	/**
	 * Constrctor for the Stats screen
	 * 
	 * @param width
	 *              the width of the screen
	 * @param height
	 *              the height of the screen
	 * @param wWidth
	 *              the width of the window
	 * @param wHeight
	 *              the height of the window
	 */
	public StatsScreen(int width, int height, int wWidth, int wHeight,
			Ship ship) {
		super(width, height);
		x = wWidth - screenWidth;
		y = wHeight - screenHeight;
		this.ship = ship;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTransition(double position, int direction) {
		// TODO Auto-generated method stub

	}

	/**
	 * It says awesome Ship!!!!
	 */
	public void draw(Graphics2D gPen) {
		gPen.setColor(Color.gray);
		gPen.fill(new Rectangle(x, y, screenWidth, screenHeight));
		gPen.setColor(Color.black);
		gPen.drawString("Awesome Ship", x + 10, y + 20);

	}

}
