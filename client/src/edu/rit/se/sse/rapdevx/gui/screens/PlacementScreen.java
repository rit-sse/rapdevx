package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import edu.rit.se.sse.rapdevx.api.GameApi;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;
import edu.rit.se.sse.rapdevx.api.dataclasses.Unit;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;
import edu.rit.se.sse.rapdevx.clientstate.GameSession;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.clientstate.UnitPlacementState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Camera;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;
import edu.rit.se.sse.rapdevx.gui.images.ArrowButton;
import edu.rit.se.sse.rapdevx.gui.images.RectangleBackground;
import edu.rit.se.sse.rapdevx.gui.images.ShipSelectSquare;

public class PlacementScreen extends Screen implements StateListener, ActionListener {

	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;
	private OverlayScreen overlay;

	private Rectangle placementArea;
	private int shownIndex;

	private ArrowButton upButton;
	private ArrowButton downButton;

	private ArrayList<ShipSelectSquare> shipSelectSquares;
	private ShipPlacement curPlacement;
	private boolean drawSelectedUnit;

	private RectangleBackground background;
	
	private ArrayList<ShipPlacement> placedShips;

	public PlacementScreen(OverlayScreen overlay, Camera camera, int width, int height) {
		super(width, height);
		
		this.camera = camera;
		this.overlay = overlay;
		overlay.addActionListener(this);

		background = new RectangleBackground(96, 128, 88, 483, false);
		shownIndex = 0;
		drawSelectedUnit = false;

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
		int x = 8;
		int y = 0;
		for (int i = 0; i < AssetLibrary.getShipClasses().size(); i++) {
			y = (32 + 8 * ((i % 5) + 1)) + (72 * (i % 5)) + 5;
			ShipSelectSquare square = new ShipSelectSquare(
					AssetLibrary.getShipClasses().get(i),
					x + placementArea.x,
					y + placementArea.y);

			shipSelectSquares.add(square);
		}
		
		placedShips = new ArrayList<ShipPlacement>();

		GameSession.get().addStateListener(this);
	}

	public void init() {
		ScreenStack.get().addScreenAfter(this, overlay);
	}

	public void draw(Graphics2D gPen) {
		gPen.translate(-camera.getX(), -camera.getY());
		for (ShipPlacement ship : placedShips)
			ship.draw(gPen);
		gPen.translate(camera.getX(), camera.getY());
		
		if (drawSelectedUnit && curPlacement != null)
			curPlacement.draw(gPen);
		
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
		upButton.setPressed(false);
		downButton.setPressed(false);
		
		if (placementArea.contains(e.getPoint())) {
			if (downButton.containsPoint(e.getPoint())
					&& shownIndex < shipSelectSquares.size() - 5)
			{
				shownIndex += 1;
				for (ShipSelectSquare square : shipSelectSquares) {
					square.setPressed(false);
					curPlacement = null;
				}
			} else if (upButton.containsPoint(e.getPoint()) && shownIndex > 0) {
				shownIndex -= 1;
				for (ShipSelectSquare square : shipSelectSquares) {
					square.setPressed(false);
					curPlacement = null;
				}
			} else {
				for (ShipSelectSquare square : shipSelectSquares) {
					if (square.containsPoint(e.getPoint())) {
						curPlacement = new ShipPlacement(e.getX() - 32 + camera.getX(),
								e.getY() + camera.getY() -32, square.getShipClass());
						
						square.setPressed(true);
						break;
					}
				}
			}
			
			upButton.setEnabled(shownIndex >= shipSelectSquares.size() - 5);
			downButton.setEnabled(shownIndex == 0);

			e.consume();
		} else if (curPlacement != null) {
			// A ship is selected and the user has clicked the map
			// so add a copy of the ship at the cursor location
			placedShips.add(curPlacement.clone());
		}
	}

	public void mouseMoved(MouseEvent e) {
		boolean inToolbar = placementArea.contains(e.getPoint());
		
		for (ShipSelectSquare square : shipSelectSquares) {
			square.setHovering(inToolbar && square.containsPoint(e.getPoint()));
		}

		upButton.setHovering(inToolbar && upButton.containsPoint(e.getPoint()));
		downButton.setHovering(inToolbar && downButton.containsPoint(e.getPoint()));
		
		drawSelectedUnit = !inToolbar;
		if (curPlacement != null) {
			curPlacement.setX(e.getX() - 32);
			curPlacement.setY(e.getY() - 32);
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ready")) {
			((UnitPlacementState)GameSession.get().getCurrentState()).ready(placedShips);
		}
	}

	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof MoveState) {
			try {
				List<DrawableShip>ships = new ArrayList<DrawableShip>();
				for (Unit unit : GameApi.getUnits(GameSession.get().getSession()).getUnits()) {
					ships.add(new DrawableShip(unit, Color.RED));
				}
				
				overlay.removeActionListener(this);
				ScreenStack.get().addScreenAfter(this,
						new MoveScreen(ships, overlay, camera, screenWidth, screenHeight));
				ScreenStack.get().removeScreen(this);
				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Unable to get ship placements from the server");
			}
		}
	}

}
