package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Text implements Drawable{
	
	private final File fontFile = new File("assets/FontPage");
	private String toConvert;
	private int xCord;
	private int yCord;
	
	public Text(String toConvert, int xCord, int yCord) {
		
		this.toConvert = toConvert;
		this.xCord = xCord;
		this.yCord = yCord;
		
	}

	public void update() {
		
		
		
	}

	public void draw(Graphics2D gPen) {
		
char[] derp = toConvert.toCharArray();
		
		for ( char character : derp ) {
			
			int xIndex;
			BufferedImage largeImage;
			BufferedImage smallImage = null;
			
			if (character >= 65 && character <= 90) {
				
				xIndex = (int) character - 65;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 0, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			} else if (character >= 97 && character <= 122) {
				
				xIndex = (int) character - 97;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 12, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 33 && character <= 58) {
				
				xIndex = (int) character - 33;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 24, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 59 && character <= 63) {
				
				xIndex = (int) character - 59;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 91&& character <= 96) {
				
				xIndex = (int) character - 91;
				xIndex *= 6;
				xIndex += 30;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character == 124) {
				
				xIndex = 66;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 48, 6, 6);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			gPen.drawImage(smallImage, xCord, yCord, smallImage.getWidth()*4, 
					smallImage.getHeight()*4, null);
			
		}
		
	}

}
