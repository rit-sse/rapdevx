/**
 * 
 */
package edu.rit.se.sse.rapdevx.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


/**
 * We don't want to recreate stubs for all of Java's input event methods, so this interface will only include the ones we need
 * @author dpk3062
 */
public interface ScreenEvents {
	
	public abstract void keyPressed(KeyEvent e);
	
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void keyTyped(KeyEvent e);
	
	//Note: clicks will be considered pressing and releasing on the same control instead of pressing and releasing on the same pixel
	
	public abstract void mousePressed(MouseEvent e);
	
	public abstract void mouseReleased(MouseEvent e);
	
	public abstract void mouseMoved(MouseEvent e);
	
	public abstract void mouseClicked(MouseEvent e);
	
}
