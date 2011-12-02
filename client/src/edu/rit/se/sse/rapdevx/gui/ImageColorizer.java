package edu.rit.se.sse.rapdevx.gui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageColorizer {
	protected BufferedImage	image;

	public ImageColorizer(BufferedImage bi) {
		image = bi;
	}

	public void color(int color, int colorReplaced) {
		ArrayList<Point> marked = this.findRecolorMarks(colorReplaced);
		for (int i = 0; i < marked.size(); i++) {
			image.setRGB((int) marked.get(i).getX(),
					(int) marked.get(i).getY(), color);
		}
		image.flush();
	}

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

	public BufferedImage getImage() {
		return this.image;
	}
}
