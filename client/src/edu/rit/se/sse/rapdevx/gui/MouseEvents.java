/**
 * 
 */
package edu.rit.se.sse.rapdevx.gui;

import java.awt.event.MouseEvent;


/**
 * We don't want to recreate stubs for all of Java's mouse event methods, so this interface will only include the ones we need
 * @author dpk3062
 */
public interface MouseEvents {
	
	//Note: clicks will be considered pressing and releasing on the same control instead of pressing and releasing on the same pixel
	
	public abstract void mousePressed(MouseEvent e);
	
	public abstract void mouseReleased(MouseEvent e);
	
	public abstract void mouseMoved(MouseEvent e);
	
}
