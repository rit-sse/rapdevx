package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private BufferedImage background;

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
		try {
			background = ImageIO.read(new File("assets/stats_screen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		gPen.drawImage(background, x, y, screenWidth, screenHeight, null);
		gPen.setColor(Color.BLACK);

		// sample name
		new Text("Awesome Ship", x + 10, y + 10).draw(gPen);

		// gPen.drawString(ship.getgID(), x + 10, y+ 20);

		drawHPBar(gPen);
		gPen.setColor(Color.BLACK);

		// sample move radius
		new Text("Move Radius: 5", x + 10, y + 65).draw(gPen);
		// gPen.drawString("Move Radius: " + ship.get)
		drawAbilities(gPen);
	}

	public void drawHPBar(Graphics2D gPen) {
		new Text("HP:", x + 25, y + 38).draw(gPen);

		// sample value
		double scale = 18.0 / 20.0 * 195.0;
		scale = 5.0 / 20.0 * 195.0;
		gPen.setColor(Color.WHITE);
		gPen.fill(new Rectangle(x + 65, y + 35, 195, 20));
		new Text(5 + "/" + 20, x + 65 + 145, y + 20).draw(gPen);
		if (scale < 1.0 / 3.0 * 195.0) {
			gPen.setColor(Color.RED);
		} else if (scale < 2.0 / 3.0 * 195.0) {
			gPen.setColor(Color.YELLOW);
		} else {
			gPen.setColor(Color.GREEN);
		}
		gPen.fill(new Rectangle(x + 65, y + 35, (int) scale, 20));
	}

	public void drawAbilities(Graphics2D gPen) {
		new Text("Abilities: ", x + 10, y + 90).draw(gPen);
		// sample abilities
		new Text("Ability 1: 5", x + 35, y + 110).draw(gPen);
		new Text("Ability 2: 6", x + 35, y + 130).draw(gPen);
		new Text("Ability 3: 7", x + 35, y + 150).draw(gPen);
	}

}
