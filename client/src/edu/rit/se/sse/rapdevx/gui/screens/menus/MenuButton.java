/**
 * 
 */
package edu.rit.se.sse.rapdevx.gui.screens.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import edu.rit.se.sse.rapdevx.gui.RectangleBackground;
import edu.rit.se.sse.rapdevx.gui.Text;

/**
 * @author dpk3062
 * 
 */
public class MenuButton {

	private Dimension DefaultSize = new Dimension(200, 50);

	private Dimension size = DefaultSize;

	private static final String DefaultText = "???";
	private static final String DefaultHelp = "I don't know what this is too";

	private String text = MenuButton.DefaultText;
	private String help = MenuButton.DefaultHelp;

	private boolean selected = false;

	private int lastDrawnFromX = 0;
	private int lastDrawnFromY = 0;
	private int lastDrawnWidth = 0;
	private int lastDrawnHeight = 0;

	private boolean pressed;

	/**
	 * Create a button for a menu screen
	 * 
	 * @param text
	 *              The user visible button text/title
	 * @param help
	 *              A user visible description of the button
	 */
	public MenuButton(String text, String help) {
		setText(text);
		setHelp(help);
	}

	/**
	 * Sets the text/title of this button
	 * 
	 * @param text
	 *              The new button text. Use null for the default text
	 */
	public void setText(String text) {
		if (text == null) {
			text = MenuButton.DefaultText;
		}

		this.text = text;
	}

	/**
	 * Sets the description of this button
	 * 
	 * @param help
	 *              The new button description. Use null for the default
	 *              description
	 */
	public void setHelp(String help) {
		if (help == null) {
			help = MenuButton.DefaultHelp;
		}

		this.help = help;
	}

	/**
	 * Sets the size of this button
	 * 
	 * @param width
	 *              The width of the button
	 * @param height
	 *              The height of the button
	 */
	private void setSize(int width, int height) {
		size = new Dimension(width, height);
	}

	/**
	 * Gets the title/text of this button
	 * 
	 * @return A non-null String
	 */
	public String getText() {
		assert (text != null);
		return text;
	}

	/**
	 * Gets a helpful description of this button
	 * 
	 * @return A non-null String
	 */
	public String getHelp() {
		assert (help != null);
		return help;
	}

	/**
	 * @return true if this button shouldn't be displayed, else false
	 */
	public boolean isHidden() {
		// TODO determine if this button should be displayed based on some
		// game state or other conditions
		return false;
	}

	/**
	 * Gets the current size of this button
	 * 
	 * @return This button's size. Will not be null
	 */
	public Dimension getSize() {
		assert (size != null);
		return size;
	}

	/**
	 * Scales the button based off of it's original size and the given scale
	 * value
	 * 
	 * @param scale
	 *              The amount of scaling. 0.5 means reduce size by a half.
	 *              1.0 means no change
	 */
	private void adjustSize(double scale) {
		// TODO support different amounts of width and height scaling?

		// adjust main button
		int width = (int) (DefaultSize.width * scale);
		int height = (int) (DefaultSize.height * scale);
		setSize(width, height);

		// adjust arcs
	}

	/**
	 * Draws this button on the given graphics. Caller should check isHidden()
	 * to see if this button is supposed to be drawn or not
	 * 
	 * @param gPen
	 *              The graphics will this button will be drawn
	 * @param x
	 *              The x-axis position of this button's top left corner
	 * @param y
	 *              The y-axis position of this button's top right corner
	 * @param scale
	 *              The amount of scaling to apply to this button's default
	 *              size. 0.5 means reduce size by a half. 1.0 means no change
	 */
	public void draw(Graphics2D gPen, int x, int y, double scale) {
		// apply scaling
		// TODO cache the last scaling value to reduce calculations while
		// drawing. When setArc() or setSize() is called again or the
		// scaling value is changed, clear the cache
		adjustSize(scale);
		Dimension size = getSize();

		// draw the main button and remember where we put it for event
		// handling
		new RectangleBackground(x, y, size.width, size.height).draw(gPen);
		if (selected) {
			gPen.setColor(new Color(30, 127, 255));
			gPen.fill(new Rectangle(x, y, 12, 4));
			gPen.fill(new Rectangle(x, y, 4, 12));
			gPen.fill(new Rectangle(x + size.width - 12, y, 12, 4));
			gPen.fill(new Rectangle(x + size.width - 4, y, 4, 12));
			gPen.fill(new Rectangle(x, y + size.height - 12, 4, 12));
			gPen.fill(new Rectangle(x, y + size.height - 4, 12, 4));
			gPen.fill(new Rectangle(x + size.width - 12, y + size.height
					- 4, 12, 4));
			gPen.fill(new Rectangle(x + size.width - 4, y + size.height
					- 12, 4, 12));
		}
		if (pressed) {
			// right
			Color color1 = new Color(76, 76, 76);
			// bottom
			Color color2 = new Color(84, 84, 84);
			// bottom left
			Color color3 = new Color(72, 72, 72);
			// top left
			Color color4 = new Color(66, 66, 66);
			// background
			Color color5 = new Color(73, 73, 73);
			gPen.setColor(color4);
			gPen.fill(new Rectangle(x, y, 4, size.height - 4));
			gPen.fill(new Rectangle(x + 4, y, size.width - 4, 4));
			gPen.setColor(color1);
			gPen.fill(new Rectangle(x + size.width - 4, y, 4, size.height));
			gPen.setColor(color3);
			gPen.fill(new Rectangle(x, y + size.height - 4, 4, 4));
			gPen.setColor(color2);
			gPen.fill(new Rectangle(x + 4, y + size.height - 4,
					size.width - 8, 4));
			gPen.setColor(color5);
			gPen.fill(new Rectangle(x + 4, y + 4, size.width - 8,
					size.height - 8));
		}
		lastDrawnFromX = x;
		lastDrawnFromY = y;
		lastDrawnWidth = size.width;
		lastDrawnHeight = size.height;

		// display the button's text
		// TODO better adjust x and y so text isn't right up against button
		// edge
		// gPen.setColor(getTextColor());
		// gPen.setFont(TextFont);
		int textY = y + size.height / 2 - 10;
		new Text(getText(), x + 10, textY, "white", 3).draw(gPen);

		// TODO adjust the border depending on if selected or not
	}

	public boolean includesPoint(int x, int y) {
		// TODO handle when width or height is negative and thus x or y
		// would be below lastDrawnFrom or above lastDrawn :/
		return !(x < lastDrawnFromX
				|| x > (lastDrawnFromX + lastDrawnWidth)
				|| y < lastDrawnFromY || y > (lastDrawnFromY + lastDrawnHeight));
	}

	/**
	 * Sets is this button is selected/highlighted or not
	 * 
	 * @param selected
	 *              true if the button is currently selected, else false
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;

	}

	public void clicked() {
		// TODO make some visual or audio changes to show that we were
		// clicked
		System.out.println("MenuButton: clicked: " + getText());
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;

	}

	public void released(int x, int y) {
		// check to see if we were 'clicked'
		if (pressed && includesPoint(x, y)) {
			clicked();
		}

		pressed = false;
	}
}
