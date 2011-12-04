package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage image;
	
	public Sprite(String filename) {
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getImageWidth() { 
		return image.getWidth();
	}
	
	public int getImageHeight() { 
		return image.getHeight();
	}

	public void draw(Graphics2D gPen, int x, int y, int scale) {
		gPen.drawImage(image, x, y, 64, 64, null);
	}
	
	public void draw(Graphics2D gPen, int x, int y) {
		gPen.drawImage(image, x, y, image.getWidth(), 
				image.getHeight(), null);
	}
}
