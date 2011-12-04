package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Text extends DrawableObject {

	private static final String FONT_FILE = "assets/FontPage.png";

	private static final double DEFAULT_SCALE = 2;

	private BufferedImage largeImage;

	private String text;
	private double scale;

	public Text(String text, int x, int y) {
		this(text, x, y, DEFAULT_SCALE, Color.LIGHT_GRAY);
	}
	
	public Text(String text, int x, int y, double textSize) {
		this(text, x, y, textSize, Color.LIGHT_GRAY);
	}
	
	public Text(String text, int x, int y, Color color) {
		this(text, x, y, DEFAULT_SCALE, color);
	}

	public Text(String text, int x, int y, double textSize, Color color) {
		super(x, y);

		this.text = text.toUpperCase();
		this.scale = textSize;
		
		try {
			largeImage = ImageIO.read(new File(FONT_FILE));
			ImageColorizer ic = new ImageColorizer(largeImage);
			ic.recolor(color.getRGB(), 0x1b39f5);
		} catch (IOException e) {
			System.err.println("Unable to load font file");
		}
	}

	public void draw(Graphics2D gPen) {
		char[] characters = text.toCharArray();
		int tempX = this.x;

		for (char character : characters) {

			int xIndex;
			BufferedImage smallImage = null;

			if (character >= 65 && character <= 90) {
				xIndex = (int) character - 65;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 0, 7, 7);

			} else if (character >= 97 && character <= 122) {
				xIndex = (int) character - 97;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 12, 7, 7);

			} else if (character >= 33 && character <= 58) {
				xIndex = (int) character - 33;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 24, 7, 7);

			} else if (character >= 59 && character <= 63) {
				xIndex = (int) character - 59;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);

			} else if (character >= 91 && character <= 96) {
				xIndex = (int) character - 91;
				xIndex *= 6;
				xIndex += 30;

				smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);

			} else if (character == 124) {
				xIndex = 66;

				smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);

			} else if (character == 32) {
				smallImage = largeImage.getSubimage(0, 48, 7, 7);
			} else {
				smallImage = largeImage.getSubimage(0, 48, 7, 7);
			}

			gPen.drawImage(smallImage, tempX, y,
					(int) (smallImage.getWidth() * this.scale),
					(int) (smallImage.getHeight() * this.scale), null);

			tempX += 6 * this.scale;
			
		}
	}

	public int getSizeOnScreen() {
		return (int) (text.length() * (7 * scale));
	}

	public void setColor(Color color) {
		try {
			largeImage = ImageIO.read(new File(FONT_FILE));
			ImageColorizer ic = new ImageColorizer(largeImage);
			ic.recolor(color.getRGB(), 0x1b39f5);
		} catch (IOException e) {
			System.err.println("Unable to load font file");
		}
	}

}
