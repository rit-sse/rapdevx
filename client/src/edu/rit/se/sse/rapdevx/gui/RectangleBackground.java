package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The awesome grey rectangle
 * 
 * @author Kristen Mills
 * 
 */
public class RectangleBackground {

	private int width;
	private int height;
	private int x;
	private int y;
	private boolean pressed;
	private boolean hover;

	/**
	 * Constructor for the rectangle background
	 * 
	 * @param x
	 *              the starting x coordinate
	 * @param y
	 *              the starting y coordinate
	 * @param width
	 *              the width
	 * @param height
	 *              the height
	 */
	public RectangleBackground(int x, int y, int width, int height) {
		this(x, y, width, height, false, false);
	}
	
	/**
	 * Constructor for the rectangle background
	 * 
	 * @param x
	 *              the starting x coordinate
	 * @param y
	 *              the starting y coordinate
	 * @param width
	 *              the width
	 * @param height
	 *              the height
	 * @param pressed
	 * 				pressed state
	 */
	public RectangleBackground(int x, int y, int width, int height,
			boolean pressed) {
		this(x, y, width, height, pressed, false);
	}
	
	/**
	 * Constructor for the rectangle background
	 * 
	 * @param x
	 *              the starting x coordinate
	 * @param y
	 *              the starting y coordinate
	 * @param width
	 *              the width
	 * @param height
	 *              the height
	 * @param pressed
	 * 				pressed state
	 * @param hover
	 * 				pressed state
	 */
	public RectangleBackground(int x, int y, int width, int height, boolean pressed, boolean hover) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.pressed = pressed;
		this.hover = hover;
	}

	/**
	 * Draw the rectangle
	 * 
	 * @param gPen
	 *              the Graphics 2d pen
	 */
	public void draw(Graphics2D gPen) {
		Color color1 = new Color(76, 76, 76);
		Color color2 = new Color(84, 84, 84);
		Color color3 = new Color(72, 72, 72);
		Color color4 = new Color(66, 66, 66);
		Color color5 = new Color(73, 73, 73);

		if (pressed) {
			gPen.setColor(color4);
			gPen.fill(new Rectangle(x, y, 4, height - 4));
			gPen.fill(new Rectangle(x + 4, y, width - 4, 4));
			gPen.setColor(color1);
			gPen.fill(new Rectangle(x + width - 4, y, 4, height));
			gPen.setColor(color3);
			gPen.fill(new Rectangle(x, y + height - 4, 4, 4));
			gPen.setColor(color2);
			gPen.fill(new Rectangle(x + 4, y + height - 4, width - 8, 4));
			gPen.setColor(color5);
			gPen.fill(new Rectangle(x + 4, y + 4, width - 8, height - 8));
		} else {
			gPen.setColor(color1);
			gPen.fill(new Rectangle(x, y, 4, height));
			gPen.setColor(color2);
			gPen.fill(new Rectangle(x + 4, y, width - 4, 4));
			gPen.setColor(color3);
			gPen.fill(new Rectangle(x + width - 4, y, 4, 4));
			gPen.setColor(color4);
			gPen.fill(new Rectangle(x + width - 4, y + 4, 4, height - 4));
			gPen.fill(new Rectangle(x + 4, y + height - 4, width - 4, 4));
			gPen.setColor(color5);
			gPen.fill(new Rectangle(x + 4, y + 4, width - 8, height - 8));
		}
		
		if (hover) {
			gPen.setColor(new Color(30, 127, 255));
			gPen.fill(new Rectangle(x, y, 12, 4));
			gPen.fill(new Rectangle(x, y, 4, 12));
			gPen.fill(new Rectangle(x + width - 12, y, 12, 4));
			gPen.fill(new Rectangle(x + width - 4, y, 4, 12));
			gPen.fill(new Rectangle(x, y + height - 12, 4, 12));
			gPen.fill(new Rectangle(x, y + height - 4, 12, 4));
			gPen.fill(new Rectangle(x + width - 12, y + height
					- 4, 12, 4));
			gPen.fill(new Rectangle(x + width - 4, y + height
					- 12, 4, 12));
		}
	}

	public void setPressed(boolean isPressed) {
		pressed = isPressed;
	}
	
	public void setHover(boolean hover) {
		this.hover = hover;
	}
}
