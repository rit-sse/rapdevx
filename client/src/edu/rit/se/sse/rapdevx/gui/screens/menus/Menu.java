/**
 * 
 */
package edu.rit.se.sse.rapdevx.gui.screens.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import edu.rit.se.sse.rapdevx.gui.Screen;


/**
 * @author dpk3062
 */
public class Menu extends Screen {
	
	private final Color BackgroundColor = Color.gray.darker().darker();
	
	//Holds all of this menu's buttons
	private final Collection<MenuButton> buttons = new ArrayList<MenuButton>();
	
	//spacing vars
	private static final int Border = 5;	//space between menu and button edges
	private static final int Spacing = 5;	//space between buttons
	private static final int Scale = 1;
	
	private static final int EmptyWidth = Border + Border;
	private static final int EmptyHeight = Border;
	
	//true if this menu shouldn't be displayed, else false
	private boolean isHidden = false;	//TODO make a getter
	
	//position
	private int cornerX = 0;
	private int cornerY = 0;
	
	//--------------------------------------------------------------------------
	// Constructors
	//--------------------------------------------------------------------------
	
	/**
	 * @param x The x-axis position of this menu's top left corner
	 * @param y The y-axis position of this menu's top left corner
	 */
	public Menu(int x, int y) {
		super(EmptyWidth, EmptyHeight);
		this.cornerX = x;
		this.cornerY = y;
	}
	
	//--------------------------------------------------------------------------
	// 
	//--------------------------------------------------------------------------
	
	/**
	 * Adds a button to this menu.  Buttons are displayed in the order they are added
	 * @param button A non-null button to add.  If the button has already been added, it is not re-added.
	 * @throws IllegalStateException If the given button is null
	 */
	public void addButton(MenuButton button) throws IllegalStateException {
		if(button == null) {
			throw new IllegalStateException("Button cannot be null");
		} else if(buttons.contains(button)) {
			return;
		}
		
		{	//TODO update Menu screen size based on button, take into account the button's hidden/visible status too?.  This needs to be refactored into draw to take into account buttons that change sizes due to scaling or other reasons
			//TODO menu should be able to shrink too, right now it can only grow
			Dimension screen = getSize();
			
			//update height
			int height = button.getSize().height + Spacing;
			
			//update width
			int width = button.getSize().width + Border + Border;
			if(width <= screen.width) {
				width = 0;
			} else {
				width = width - screen.width;
			}
			adjustSize(width, height);
		}
		
		buttons.add(button);
	}
	
	//--------------------------------------------------------------------------
	// Methods from Screen
	//--------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.Screen#update(boolean, boolean)
	 */
	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		//TODO if lost focus, unselect all buttons and grey display
		this.isHidden = !isVisible;
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.Screen#updateTransition(double, int)
	 */
	@Override
	public void updateTransition(double position, int direction) {
		// TODO Auto-generated method stub
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.Screen#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D gPen) {
		//TODO re-enable hiding after testing
		////if we aren't displayed, just stop
		//if(isHidden) {
		//	return;
		//}
		
		//draw the backgrond
		Dimension size = getSize();
		gPen.setColor(BackgroundColor);
		gPen.fillRect(cornerX, cornerY, size.width, size.height);
		
		//location of where to draw the buttons
		int x = cornerX + Border;
		int y = cornerY + Border;
		int scale = Scale;
		
		//draw each button
		for(MenuButton button: buttons) {
			//skip hidden buttons
			if(button.isHidden()) {
				continue;
			}
			
			button.draw(gPen, x, y, scale);
			y += button.getSize().height + Spacing;	//moving down for the next button
			//TODO what about the last button? should it use Spacing or Border?			
		}
		
		//TODO if we don't have focus, draw transparent, light gray myst over ourself
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.Screen#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//if the mouse isn't over us, ignore handle the event
		int x = e.getX();
		int y = e.getY();
		if(!includesPoint(x, y)) {
			return;
		}
		
		for(MenuButton button: getButtonsOver(x, y)) {
			button.pressed();
		}
		e.consume();
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.Screen#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		//if the mouse isn't over us, ignore handle the event
		int x = e.getX();
		int y = e.getY();
		if(!includesPoint(x, y)) {
			return;
		}
		
		//don't matter what button we've over, release all of them
		for(MenuButton button: buttons) {
			button.released(x, y);
		}
		e.consume();
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.Screen#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		//if the mouse isn't over us, ignore handle the event
		//TODO make sure buttons become unselected when mouse leaves the menu (mouse can jump, so can't rely on it hitting a border before leaving.  Instead, use mouseExit())
		int x = e.getX();
		int y = e.getY();
		if(!includesPoint(x, y)) {
			return;
		}
		
		//a button is selected when the mouse is over it
		for(MenuButton button: buttons) {
			button.setSelected(button.includesPoint(x,y));
		}
		
		e.consume();
	}
	
	//--------------------------------------------------------------------------
	// Helper methods
	//--------------------------------------------------------------------------
	
	/**
	 * Gets a collection of all buttons that include/overlap the both of the points
	 * @param x A point on the x-axis
	 * @param y A point on the y-axis
	 * @return A collection of MenuButtons.  Will may be empty, but will never be null
	 */
	private Collection<MenuButton> getButtonsOver(int x, int y) {
		Collection<MenuButton> overButtons = new ArrayList<MenuButton>();
		
		for(MenuButton button: buttons) {
			if(button.includesPoint(x, y)) {
				overButtons.add(button);
			}
		}
		
		assert(overButtons != null);
		return overButtons;
	}
	
	/**
	 * Checks to see if the given points are inside this Menu's drawing area
	 * @param x A point on the x-axis
	 * @param y A point on the y-axis
	 * @return true if the given points are inside this Menu
	 */
	private boolean includesPoint(int x, int y) {
		//TODO handle when width or height is negative :/
		Dimension size = getSize();
		return !(x < cornerX || x > (cornerX+size.width) ||
				y < cornerY || y > (cornerY+size.height));
	}
	
}
