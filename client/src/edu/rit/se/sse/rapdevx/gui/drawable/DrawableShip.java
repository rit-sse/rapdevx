package edu.rit.se.sse.rapdevx.gui.drawable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.gui.Art;
import edu.rit.se.sse.rapdevx.gui.ImageColorizer;

public class DrawableShip extends DrawableObject {
	
	private static final String SHIP_IMAGE = "assets/ship.png";
	private static final String SHIP_CLEAR_IMAGE = "assets/clear_ship.png";
	
	private Ship ship;
	private boolean isSelected;
	private Color teamColor;
	
	private BufferedImage shipImage;
	private BufferedImage shipClearImage;
	
	private DrawablePath path;
	
	public DrawableShip(Ship ship, Color teamColor) {
		super(ship.getX() * 2, ship.getY() * 2, 64, 64);
		this.isSelected = false;
		this.teamColor = teamColor;
		this.ship = ship;
		
		// Load the ship image
		try {
			shipImage = ImageIO.read(new File(SHIP_IMAGE));
		} catch (IOException e) {
			System.err.println("Unable to load ship image");
		}
		try {
			shipClearImage = ImageIO.read(new File(SHIP_CLEAR_IMAGE));
		} catch (IOException e) {
			System.err.println("Unable to load ship clear image");
		}
		
		// Recolor the ship with the team color
		ImageColorizer ic = new ImageColorizer(shipImage);
		ic.recolorStrong(teamColor.getRGB(), Art.MAGIC_PINK);
		
		ic = new ImageColorizer(shipClearImage);
		ic.recolorStrong(teamColor.getRGB(), Art.MAGIC_PINK);
	}
	
//	public void setColor( Color newColor ) {
//		teamColor = newColor;
//	}
	
	public Color getColor() {
		return teamColor;
	}

	public void update() {
		this.x += xVel;
		this.y += yVel;
	}
	
	public void draw(Graphics2D gPen, Rectangle2D bounds) {
		if (isSelected) {
			gPen.setColor(teamColor);
			gPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			gPen.setStroke(new BasicStroke(2.0f));
			gPen.draw(getBounds());
			gPen.setStroke(new BasicStroke());
			
			if ( path != null ) {
				path.draw( gPen );
			}
		}
		gPen.drawImage(shipImage, x, y, 64, 64, null);
	}
	
	public void drawClear(Graphics2D gPen, Rectangle2D bounds, Point center) {
		/*
		if (isSelected) {
			gPen.setColor(teamColor);
			gPen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			gPen.setStroke(new BasicStroke(2.0f));
			gPen.draw(getBounds());
			gPen.setStroke(new BasicStroke());
		}
		*/
		int leftX = (int)center.getX() - (64/2);
		int topY = (int)center.getY() - (64/2);
		gPen.drawImage(shipClearImage, leftX, topY, 64, 64, null);
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public Ellipse2D getBounds() {
		// TODO get ship radius (2 * scale)
		return new Ellipse2D.Double(x - 5, y + 3, 68, 68);
	}

	public Ship getShip() {
		return ship;
	}
	
	public void setPath( DrawablePath thisPath ) {
		path = thisPath;
	}

}
