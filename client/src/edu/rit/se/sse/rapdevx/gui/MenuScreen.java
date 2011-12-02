/**
 * Provides a customizable list-style menu
 */
package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.util.List;

/**
 * @author McAfee
 *
 */
public class MenuScreen extends Screen{

	private List<MenuButton> menuButtons;
	
	/**
	 * Constructor
	 * 
	 * @param initx - starting x coordinate
	 * @param inity - starting y coordinate
	 * @param menuButtons - a list of buttons that will be on the menu, 
	 * 	top to bottom
	 */
	public MenuScreen(int initx, int inity, List<MenuButton> menuButtons){
		//DUMMY SUPER, THIS WILL BE BASED ON BUTTONS
		super(5,5);
		this.menuButtons = menuButtons;
		
		
		
	}
	
	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTransition(double position, int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D gPen) {
		// TODO Auto-generated method stub
		
	}
	
}
