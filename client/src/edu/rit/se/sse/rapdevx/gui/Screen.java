package edu.rit.se.sse.rapdevx.gui;

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

	public boolean keyPressed(KeyEvent e) {
		return true;
	}

	public boolean keyReleased(KeyEvent e) {
		return true;
	}

	public boolean keyTyped(KeyEvent e) {
		return true;
	}

}
