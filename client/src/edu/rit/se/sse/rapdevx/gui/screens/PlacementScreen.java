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
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Camera;
import edu.rit.se.sse.rapdevx.gui.drawable.DrawableShip;

public class PlacementScreen extends Screen implements StateListener {

	private static final Color PLACEMENT_CLEAR = new Color(124, 124, 124, 124);
	private static final Color PLACEMENT_SOLID = new Color(124, 124, 124);
	
	private static final Color BUTTON_CLEAR_BACK = new Color(160, 160, 160, 96);
	private static final Color BUTTON_SOLID_BACK = new Color(192, 192, 192);
	
	/** A reference to the map camera for positioning objects in world space */
	private Camera camera;
	
	private Rectangle placementArea;
	private Color placementColor;
	private int shownIndex;
	
	private Rectangle upArea;
	private Polygon upTriangle;
	private Rectangle downArea;
	private Polygon downTriangle;
	private Rectangle hoveredRectangle;
	
	private ArrayList<ShipSelectSquare> shipSelectSquares;
	private ShipSelectSquare selectedSquare;
	
	private OverlayScreen overlay;
	
	public PlacementScreen(Camera camera, int width, int height) {
		super(width, height);
		this.camera = camera;
		
		this.overlay = new OverlayScreen(width, height);
		
		placementArea = new Rectangle(88, 472);
		placementArea.x = 96;
		placementArea.y = 128;
		
		placementColor = PLACEMENT_CLEAR;
		
		upArea = new Rectangle(72, 32);
		upArea.x = 8;
		upArea.y = 4;
		upArea.translate(placementArea.x, placementArea.y);
		
		upTriangle = new Polygon();
		upTriangle.addPoint(upArea.x + 4, upArea.y + 28);
		upTriangle.addPoint(upArea.x + 64, upArea.y + 28);
		upTriangle.addPoint(upArea.x + 36, upArea.y + 4);
		
		downArea = new Rectangle(72, 32);
		downArea.x = 8;
		downArea.y = 436;
		downArea.translate(placementArea.x, placementArea.y);
		
		downTriangle = new Polygon();
		downTriangle.addPoint(downArea.x + 4, downArea.y + 4);
		downTriangle.addPoint(downArea.x + 64, downArea.y + 4);
		downTriangle.addPoint(downArea.x + 36, downArea.y + 28);
		
		shownIndex = 0;
		
		shipSelectSquares = new ArrayList<ShipSelectSquare>();
		// TODO actually load the ships
		// Max 5 ships displayed at any time...
		for (int i = 0; i < 6; i++) {
			int x = 8;
			int y = (32 + 8 * (i + 1)) + (72 * (i));
			ShipSelectSquare square = new ShipSelectSquare(x, y);
			
			shipSelectSquares.add(square);
		}
		
		GameSession.get().addStateListener(this);
	}
	
	public void init() {
		ScreenStack.get().addScreenAfter(this, overlay);
	}

	public void draw(Graphics2D gPen) {
		
		gPen.setColor(placementColor);
		gPen.fillRoundRect(placementArea.x, placementArea.y, 
				placementArea.width, placementArea.height,
				15, 15);
		
		int buttonAlpha = 96;
		int grayLevel = 160;
		if (hoveredRectangle == upArea) buttonAlpha = 255;
		if (shownIndex == 0) buttonAlpha = 0;
		if (hoveredRectangle == upArea) grayLevel = 192;
		
		gPen.setColor(new Color(grayLevel, grayLevel, grayLevel, buttonAlpha));
		gPen.fillRoundRect(upArea.x, upArea.y, 
				upArea.width, upArea.height,
				10, 10);
		
		gPen.setColor(new Color(255, 0, 0, buttonAlpha));
		gPen.fillPolygon(upTriangle);
		
		buttonAlpha = 96;
		grayLevel = 160;
		if (hoveredRectangle == downArea) buttonAlpha = 255;
		if (shownIndex >= shipSelectSquares.size() - 5) buttonAlpha = 0;
		if (hoveredRectangle == downArea) grayLevel = 192;
		
		gPen.setColor(new Color(grayLevel, grayLevel, grayLevel, buttonAlpha));
		gPen.fillRoundRect(downArea.x, downArea.y, 
				downArea.width, downArea.height,
				10, 10);

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
		
		placementColor = PLACEMENT_CLEAR;
		if (placementArea.contains(e.getPoint())) {
			placementColor = PLACEMENT_SOLID;
			e.consume();
		}
		
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
		
		private final Color SELECT_CLEAR = new Color(160, 160, 160, 96);
		private final Color SELECT_SOLID = new Color(192, 192, 192);
		
		private Rectangle selectArea;
		private Color selectColor = SELECT_CLEAR;
		private boolean isHoveredOver;
		private boolean isSelected;
		
		// TODO Probably don't want to use drawable ship...
		private DrawableShip ship;
		
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
		}
		
		public boolean containsPoint(Point p) {
			
			return selectArea.contains(p);
			
		}
		
		public void draw(Graphics2D gPen) {
			
			if (this.isSelected) gPen.setColor(SELECT_SOLID);
			else                 gPen.setColor(selectColor);
			gPen.fillRoundRect(selectArea.x, selectArea.y, 
					selectArea.width, selectArea.height,
					10, 10);
			
			if (this.isHoveredOver || this.isSelected) {
				gPen.setColor(Color.BLUE);
				gPen.drawRoundRect(selectArea.x, selectArea.y, 
					selectArea.width, selectArea.height,
					10, 10);
			}
			
			ship.draw(gPen, selectArea);
			
		}
		
		public void setHoveredOver(boolean isHoveredOver) {
			this.isHoveredOver = isHoveredOver;
			if (isHoveredOver) {
				selectColor = SELECT_SOLID;
			}
			else {
				selectColor = SELECT_CLEAR;
			}
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
