package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class ScreenStack implements KeyListener, MouseListener, MouseMotionListener {
	
	private static ScreenStack instance = new ScreenStack();
	
	private LinkedList<Screen> screenList;
	int xShift, yShift;
	
	private ScreenStack() {
		screenList = new LinkedList<Screen>();
	}
	
	public static ScreenStack get() {
		return instance;
	}
	
	public void setOffsets(int xShift, int yShift) {
		this.xShift = xShift;
		this.yShift = yShift;
	}
	
	/**
	 * Adds screen to the top of the list
	 * 
	 * @param screen the screen to add
	 */
	public synchronized void addScreen(Screen screen) {
		screenList.addLast(screen);
	}
	
	/**
	 * Adds a screen before another screen in the list.
	 * 
	 * @param referenceScreen the screen to add before
	 * @param newScreen the new screen to add
	 */
	public synchronized void addScreenBefore(Screen referenceScreen, Screen newScreen) {
		for (int i = 0; i < screenList.size(); i++) {
			if (screenList.get(i) == referenceScreen) {
				screenList.add(i, newScreen);
				break;
			}
		}
		
		//TODO throw exception?
	}
	
	/**
	 * Adds a screen after another screen in the list.
	 * 
	 * @param referenceScreen the screen to add before
	 * @param newScreen the new screen to add
	 */
	public synchronized void addScreenAfter(Screen referenceScreen, Screen newScreen) {
		for (int i = 0; i < screenList.size(); i++) {
			if (screenList.get(i) == referenceScreen) {
				//TODO may need to do special casing for end of list?
				screenList.add(i + 1, newScreen);
				break;
			}
		}
		
		//TODO throw exception?
	}
	
	/**
	 * Remove a screen from the list.
	 * 
	 * @param screen the screen to remove
	 */
	public synchronized void removeScreen(Screen screen) {
		screenList.remove(screen);
	}
	
	/**
	 * Updates the screens in the list.  Determines which screens should
	 * have focus and are visible.
	 */
	public synchronized void update() {
		boolean otherScreenHasFocus = false;
		boolean coveredByOtherScreen = false;
		
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			
			screen.update(otherScreenHasFocus, !coveredByOtherScreen);
			
			if ((screen.getState() == Screen.State.ACTIVE) ||
					(screen.getState() == Screen.State.TRANSITION_ON))
			{
				//TODO: combine if statements if nothing else is added here
				if (!otherScreenHasFocus) {
					otherScreenHasFocus = true;
					
					if (screen.isPopup()) {
						coveredByOtherScreen = true;
					}
				}
			}
		}
	}
	
	/**
	 * Draws all of the screens in the list from bottom to top.
	 * 
	 * @param gPen the graphics pen to draw with 
	 */
	public synchronized void draw(Graphics2D gPen) {
		for (Screen screen : screenList) {
			screen.draw(gPen);
		}
	}
	
	/**** User Input ****/
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			if(e.isConsumed()) {
				return;	//we're done if someone handled the event
			}
			screen.keyPressed(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public synchronized void keyReleased(KeyEvent e) {
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			if(e.isConsumed()) {
				return;	//we're done if someone handled the event
			}
			screen.keyReleased(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public synchronized void keyTyped(KeyEvent e) {
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			if(e.isConsumed()) {
				return;	//we're done if someone handled the event
			}
			screen.keyTyped(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public synchronized void mousePressed(MouseEvent e) {
		adjustMouseCoords(e);
		
		//tell all the screens about this mouse event
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			if(e.isConsumed()) {
				return;	//we're done if someone handled the event
			}
			screen.mousePressed(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public synchronized void mouseReleased(MouseEvent e) {
		adjustMouseCoords(e);
		
		//tell all the screens about this mouse event
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			if(e.isConsumed()) {
				return;	//we're done if someone handled the event
			}
			screen.mouseReleased(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public synchronized void mouseMoved(MouseEvent e) {
		//TODO should we only send this to the screen with focus? (need to make the same choice for all the other events too)
		
		adjustMouseCoords(e);
		
		//tell all the screens about this mouse event
		for (int i = screenList.size() - 1; i >= 0; i--) {
			Screen screen = screenList.get(i);
			if(e.isConsumed()) {
				return;	//we're done if someone handled the event
			}
			screen.mouseMoved(e);
		}
	}
	
	private void adjustMouseCoords(MouseEvent e) {
		e.translatePoint(-xShift, -yShift);
	}
	
}
