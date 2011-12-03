package edu.rit.se.sse.rapdevx.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Screen {
	
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
	public abstract void updateTransition(double position, int direction);
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
	
	public boolean mouseClicked(MouseEvent e) {
		return true;
	}
	
	public boolean mouseEntered(MouseEvent e) {
		return true;
	}

	public boolean mouseExited(MouseEvent e) {
		return true;
	}

	public boolean mousePressed(MouseEvent e) {
		return true;
	}

	public boolean mouseReleased(MouseEvent e) {
		return true;
	}
}
