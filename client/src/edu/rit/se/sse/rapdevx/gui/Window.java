package edu.rit.se.sse.rapdevx.gui;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Window {

	private JFrame window;
	
	private GraphicsDevice graphics;
	private GraphicsConfiguration graphicsConfig;
	
	public Window(String title) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics = env.getDefaultScreenDevice();
		graphicsConfig = graphics.getDefaultConfiguration();
		window = new JFrame(title);
		
		// Create the GUI
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
}
