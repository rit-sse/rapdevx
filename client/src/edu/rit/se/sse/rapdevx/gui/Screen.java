package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Screen implements KeyListener, MouseListener {
	
	protected int screenWidth, screenHeight;
	
	public Screen(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
	}
	
	public abstract void update(boolean hasFocus, boolean isVisible);
	
	public abstract void updateTransition(double position, int direction);
	
	public abstract void draw(Graphics2D gPen);

	
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
