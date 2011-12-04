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
	}

	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTransition(double position, int direction) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics2D gPen) {
		new RectangleBackground(x, y, screenWidth, screenHeight).draw(gPen);
		gPen.setColor(Color.BLACK);

		// sample name
		new Text("Awesome Ship", x + 10, y + 10).drawColor(gPen,0xFF7f7f7f);

		// real stuff
		// new Text(ship.getgID(), x + 10, y+ 20).drawColor(gPen,0xFF7f7f7f);

		drawHPBar(gPen);
		gPen.setColor(Color.BLACK);

		// sample move radius
		new Text("Move Radius 5", x + 10, y + 65).drawColor(gPen,0xFF7f7f7f);

		// real stuff
		// new Text(
		// "Move Radius: "
		// + GameSession.get().findByClassId(
		// ship.getClassID()).getRadius(), x + 10,
		// y + 65).drawColor(gPen,0xFF7f7f7f);
		drawAbilities(gPen);
	}

	public void drawHPBar(Graphics2D gPen) {
		new Text("HP", x + 25, y + 38).drawColor(gPen,0xFF7f7f7f);
		// sample value
		double hp = 5.0;
		double maxHp = 20.0;

		// real stuff
		// double hp = ship.getHp();
		// double maxHp = GameSession.get().findByClassId(ship.getClassID())
		// .getMaxhp();
		double scale = hp / maxHp * 195.0;
		gPen.setColor(new Color(127,127,127));
		gPen.fill(new Rectangle(x + 65, y + 35, 195, 20));
		new Text((int) hp + "/" + (int) maxHp, x + 65 + 145, y + 20)
				.drawColor(gPen,0xFF7f7f7f);
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
		new Text("Abilities ", x + 10, y + 90).drawColor(gPen,0xFF7f7f7f);
		// sample abilities
		new Text("Ability 1  Radius 5  Damage 6", x + 20, y + 110, 1.5)
				.drawColor(gPen,0xFF7f7f7f);
		new Text("Ability 2  Radius 6  Damage 6", x + 20, y + 125, 1.5)
				.drawColor(gPen,0xFF7f7f7f);
		new Text("Ability 3  Radius 7  Damage 8", x + 20, y + 140, 1.5)
				.drawColor(gPen,0xFF7f7f7f);
		new Text("Ability 4  Radius 5  Damage 6", x + 20, y + 155, 1.5)
				.drawColor(gPen,0xFF7f7f7f);
		new Text("Ability 5  Radius 6  Damage 6", x + 20, y + 170, 1.5)
				.drawColor(gPen,0xFF7f7f7f);

		// real stuff
		// int yPosition = 110;
		// List<Ability> abilities = GameSession.get()
		// .findByClassId(ship.getClassID()).getAbilities();
		// for (Ability ability : abilities) {
		// new Text(ability.getName() + ": Radius: "
		// + ability.getRadius() + " Damage: "
		// + ability.getDefault_damage(), x + 35, y
		// + yPosition, 1.5).drawColor(gPen,0xFF7f7f7f);
		// yPosition += 10;
		// }
	}

}
