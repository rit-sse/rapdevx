package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background implements Drawable {
	
	private int width, height;
	private BufferedImage background;
	
	public Background(int width, int height) {
		this.width = width;
		this.height = height;
		
		try {
			background = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public void update() {}

	public void draw(Graphics2D gPen) {
		gPen.drawImage(background, 0, 0, width, height, null);
	}
	
}
