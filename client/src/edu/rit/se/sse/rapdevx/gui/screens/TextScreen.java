package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.rit.se.sse.rapdevx.gui.ImageColorizer;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.Text;

public class TextScreen extends Screen {
	
	private Text title1;
	private Text title2;
	private Text title3;
	
	public TextScreen(int width, int height) {
		super(width, height);
		
		// Initialize things here
		
		title1 = new Text("Ba", 297, 100, 5);
		title2 = new Text("SSE", 285 + title1.getSizeOnScreen(), 93, 7);
		title3 = new Text("ic wars", 265 + title1.getSizeOnScreen() + title2.getSizeOnScreen(), 100, 5);
		
	}

	@Override
	public void draw(Graphics2D gPen) {
		// Draw things here
		gPen.setColor(Color.GREEN);
		BufferedImage image;
		try {
			image = ImageIO.read(new File("assets/background.png"));
			gPen.drawImage(image, null, 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage file;
		BufferedImage button1;
		BufferedImage button2;
		BufferedImage button3;
		BufferedImage button4;
		
		try {
			file = ImageIO.read(new File("assets/TitleButtonProto1.png"));
			button1 = file.getSubimage(0, 0, 600, 60);
			gPen.drawImage(button1, null, 215, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			file = ImageIO.read(new File("assets/TitleButtonProto1.png"));
			button2 = file.getSubimage(0, 0, 600, 60);
			gPen.drawImage(button2, null, 215, 350);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			file = ImageIO.read(new File("assets/TitleButtonProto1.png"));
			button3 = file.getSubimage(0, 0, 600, 60);
			gPen.drawImage(button3, null, 215, 450);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			file = ImageIO.read(new File("assets/TitleButtonProto1.png"));
			button4 = file.getSubimage(0, 0, 600, 60);
			gPen.drawImage(button4, null, 215, 550);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		title1.drawWhite(gPen);
		title2.drawColor(gPen, 0xffff00);
		title3.drawWhite(gPen);
		
	}

	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		// Move things here
			// hasFocus tells you if the screen accepts input currently (paused / unpaused)
			// isVisible tells you if any thing can be seen on screen
	}

	@Override
	public void updateTransition(double position, int direction) {
		// Called when we are transitioning from one screen to another
		// You can set up cool effects here.
	}
	
	
	
	

}
