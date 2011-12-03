package edu.rit.se.sse.rapdevx.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Screen implements KeyListener, MouseListener {
	
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
	
	public void mouseClicked(MouseEvent arg0) {
		
	}
	
	public void mouseEntered(MouseEvent arg0) {		
	}
	
	public void mouseExited(MouseEvent arg0) {
		
	}
	
	public void mousePressed(MouseEvent arg0) {
		
	}
	
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public void keyPressed(KeyEvent arg0) {
		
	}
	
	public void keyReleased(KeyEvent arg0) {
		
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
