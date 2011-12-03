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

public class ImageColorizer extends BufferedImage{

	/**
	 * Creates a new ImageColorizer given a BufferedImage
	 * @param bi The BufferedImage to be operated upon
	 */
	public ImageColorizer(BufferedImage bi) {
		super(bi.getColorModel(),bi.getRaster(),bi.isAlphaPremultiplied(),null);
		
	}

	/**
	 * Recolors the image.  Hex values for this are the standard RGB codes with another
	 * byte at the beginning for the alpha values.  If you are using the color off a standard
	 * RGB picker, the alpha byte should be FF.  If you want something to be completely
	 * transparent, the alpha byte should be 00.  If you don't want to mess with the alpha
	 * byte, try using the recolorStrong method instead.
	 * 
	 * @param color The color (in hex) that the image will be repainted with
	 * @param colorReplaced The color (in hex) that will be replaced
	 */
	public void recolor(int color, int colorReplaced) {
		ArrayList<Point> marked = this.findRecolorMarks(colorReplaced);
		for (int i = 0; i < marked.size(); i++) {
			this.setRGB((int) marked.get(i).getX(),
					(int) marked.get(i).getY(), color);
		}
		this.flush();
	}

	/**
	 * Recolors the image and replaces the top byte of the color parameter with FF.
	 * Otherwise, this is the same as the recolor method
	 * @param color The color (in hex) that the image will be repainted with
	 * @param colorReplaced The color (in hex) that will be replaced
	 */
	public void recolorStrong(int color, int colorReplaced){
		recolor((0xFF000000|color), colorReplaced);
	}
	
	/**
	 * Finds all the points that match the given color (in hex) and returns them
	 * in an ArrayList
	 * @param markColor The color to be found (with the top byte disregarded)
	 * @return An ArrayList of Points that match the passed in color
	 */
	
	public BufferedImage copyAndRecolor(int color, int colorReplaced){
		BufferedImage result = new BufferedImage(this.getWidth(), this.getHeight(), this.getType());
		result.setData(this.getRaster());
		long rgbValue;
		for (int i = 0; i < result.getWidth(); i++) {
			for (int j = 0; j < result.getHeight(); j++) {
				// System.out.println(Integer.toHexString(0xFFFFFF &
				// image.getRGB(i, j)));
				rgbValue = result.getRGB(i, j);
				rgbValue &= 0x00FFFFFF;
				if (rgbValue == colorReplaced) {
					result.setRGB(i, j, color);
					//System.out.println("Found some magenta");
				}
			}
		}
		result.flush();
		return result;
	}
	public ArrayList<Point> findRecolorMarks(long markColor) {
		ArrayList<Point> markedPoints = new ArrayList<Point>();
		long rgbValue;
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				// System.out.println(Integer.toHexString(0xFFFFFF &
				// image.getRGB(i, j)));
				rgbValue = this.getRGB(i, j);
				rgbValue &= 0x00FFFFFF;
				if (rgbValue == markColor) {
					markedPoints.add(new Point(i, j));
					// System.out.println("Found some magenta");
				}
			}
		}
		return markedPoints;
	}

}
