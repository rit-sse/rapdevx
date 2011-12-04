package edu.rit.se.sse.rapdevx.gui.drawable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.rit.se.sse.rapdevx.gui.ImageColorizer;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableObject;

public class Text extends DrawableObject {

	private static final String FONT_FILE = "assets/FontPage.png";
	private static final int FONT_REPLACE_COLOR = 0x1b39f5;
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
			ic.recolor(color.getRGB(), FONT_REPLACE_COLOR);
		} catch (IOException e) {
			System.err.println("Unable to load font file");
		}
	}

	public void draw(Graphics2D gPen) {
		char[] characters = text.toCharArray();
		int tempX = this.x;

		for (char character : characters) {

			int xIndex = 0;
			int yIndex = 0;
			BufferedImage smallImage = null;

			if (character >= 65 && character <= 90) {
				xIndex = ((int) character - 65) * 6;
				yIndex = 0;
			} else if (character >= 97 && character <= 122) {
				xIndex = ((int) character - 97) * 6;
				yIndex = 7;
			} else if (character >= 33 && character <= 58) {
				xIndex = ((int) character - 33) * 6;
				yIndex = 14;
			} else if (character >= 59 && character <= 63) {
				xIndex = ((int) character - 59) * 6;
				yIndex = 21;
			} else if (character >= 91 && character <= 96) {
				xIndex = ((int) character - 91) * 6 + 30;
				yIndex = 21;
			} else if (character == 124) {
				xIndex = 66;
				yIndex = 21;
			} else if (character == 32) {
				xIndex = 84;
				yIndex = 21;
			} else {
				xIndex = 78;
				yIndex = 21;
			}
			
			smallImage = largeImage.getSubimage(xIndex, yIndex, 6, 7);

			gPen.drawImage(smallImage, tempX, y,
					(int) (smallImage.getWidth() * scale),
					(int) (smallImage.getHeight() * scale), null);

			tempX += 6 * scale;
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
