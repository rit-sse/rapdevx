package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import edu.rit.se.sse.rapdevx.gui.screens.*;
import edu.rit.se.sse.rapdevx.gui.screens.menus.Menu;
import edu.rit.se.sse.rapdevx.gui.screens.menus.MenuButton;

public class Window {

	private int insetLeft;
	private int insetTop;
	private int insetRight;
	private int insetBottom;

	private JFrame window;

	private GraphicsDevice graphics;
	private GraphicsConfiguration graphicsConfig;
	private BufferStrategy bufferStrategy;

	public Window(String title, boolean fullscreen) {
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		graphics = env.getDefaultScreenDevice();
		graphicsConfig = graphics.getDefaultConfiguration();

		/**** Create the window ****/
		window = new JFrame(title, graphicsConfig);

		// Set some window properties
		if (fullscreen) {
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);
			window.setUndecorated(true);
		} else {
			window.setSize(1024, 768);
		}
		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setIgnoreRepaint(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		insetLeft = window.getInsets().left;
		insetTop = window.getInsets().top;
		insetRight = window.getInsets().right;
		insetBottom = window.getInsets().bottom;

		int windowWidth = window.getWidth() - insetLeft - insetRight;
		int windowHeight = window.getHeight() - insetTop - insetBottom;
		
		ScreenStack.get().setOffsets(insetLeft, insetTop);

		// Setup double buffering (makes graphics faster)
		window.createBufferStrategy(2);
		bufferStrategy = window.getBufferStrategy();

		/**** Create the panel to draw on ****/

		// Start with the move phase on the map
		MapScreen mapScreen = new MapScreen(windowWidth, windowHeight);
		ScreenStack.get().addScreen(mapScreen);
		
		MoveScreen moveScreen = new MoveScreen(mapScreen.getCamera(),
				windowWidth, windowHeight);
		ScreenStack.get().addScreen(moveScreen);

		// Add a control overlay to the window
		ScreenStack.get().addScreen(new OverlayScreen(windowWidth, windowHeight));

		// TODO remove after testing
		Menu testMenu = new Menu(300, 300);
		MenuButton playButton = new MenuButton("Play",
				"This button does nothing yet");
		MenuButton settingsButton = new MenuButton("Settings",
				"This button does nothing yet");
		MenuButton helpButton = new MenuButton("Help",
				"This button does nothing yet");
		testMenu.addButton(playButton);
		testMenu.addButton(settingsButton);
		testMenu.addButton(helpButton);
		//screenStack.addScreen(testMenu);;

		// Add the panel to the window
		// window.getContentPane().add(screenStack);
		window.addKeyListener(ScreenStack.get());
		window.addMouseListener(ScreenStack.get());
		window.addMouseMotionListener(ScreenStack.get());
		window.requestFocusInWindow();
	}

	public void update() {
		ScreenStack.get().update();
	}

	public void render() {
		// Create a graphics object from the buffer strategy
		// and draw the screens
		Graphics gPen = null;

		try {
			gPen = bufferStrategy.getDrawGraphics();
			if (!bufferStrategy.contentsLost())
			{
				gPen.translate(window.getInsets().left, window.getInsets().top);
				ScreenStack.get().draw((Graphics2D) gPen);
				bufferStrategy.show();
			}
		} finally {
			if (gPen != null) {
				gPen.dispose();
			}
		}
	}
	
}
