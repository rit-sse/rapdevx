package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JFrame;

import javazoom.jl.player.Player;
import edu.rit.se.sse.rapdevx.gui.screens.MapScreen;
import edu.rit.se.sse.rapdevx.gui.screens.OverlayScreen;
import edu.rit.se.sse.rapdevx.gui.screens.PlacementScreen;

public class Window
{

	private int insetLeft;
	private int insetTop;
	private int insetRight;
	private int insetBottom;

	private JFrame window;

	private GraphicsDevice graphics;
	private GraphicsConfiguration graphicsConfig;
	private BufferStrategy bufferStrategy;

	public Window(String title, boolean fullscreen)
	{
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		graphics = env.getDefaultScreenDevice();
		graphicsConfig = graphics.getDefaultConfiguration();

		/**** Create the window ****/
		window = new JFrame(title, graphicsConfig);

		// Set some window properties
		if (fullscreen)
		{
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);
			window.setUndecorated(true);
		}
		else
		{
			window.setSize(1024, 768);
		}

		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setIgnoreRepaint(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		// Set the screen stack to handle input
		window.addKeyListener(ScreenStack.get());
		window.addMouseListener(ScreenStack.get());
		window.addMouseMotionListener(ScreenStack.get());

		// Adjust the coordinates to account for window decorations
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

		// Start with the move phase on the map
		MapScreen mapScreen = new MapScreen(windowWidth, windowHeight);
		ScreenStack.get().addScreen(mapScreen);

		// MoveScreen moveScreen = new MoveScreen(mapScreen.getCamera(),
		// windowWidth, windowHeight);
		// ScreenStack.get().addScreen(moveScreen);

		ScreenStack.get().addScreen(
				new PlacementScreen(mapScreen.getCamera(), windowWidth,
						windowHeight));

		// Add a control overlay to the window
		ScreenStack.get().addScreen(
				new OverlayScreen(windowWidth, windowHeight));

		window.requestFocusInWindow();

		playBackground();
	}

	private Player player;

	public void playBackground()
	{
		String filename = "assets/sounds/tardis.mp3";

		try
		{
			player = new Player(new BufferedInputStream(new FileInputStream(
					filename)));
		}
		catch (Exception e)
		{
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}

		// run in new thread to play in background
		Thread musicThread = new Thread()
		{
			String filename = "assets/sounds/tardis.mp3";

			public void run()
			{
				try
				{
					player.play();
					while (true)
					{
						if (player.isComplete())
						{
							player.close();
							player = new Player(new BufferedInputStream(
									new FileInputStream(filename)));
							player.play();
						}
					}

				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
		};
		musicThread.start();
	}

	public void update()
	{
		ScreenStack.get().update();
	}

	public void render()
	{
		// Create a graphics object from the buffer strategy
		// and draw the screens
		Graphics gPen = null;

		try
		{
			gPen = bufferStrategy.getDrawGraphics();
			if (!bufferStrategy.contentsLost())
			{
				gPen.translate(window.getInsets().left, window.getInsets().top);
				ScreenStack.get().draw((Graphics2D) gPen);
				bufferStrategy.show();
			}
		}
		finally
		{
			if (gPen != null)
			{
				gPen.dispose();
			}
		}
	}

}
