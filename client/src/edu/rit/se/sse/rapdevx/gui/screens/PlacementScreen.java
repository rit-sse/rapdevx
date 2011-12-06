package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.clientmodels.Ship;
import edu.rit.se.sse.rapdevx.clientstate.GameSession;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.RectangleBackground;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Camera;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;

public class PlacementScreen extends Screen implements StateListener {

	
	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;
	
	private Rectangle placementArea;
	private int shownIndex;
	
	private Rectangle upArea;
	private Polygon upTriangle;
	RectangleBackground upButton;
	private Rectangle downArea;
	private Polygon downTriangle;
	RectangleBackground downButton;
	private Rectangle hoveredRectangle;
	
	private ArrayList<ShipSelectSquare> shipSelectSquares;
	private ShipSelectSquare selectedSquare;
	
	private OverlayScreen overlay;
	RectangleBackground background;
	
	public PlacementScreen(OverlayScreen overlay, Camera camera, int width, int height) {
		super(width, height);
		
		this.camera = camera;
		
		this.overlay = overlay;
		
		background = new RectangleBackground(96, 128, 88, 483, false);
		
		placementArea = new Rectangle(88, 472);
		placementArea.x = 96;
		placementArea.y = 128;
		
		upArea = new Rectangle(72, 32);
		upArea.x = 8;
		upArea.y = 9;
		upArea.translate(placementArea.x, placementArea.y);
		
		upButton = new RectangleBackground( upArea.x, upArea.y, upArea.width, upArea.height, false );
		
		upTriangle = new Polygon();
		upTriangle.addPoint(upArea.x + 7, upArea.y + 26);
		upTriangle.addPoint(upArea.x + 65, upArea.y + 26);
		upTriangle.addPoint(upArea.x + 37, upArea.y + 5);
		
		downArea = new Rectangle(72, 32);
		downArea.x = 8;
		downArea.y = 441;
		downArea.translate(placementArea.x, placementArea.y);
		
		downButton = new RectangleBackground( downArea.x, downArea.y, downArea.width, downArea.height, false );
		
		downTriangle = new Polygon();
		downTriangle.addPoint(downArea.x + 7, downArea.y + 5);
		downTriangle.addPoint(downArea.x + 65, downArea.y + 5);
		downTriangle.addPoint(downArea.x + 37, downArea.y + 26);
		
		shownIndex = 0;
		
		shipSelectSquares = new ArrayList<ShipSelectSquare>();
		// TODO actually load the ships
		// Max 5 ships displayed at any time...
		for (int i = 0; i < 6; i++) {
			int x = 8;
			int y = (32 + 8 * (i + 1)) + (72 * (i)) + 5;
			ShipSelectSquare square = new ShipSelectSquare(x, y);
			
			shipSelectSquares.add(square);
		}
		
		GameSession.get().addStateListener(this);
	}
	
	/**
	 *  draw those fancy blue corner thingys around the button
	 * @param gPen - the pen
	 * @param bounds - the bounds. WARNING! this is Rectangle, not Rectangle2D
	 */
	public void drawSelectors(Graphics2D gPen, Rectangle bounds) {
		int x = bounds.x;
		int y = bounds.y;
		
		gPen.setColor(new Color(30, 127, 255));
		gPen.fill(new Rectangle(x, y, 12, 4));
		gPen.fill(new Rectangle(x, y, 4, 12));
		gPen.fill(new Rectangle(x + bounds.width - 12, y, 12, 4));
		gPen.fill(new Rectangle(x + bounds.width - 4, y, 4, 12));
		gPen.fill(new Rectangle(x, y + bounds.height - 12, 4, 12));
		gPen.fill(new Rectangle(x, y + bounds.height - 4, 12, 4));
		gPen.fill(new Rectangle(x + bounds.width - 12, y + bounds.height
				- 4, 12, 4));
		gPen.fill(new Rectangle(x + bounds.width - 4, y + bounds.height
				- 12, 4, 12));
	}

	public void draw(Graphics2D gPen) {
		
//		gPen.setColor(placementColor);
//		gPen.fillRoundRect(placementArea.x, placementArea.y, 
//				placementArea.width, placementArea.height,
//				15, 15);
		
		background.draw(gPen);
		
		int buttonAlpha = 255;
		upButton.setHover(false);
		if (shownIndex == 0) {
			buttonAlpha = 0;
			upButton.setPressed(true);
		} else {
			upButton.setPressed(false);
			if (hoveredRectangle == upArea)
				upButton.setHover(true);
		}
		upButton.draw(gPen);
		
		/// up arrow
		gPen.setColor(new Color(255, 0, 0, buttonAlpha));
		gPen.fillPolygon(upTriangle);

		buttonAlpha = 255;
		downButton.setHover(false);
		if  (shownIndex >= shipSelectSquares.size() - 5) {
			buttonAlpha = 0;
			downButton.setPressed(true);
		}
		else {
			downButton.setPressed(false);
			if (hoveredRectangle == downArea)
				downButton.setHover(true);
		}
		
		downButton.draw(gPen);

		/// down arrow
		gPen.setColor(new Color(255, 0, 0, buttonAlpha));
		gPen.fillPolygon(downTriangle);
		
		for (int i = shownIndex; i < shownIndex + 5; i++) {
			if (i >= shipSelectSquares.size()) break;
			
			shipSelectSquares.get(i).draw(gPen);
		}
		
	}

	public void update(boolean hasFocus, boolean isVisible) {
	}

	public void mouseReleased(MouseEvent e) {
		
		if (downArea.contains(e.getPoint()) && 
				shownIndex < shipSelectSquares.size() - 5) {
			
			shownIndex += 1;
			for (ShipSelectSquare square : shipSelectSquares) {
				square.moveUp();
			}
			e.consume();
		}
		else if (upArea.contains(e.getPoint()) && shownIndex > 0){
			shownIndex -= 1;
			for (ShipSelectSquare square : shipSelectSquares) {
				square.moveDown();
			}
			e.consume();
		}
		
		if (e.isConsumed())
			return;
		
		for (ShipSelectSquare square : shipSelectSquares) {
			if (square.containsPoint(e.getPoint())) {
				if (selectedSquare != null) {
					selectedSquare.setSelected(false);
				}
				
				selectedSquare = square;
				selectedSquare.setSelected(true);
				e.consume();
				return;
			}
		}
		
		if (selectedSquare != null) {
			// TODO place a ship
			selectedSquare.setSelected(false);
			e.consume();
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
//		placementColor = PLACEMENT_FULL;//PLACEMENT_CLEAR;
//		if (placementArea.contains(e.getPoint())) {
//			placementColor = PLACEMENT_SOLID;
//			e.consume();
//		}
		
		for (ShipSelectSquare square : shipSelectSquares) {
			square.setHoveredOver(square.containsPoint(e.getPoint()));
		}
		
		if (upArea.contains(e.getPoint())) {
			hoveredRectangle = upArea;
			e.consume();
		}
		else if (downArea.contains(e.getPoint())) {
			hoveredRectangle = downArea;
			e.consume();
		}
		else {
			hoveredRectangle = null;
		}
		
	}
	
	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof MoveState) {
			ScreenStack.get().addScreenAfter(this, new MoveScreen(overlay, camera, screenWidth, screenHeight));
			ScreenStack.get().removeScreen(this);
		}
	}
	
	private class ShipSelectSquare {
		
		private Rectangle selectArea;
		private boolean isHoveredOver;
		private boolean isSelected;
		
		// TODO Probably don't want to use drawable ship...
		private DrawableShip ship;
		
		private RectangleBackground background;
		
		public ShipSelectSquare(int x, int y) {
			
			selectArea = new Rectangle(72, 72);
			selectArea.x = x;
			selectArea.y = y;
			
			selectArea.translate(placementArea.x, placementArea.y);
			
			Ship ship = new Ship();
			ship.setX((selectArea.x / 2) + 3);
			ship.setY((selectArea.y / 2) + 3);
			ship.setHp(ship.getMaxHp());
			this.ship = new DrawableShip(ship, new Color(48, 129, 233));
			
			background = new RectangleBackground(selectArea.x, selectArea.y, 72, 72, false );
		}
		
		public boolean containsPoint(Point p) {
			
			return selectArea.contains(p);
			
		}
		
		public void draw(Graphics2D gPen) {
			
			background = new RectangleBackground(selectArea.x, selectArea.y, 72, 72);
			background.setPressed(isSelected);
			background.setHover(isHoveredOver || isSelected);
			background.draw(gPen);
			
			ship.draw(gPen, selectArea);
			
		}
		
		public void setHoveredOver(boolean isHoveredOver) {
			this.isHoveredOver = isHoveredOver;
		}
		
		public void setSelected(boolean selected) {
			this.isSelected = selected;
		}
		
		public int magicMoveConstant = 80;
		
		public void moveUp() {
			selectArea.y -= magicMoveConstant;
			ship.setX(selectArea.x + 6);
			ship.setY(selectArea.y + 6);
		}
		
		public void moveDown() {
			selectArea.y += magicMoveConstant;
			ship.setX(selectArea.x + 6);
			ship.setY(selectArea.y + 6);
		}
		
	}
	
}
