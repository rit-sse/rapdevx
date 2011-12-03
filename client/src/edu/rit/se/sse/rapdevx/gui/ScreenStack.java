package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ScreenStack extends JPanel implements KeyListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Screen> screenList;
	
	public ScreenStack() {
		screenList = new ArrayList<Screen>();
	}
	
	public void addScreen(Screen screen) {
		screenList.add(screen);
	}
	
	public void removeScreen(Screen screen) {
		screenList.remove(screen);
	}
	
	public void update() {
		boolean otherScreenHasFocus = false;
		boolean coveredByOtherScreen = false;
		
		for (Screen screen : screenList) {
			screen.update(otherScreenHasFocus, coveredByOtherScreen);
			
			if ((screen.getState() == Screen.State.ACTIVE) ||
				(screen.getState() == Screen.State.TRANSITION_ON))
			{
				if (!otherScreenHasFocus) {
					otherScreenHasFocus = true;
					
					if (screen.isPopup())
						coveredByOtherScreen = true;
				}
			}
		}
	}
	
	public void draw(Graphics2D gPen) {
		for (Screen screen : screenList) {
			screen.draw(gPen);
		}
	}
	
	/**** User Input ****/

	public void keyPressed(KeyEvent e) {
		for (Screen screen : screenList) {
			screen.keyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		for (Screen screen : screenList) {
			screen.keyReleased(e);
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
