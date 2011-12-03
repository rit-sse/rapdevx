package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
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
		drawGrid(gPen);
	}
	
	public void drawGrid(Graphics2D gPen){
		int x1 = 32;
		int y1 = 32;
		gPen.setColor(new Color(18,18,19));
		while(x1 < width){
			gPen.fill(new Rectangle(x1, 0, 4, height));
			x1+=64;
			swapColor(gPen);
		}
		while(y1 < height){
			gPen.fill(new Rectangle(0, y1, width, 4));
			y1+=64;
			swapColor(gPen);
		}
			
	}
	
	public void swapColor(Graphics2D gPen){
		Color color1 = new Color(18,18,19);
		Color color2 = new Color(26,24,29);
		if(gPen.getColor().equals(color1)){
			gPen.setColor(color2);
		}else{
			gPen.setColor(color1);
		}
	}
	
}
