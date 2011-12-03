package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite extends DrawableObject {

	private BufferedImage image;
	
	public Sprite(String filename, int x, int y) {
		super(x, y);
		
		try {
			ImageIO.read(new File("ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D gPen) {
		gPen.drawImage(image, x, y, 64, 64, null);
	}
}
