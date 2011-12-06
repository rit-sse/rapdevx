package edu.rit.se.sse.rapdevx.gui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.clientstate.GameSession;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.Screen;
import edu.rit.se.sse.rapdevx.gui.ScreenStack;
import edu.rit.se.sse.rapdevx.gui.drawable.Camera;
import edu.rit.se.sse.rapdevx.gui.images.RectangleBackground;
import edu.rit.se.sse.rapdevx.gui.images.ShipSelectSquare;

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
	
	public PlacementScreen(Camera camera, int width, int height) {
		super(width, height);
		this.camera = camera;
		
		this.overlay = new OverlayScreen(width, height);
		
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
			ShipSelectSquare square = new ShipSelectSquare(x + placementArea.x, y + placementArea.y);
			
			shipSelectSquares.add(square);
		}
		
		GameSession.get().addStateListener(this);
	}
	
	public void init() {
		ScreenStack.get().addScreenAfter(this, overlay);
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
		upButton.setHovering(false);
		if (shownIndex == 0) {
			buttonAlpha = 0;
			upButton.setPressed(true);
		} else {
			upButton.setPressed(false);
			if (hoveredRectangle == upArea)
				upButton.setHovering(true);
		}
		upButton.draw(gPen);
		
		/// up arrow
		gPen.setColor(new Color(255, 0, 0, buttonAlpha));
		gPen.fillPolygon(upTriangle);

		buttonAlpha = 255;
		downButton.setHovering(false);
		if  (shownIndex >= shipSelectSquares.size() - 5) {
			buttonAlpha = 0;
			downButton.setPressed(true);
		}
		else {
			downButton.setPressed(false);
			if (hoveredRectangle == downArea)
				downButton.setHovering(true);
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
					selectedSquare.setPressed(false);
				}
				
				selectedSquare = square;
				selectedSquare.setPressed(true);
				e.consume();
				return;
			}
		}
		
		if (selectedSquare != null) {
			// TODO place a ship
			selectedSquare.setPressed(false);
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
			square.setHovering(square.containsPoint(e.getPoint()));
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
	
	
	
}
