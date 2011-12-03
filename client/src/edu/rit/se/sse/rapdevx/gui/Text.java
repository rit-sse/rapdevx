package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Text extends DrawableObject {

	private static final String FONT_FILE = "assets/FontPage.png";
	private static final String FONT_FILE_WHITE = "assets/FontPageWhite.png";

	private static final int DEFAULT_SCALE = 2;

	private BufferedImage largeImage;

	private String text;
	private int scale;

	public Text(String text, int x, int y) {
		this(text, x, y, DEFAULT_SCALE);
	}

	public Text(String text, int x, int y, int textSize) {
		super(x, y);

		this.text = text.toUpperCase();
		this.scale = textSize;

		// TODO make font image static and load only once for all text
		try {
			largeImage = ImageIO.read(new File(FONT_FILE));
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

				smallImage = largeImage.getSubimage(0, 100, 7, 7);

			} else {

				smallImage = largeImage.getSubimage(0, 48, 7, 7);

			}

			gPen.drawImage(smallImage, tempX, y, smallImage.getWidth()
					* this.scale, smallImage.getHeight() * this.scale, null);

			tempX += 6 * this.scale;

		}

	}

	public void drawColor(Graphics2D gPen, int toReplace) {

		int replaceWith = 0x000000;
		char[] toConvertArray = text.toCharArray();
		int tempX = this.x;

		for (char character : toConvertArray) {

			int xIndex;
			BufferedImage smallImage = null;
			ImageColorizer colorize = null;

			if (character >= 65 && character <= 90) {

				xIndex = (int) character - 65;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 0, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);

			} else if (character >= 97 && character <= 122) {

				xIndex = (int) character - 97;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 12, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);

			} else if (character >= 33 && character <= 58) {

				xIndex = (int) character - 33;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 24, 7, 7);

			} else if (character >= 59 && character <= 63) {

				xIndex = (int) character - 59;
				xIndex *= 6;

				smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);

			} else if (character >= 91 && character <= 96) {

				xIndex = (int) character - 91;
				xIndex *= 6;
				xIndex += 30;

				smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);

			} else if (character == 124) {

				xIndex = 66;

				smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);

			} else if (character == 32) {

				smallImage = largeImage.getSubimage(0, 100, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);

			} else {

				smallImage = largeImage.getSubimage(0, 48, 7, 7);
				colorize = new ImageColorizer(smallImage);
				colorize.recolorStrong(replaceWith, toReplace);
			}

			gPen.drawImage(colorize.copyAndRecolor(replaceWith, toReplace),
					tempX, y, smallImage.getWidth() * this.scale,
					smallImage.getHeight() * this.scale, null);

			tempX += 6 * this.scale;

		}

	}

	public int getSizeOnScreen() {
		return text.length() * (7 * scale);
	}

}
