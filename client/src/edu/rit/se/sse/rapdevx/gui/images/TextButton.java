package edu.rit.se.sse.rapdevx.gui.images;

import java.awt.Graphics2D;

import edu.rit.se.sse.rapdevx.gui.drawable.Text;
import edu.rit.se.sse.rapdevx.gui.screens.menus.Menu;

public class TextButton extends RectangleBackground {

	Text buttonText;
	Menu menu;
	String text;

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
	public TextButton(int x, int y, int width, int height, String text, Menu menu) {
		this(x, y, width, height, false, false, text,menu);
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
	public TextButton(int x, int y, int width, int height, boolean pressed, String text, Menu menu) {
		this(x, y, width, height, pressed, false, text, menu);
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
	public TextButton(int x, int y, int width, int height, boolean pressed,
			boolean hover, String text, Menu menu) {
		super(x, y, width, height, pressed, hover);
		buttonText = new Text(text, x + 10, y + 10);
		this.menu = menu;
		this.text = text;
	}
	
	public void draw(Graphics2D gPen){
		super.draw(gPen);
		buttonText.draw(gPen);
	}
	
	public Menu getMenu(){
		return menu;
	}
	
	public String getText(){
		return text;
	}
}
