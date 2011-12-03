package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background implements Drawable {
	
	private int x, y, width, height;
	private BufferedImage background;
	
	public Background(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		try {
			background = ImageIO.read(new File("assets/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public void update() {}

	public void draw(Graphics2D gPen) {
		gPen.drawImage(background, x, y, width, height, null);
	}
	
}
