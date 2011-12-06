package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.images.TextButton;
import edu.rit.se.sse.rapdevx.gui.screens.menus.Menu;

public class OptionsScreen extends Screen implements ActionListener {

	private static final Color BLACK_TINT = new Color(0, 0, 0, 150);

	private Menu menu;

	private TextButton continueButton;
	private TextButton settingsButton;
	private TextButton helpButton;
	private TextButton exitButton;

	public OptionsScreen(int width, int height) {
		super(width, height);

		menu = new Menu(width / 2 - 100, height / 2 - 200);

		continueButton = new TextButton(width / 2 - 100 + 5,
				height / 2 - 200 + 10, 200, 50, "Continue", menu);
		settingsButton = new TextButton(width / 2 - 100 + 5, height / 2
				- 200 + 50 + 10, 200, 50, "Settings", menu);
		helpButton = new TextButton(width / 2 - 100 + 5, height / 2 - 200
				+ 100 + 10, 200, 50, "Help", menu);
		exitButton = new TextButton(width / 2 - 100 + 5, height / 2 - 200
				+ 150 + 10, 200, 50, "Settings", menu);

		continueButton.addActionListener(this);
		settingsButton.addActionListener(this);
		helpButton.addActionListener(this);
		exitButton.addActionListener(this);

		menu.addButton(continueButton);
		menu.addButton(settingsButton);
		menu.addButton(helpButton);
		menu.addButton(exitButton);
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
		} else if (e.getSource() == exitButton) {
			// TODO fix this
			System.exit(0);
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
