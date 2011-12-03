package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import edu.rit.se.sse.rapdevx.gui.screens.ExampleScreen;
import edu.rit.se.sse.rapdevx.gui.screens.menus.Menu;
import edu.rit.se.sse.rapdevx.gui.screens.menus.MenuButton;

public class Window {
	
	private JFrame window;
	
	private GraphicsDevice graphics;
	private GraphicsConfiguration graphicsConfig;
	private BufferStrategy bufferStrategy;
	
	private ScreenStack screenStack;
	
	public Window(String title) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics = env.getDefaultScreenDevice();
		graphicsConfig = graphics.getDefaultConfiguration();
		
		/**** Create the window ****/
		window = new JFrame(title, graphicsConfig);
		
		// Set some window properties
		window.setSize(1024, 768);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setIgnoreRepaint(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		// Setup double buffering (makes graphics faster)
		window.createBufferStrategy(2);
		bufferStrategy = window.getBufferStrategy();
		
		
		/**** Create the panel to draw on ****/
		screenStack = new ScreenStack();
		screenStack.setSize(window.getWidth(), window.getHeight());
		screenStack.addScreen(new ExampleScreen(window.getWidth(), window.getHeight()));
		
		//TODO remove after testing
		Menu testMenu = new Menu(300, 300);
		MenuButton playButton = new MenuButton("Play Test", "This button does nothing yet");
		MenuButton settingsButton = new MenuButton("Settings Test", "This button does nothing yet");
		MenuButton helpButton = new MenuButton("Help Test", "This button does nothing yet");
		testMenu.addButton(playButton);
		testMenu.addButton(settingsButton);
		testMenu.addButton(helpButton);
		screenStack.addScreen(testMenu);
		
		// Add the panel to the window
		window.getContentPane().add(screenStack);
		window.addKeyListener(screenStack);
		window.addMouseListener(screenStack);
		window.addMouseMotionListener(screenStack);
		window.requestFocusInWindow();
	}
	
	public void update() {
		screenStack.update();
	}
	
	public void render() {
		// Create a graphics object from the buffer strategy
		// and draw the screens
		Graphics gPen = null;
		
		try {
			gPen = bufferStrategy.getDrawGraphics();
			if (!bufferStrategy.contentsLost()) {
				screenStack.draw((Graphics2D)gPen);
				bufferStrategy.show();
			}
		} finally {
			if (gPen != null) {
				gPen.dispose();
			}
		}
	}
}
