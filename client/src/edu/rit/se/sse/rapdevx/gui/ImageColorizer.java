package edu.rit.se.sse.rapdevx.gui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class is a wrapper for a BufferedImage that is used to
 * repaint a color on the image
 * @author Stephen Yingling
 *
 */

public class ImageColorizer {
	protected BufferedImage	image;

	/**
	 * Creates a new ImageColorizer given a BufferedImage
	 * @param bi The BufferedImage to be operated upon
	 */
	public ImageColorizer(BufferedImage bi) {
		image = bi;
	}

	/**
	 * Recolors the image.  Hex values for this are the standard RGB codes with an
	 * extra byte at the top.  Unless you know what the topmost byte is used for,
	 * it is suggested to either prepend the hex code with FF or use the colorStrong
	 * method instead.
	 * 
	 * @param color The color (in hex) that the image will be repainted with
	 * @param colorReplaced The color (in hex) that will be replaced
	 */
	public void color(int color, int colorReplaced) {
		ArrayList<Point> marked = this.findRecolorMarks(colorReplaced);
		for (int i = 0; i < marked.size(); i++) {
			image.setRGB((int) marked.get(i).getX(),
					(int) marked.get(i).getY(), color);
		}
		image.flush();
	}

	/**
	 * Recolors the image and replaces the top byte of the color parameter with FF.
	 * Otherwise, this is the same as the color method
	 * @param color The color (in hex) that the image will be repainted with
	 * @param colorReplaced The color (in hex) that will be replaced
	 */
	public void colorStrong(int color, int colorReplaced){
		color((0xFF000000|color), colorReplaced);
	}
	
	/**
	 * Finds all the points that match the given color (in hex) and returns them
	 * in an ArrayList
	 * @param markColor The color to be found (with the top byte disregarded)
	 * @return An ArrayList of Points that match the passed in color
	 */
	
	public ArrayList<Point> findRecolorMarks(long markColor) {
		ArrayList<Point> markedPoints = new ArrayList<Point>();
		long rgbValue;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				// System.out.println(Integer.toHexString(0xFFFFFF &
				// image.getRGB(i, j)));
				rgbValue = image.getRGB(i, j);
				rgbValue &= 0x00FFFFFF;
				if (rgbValue == markColor) {
					markedPoints.add(new Point(i, j));
					// System.out.println("Found some magenta");
				}
			}
		}
		return markedPoints;
	}

	/**
	 * 
	 * @return The image that the object is operating on
	 */
	
	public BufferedImage getImage() {
		return this.image;
	}
}
