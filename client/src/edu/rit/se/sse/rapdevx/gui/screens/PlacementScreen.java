package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.clientstate.GameSession;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Camera;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;
import edu.rit.se.sse.rapdevx.gui.images.ArrowButton;
import edu.rit.se.sse.rapdevx.gui.images.RectangleBackground;
import edu.rit.se.sse.rapdevx.gui.images.ShipSelectSquare;

public class PlacementScreen extends Screen implements StateListener {

	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;
	private OverlayScreen overlay;

	private Rectangle placementArea;
	private int shownIndex;

	private ArrowButton upButton;
	private ArrowButton downButton;

	private ArrayList<ShipSelectSquare> shipSelectSquares;
	private DrawableShip selectedShip;
	private boolean drawShip;

	private RectangleBackground background;

	public PlacementScreen(Camera camera, int width, int height) {
		super(width, height);
		this.camera = camera;
		this.overlay = new OverlayScreen(width, height);

		background = new RectangleBackground(96, 128, 88, 483, false);
		shownIndex = 0;
		drawShip = false;

		placementArea = new Rectangle(88, 472);
		placementArea.x = 96;
		placementArea.y = 128;

		upButton = new ArrowButton(placementArea.x + 8, placementArea.y + 9,
				72, 32, ArrowButton.Direction.UP);
		upButton.setEnabled(false);

		downButton = new ArrowButton(placementArea.x + 8,
				placementArea.y + 441, 72, 32, ArrowButton.Direction.DOWN);

		shipSelectSquares = new ArrayList<ShipSelectSquare>();
		// TODO actually load the ships
		// Max 5 ships displayed at any time...
		for (int i = 0; i < 6; i++) {
			int x = 8;
			int y = (32 + 8 * (i + 1)) + (72 * (i)) + 5;
			ShipSelectSquare square = new ShipSelectSquare(x + placementArea.x,
					y + placementArea.y);

			shipSelectSquares.add(square);
		}

		GameSession.get().addStateListener(this);
	}

	public void init() {
		ScreenStack.get().addScreenAfter(this, overlay);
	}

	public void draw(Graphics2D gPen) {
		if (drawShip && selectedShip != null)
			selectedShip.draw(gPen);
		
		background.draw(gPen);
		upButton.draw(gPen);
		downButton.draw(gPen);

		for (int i = shownIndex; i < shownIndex + 5; i++) {
			if (i >= shipSelectSquares.size())
				break;

			shipSelectSquares.get(i).draw(gPen);
		}
	}

	public void update(boolean hasFocus, boolean isVisible) {}

	public void mouseReleased(MouseEvent e) {
		// Clear passed pressed buttons
		for (ShipSelectSquare square : shipSelectSquares) {
			square.setPressed(false);
		}
		upButton.setPressed(false);
		downButton.setPressed(false);
		
		if (placementArea.contains(e.getPoint())) {
			if (downButton.containsPoint(e.getPoint())
					&& shownIndex < shipSelectSquares.size() - 5)
			{
				shownIndex += 1;
				for (ShipSelectSquare square : shipSelectSquares) {
					square.moveUp();
				}
			} else if (upButton.containsPoint(e.getPoint()) && shownIndex > 0) {
				shownIndex -= 1;
				for (ShipSelectSquare square : shipSelectSquares) {
					square.moveDown();
				}
			} else {
				for (ShipSelectSquare square : shipSelectSquares) {
					if (square.containsPoint(e.getPoint())) {
						//TODO team color selection
						selectedShip = new DrawableShip((Ship)square.getShip().clone(), Color.RED);
						square.setPressed(true);
						
						break;
					}
				}
	
				if (selectedShip != null) {
					// TODO place a ship
				}
			}
			
			upButton.setEnabled(shownIndex >= shipSelectSquares.size() - 5);
			downButton.setEnabled(shownIndex == 0);

			e.consume();
		}
	}

	public void mouseMoved(MouseEvent e) {
		boolean inToolbar = placementArea.contains(e.getPoint());
		
		for (ShipSelectSquare square : shipSelectSquares) {
			square.setHovering(inToolbar && square.containsPoint(e.getPoint()));
		}

		upButton.setHovering(inToolbar && upButton.containsPoint(e.getPoint()));
		downButton.setHovering(inToolbar && downButton.containsPoint(e.getPoint()));
		
		drawShip = !inToolbar;
		if (selectedShip != null) {
			selectedShip.setCenter(e.getX(), e.getY());
		}
		
		e.consume();
	}
	
	public void mousePressed(MouseEvent e) {
		if (placementArea.contains(e.getPoint())) {
			for (ShipSelectSquare square : shipSelectSquares) {
				square.setPressed(square.containsPoint(e.getPoint()));
			}

			upButton.setPressed(upButton.containsPoint(e.getPoint()));
			downButton.setPressed(downButton.containsPoint(e.getPoint()));

			e.consume();
		}
	}

	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof MoveState) {
			ScreenStack.get().addScreenAfter(this,
					new MoveScreen(overlay, camera, screenWidth, screenHeight));
			ScreenStack.get().removeScreen(this);
		}
	}

}
