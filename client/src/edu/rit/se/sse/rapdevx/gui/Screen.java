package edu.rit.se.sse.rapdevx.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Screen implements ScreenEvents {
	
	enum State {
		ACTIVE,
		TRANSITION_ON,
		TRANSITION_OFF,
		HIDDEN
	}
	
	protected State currentState;
	protected int screenWidth, screenHeight;
	protected boolean isPopup;
	
	protected int transitionTimeOn;
	protected int transitionTimeOff;
	protected double transitionPosition;
	
	public Screen(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
	}
	
	/**
	 * Adjusts this screen's dimensions
	 * @param width Increaes this screen's width by this amount (negative numbers will decrease the width)
	 * @param height Increases this screen's height by this amount (negative numbers will decrease the height)
	 */
	protected void adjustSize(int width, int height) {
		screenWidth += width;
		screenHeight += height;
	}
	
	/**
	 * @return The dimensions of this screen
	 */
	protected Dimension getSize() {
		return new Dimension(screenWidth, screenHeight);
	}
	
	public abstract void update(boolean hasFocus, boolean isVisible);
	
	public void updateTransition(double position, int direction) {
		
	}
	
	public abstract void draw(Graphics2D gPen); 
	
	public void exit() {
		///TODO
	}
	
	public boolean isPopup() {
		return isPopup;
	}
	
	public State getState() {
		return currentState;
	}
	
	/**** User Input ****/
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.ScreenEvents#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.ScreenEvents#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.ScreenEvents#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.ScreenEvents#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.ScreenEvents#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.se.sse.rapdevx.gui.ScreenEvents#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
