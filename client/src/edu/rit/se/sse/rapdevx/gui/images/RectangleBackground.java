package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The awesome grey rectangle
 * 
 * @author Kristen Mills
 * 
 */
public class RectangleBackground implements IGrayableImage {

	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected boolean pressed;
	protected boolean hovering;
	
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

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
	 *              pressed state
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
	 *              pressed state
	 * @param hover
	 *              pressed state
	 */
	public RectangleBackground(int x, int y, int width, int height,
			boolean pressed, boolean hover) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.pressed = pressed;
		this.hovering = hover;
	}

	/**
	 * Draw the rectangle
	 * 
	 * @param gPen
	 *              the Graphics 2d pen
	 */
	public void draw(Graphics2D gPen) {
		Color color1 = new Color(76, 76, 76); // left
		Color color2 = new Color(84, 84, 84); // top
		Color color3 = new Color(72, 72, 72); // top right corner
		Color color4 = new Color(66, 66, 66); // bottom and right
		Color color5 = new Color(73, 73, 73); // background

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

		if (hovering) {
			gPen.setColor(new Color(30, 127, 255));
			gPen.fill(new Rectangle(x, y, 12, 4));
			gPen.fill(new Rectangle(x, y, 4, 12));
			gPen.fill(new Rectangle(x + width - 12, y, 12, 4));
			gPen.fill(new Rectangle(x + width - 4, y, 4, 12));
			gPen.fill(new Rectangle(x, y + height - 12, 4, 12));
			gPen.fill(new Rectangle(x, y + height - 4, 12, 4));
			gPen.fill(new Rectangle(x + width - 12, y + height - 4, 12, 4));
			gPen.fill(new Rectangle(x + width - 4, y + height - 12, 4, 12));
		}
	}

	public void setPressed(boolean isPressed) {
		pressed = isPressed;
	}

	@Override
	public void setHovering(boolean isHovering) {
		hovering = isHovering;
	}

	@Override
	public boolean containsPoint(Point point) {
		return !(point.x < x || point.x > (x + width) || point.y < y || point.y > (y + height));
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	/**
	 * @param listener
	 *              the listener to add
	 */
	public synchronized void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	/**
	 * @param observer
	 *              the observer to remove
	 */
	public synchronized void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}

	private synchronized void notifyActionListeners() {
		for (ActionListener listener : listeners) {
			ActionEvent event = new ActionEvent(this, 0, "text");
			listener.actionPerformed(event);
		}
	}
	public void clicked() {
		notifyActionListeners();
	}
	public void released(int x, int y) {
		// check to see if we were 'clicked'
		if (pressed && containsPoint(new Point(x, y))) {
			clicked();
		}

		pressed = false;
	}
}
