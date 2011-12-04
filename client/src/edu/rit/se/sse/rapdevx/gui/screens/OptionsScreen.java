package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.screens.menus.Menu;
import edu.rit.se.sse.rapdevx.gui.screens.menus.MenuButton;

public class OptionsScreen extends Screen implements ActionListener {
	
	private static final Color BLACK_TINT = new Color(0, 0, 0, 150);
	
	private Menu menu;
	
	private MenuButton continueButton;
	private MenuButton settingsButton;
	private MenuButton helpButton;
	
	public OptionsScreen(int width, int height) {
		super(width, height);
		
		menu = new Menu(400, 200);
		
		continueButton = new MenuButton("Continue", "Go back to the game!");
		settingsButton = new MenuButton("Settings", "Adjust game settings");
		helpButton = new MenuButton("Help", "Click this if you are lost!");
		
		continueButton.addActionListener(this);
		settingsButton.addActionListener(this);
		helpButton.addActionListener(this);
		
		menu.addButton(continueButton);
		menu.addButton(settingsButton);
		menu.addButton(helpButton);
	}
	
	public void init() {
		ScreenStack.get().addScreen(menu);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == continueButton) {
			// Close this menu
			ScreenStack.get().removeScreen(menu);
			ScreenStack.get().removeScreen(this);
		} else if (e.getSource() == settingsButton) {
			System.out.println("Settings");
		} else if (e.getSource() == helpButton) {
			System.out.println("Help");
		}
	}
	
	public void update(boolean hasFocus, boolean isVisible) {
		
	}
	
	public void draw(Graphics2D gPen) {
		gPen.setColor(BLACK_TINT);
		gPen.fillRect(0, 0, screenWidth, screenHeight);
	}
	
	public void keyPressed(KeyEvent e) {
		e.consume();
	}
	
	public void keyReleased(KeyEvent e) {
		e.consume();
	}
	
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ScreenStack.get().removeScreen(menu);
			ScreenStack.get().removeScreen(this);
		}
		
		e.consume();
	}
	
	public void mouseMoved(MouseEvent e) {
		e.consume();
	}
	
	public void mouseReleased(MouseEvent e) {
		e.consume();
	}
	
}
