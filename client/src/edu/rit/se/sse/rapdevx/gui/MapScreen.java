package edu.rit.se.sse.rapdevx.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MapScreen extends Screen {
	
	private ArrayList<DrawableShip> shipList;

	public MapScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(boolean hasFocus, boolean isVisible) {
		// TODO Auto-generated method stub
		for (DrawableShip ship : shipList) {
			ship.update();
		}
	}

	@Override
	public void updateTransition(double position, int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D gPen) {
		// Draw a black background
		gPen.setColor(Color.BLACK);
		gPen.fillRect(0, 0, screenWidth, screenHeight);
		
		// TODO Auto-generated method stub
		for (DrawableShip ship : shipList) {
			ship.draw(gPen);
		}
	}

}
