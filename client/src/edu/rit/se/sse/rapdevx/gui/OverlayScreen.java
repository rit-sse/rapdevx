package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import edu.rit.se.sse.rapdevx.audio.AudioManager;
import edu.rit.se.sse.rapdevx.audio.Sound;
import edu.rit.se.sse.rapdevx.clientstate.AttackState;
import edu.rit.se.sse.rapdevx.clientstate.DoneState;
import edu.rit.se.sse.rapdevx.clientstate.MoveState;
import edu.rit.se.sse.rapdevx.clientstate.StartingState;
import edu.rit.se.sse.rapdevx.clientstate.UnitPlacementState;
import edu.rit.se.sse.rapdevx.events.StateEvent;
import edu.rit.se.sse.rapdevx.events.StateListener;
import edu.rit.se.sse.rapdevx.gui.images.GrayableImage;
import edu.rit.se.sse.rapdevx.gui.images.IGrayableImage;

public class OverlayScreen extends Screen implements StateListener {

	private static final int STARTING_X_MODIFIER = 212;
	
	private static final int UNDO_MODIFIER_X = 0;
	private static final int REDO_MODIFIER_X = 32;
	private static final int PHASE_MODIFIER_X = 64;
	private static final int READY_MODIFIER_X = 292;
	private static final int BEG_MODIFIER_X = 52;
	private static final int BEG_MODIFIER_Y = 32;
	private static final int BACK2_MODIFIER_X = 92;
	private static final int BACK2_MODIFIER_Y = 32;
	private static final int BACK1_MODIFIER_X = 132;
	private static final int BACK1_MODIFIER_Y = 32;
	private static final int PAUSE_MODIFIER_X = 172;
	private static final int PAUSE_MODIFIER_Y = 32;
	private static final int FORW1_MODIFIER_X = 212;
	private static final int FORW1_MODIFIER_Y = 32;
	private static final int FORW2_MODIFIER_X = 252;
	private static final int FORW2_MODIFIER_Y = 32;
	private static final int SKIP_MODIFIER_X = 292;
	private static final int SKIP_MODIFIER_Y = 32;
	
	private IGrayableImage undo;
	private IGrayableImage redo;
	private IGrayableImage phase;
	private IGrayableImage readyEnabled;
	private IGrayableImage readyDisabled;
	private IGrayableImage beginning;
	private IGrayableImage backfast;
	private IGrayableImage back;
	private IGrayableImage pause;
	private IGrayableImage forward;
	private IGrayableImage forwardfast;
	private IGrayableImage skip;
	
	private IGrayableImage selectedImage;
	
	private boolean isReady;
	
	private Rectangle mainBounds;
	private Rectangle playbackBounds;
	
	public OverlayScreen(int width, int height, int insetLeft, int insetTop) {
		super(width, height);

		int STARTING_X = (width / 2) - STARTING_X_MODIFIER;
		int STARTING_Y = 0;
		
		isReady = false;
		
		mainBounds = new Rectangle(382, 32);
		mainBounds.x = STARTING_X + insetLeft;
		mainBounds.y = insetTop;
		
		playbackBounds = new Rectangle(280, 64);
		playbackBounds.x = STARTING_X + 52 + insetTop;
		playbackBounds.y = 32 + insetTop;
		
		undo = new GrayableImage("assets/Undo.png",
				STARTING_X + UNDO_MODIFIER_X, STARTING_Y,
				insetLeft, insetTop);
		redo = new GrayableImage("assets/Redo.png",
				STARTING_X + REDO_MODIFIER_X, STARTING_Y,
				insetLeft, insetTop);
		phase = new GrayableImage("assets/StatusBar.png",
				STARTING_X + PHASE_MODIFIER_X, STARTING_Y,
				insetLeft, insetTop);
		readyEnabled = new GrayableImage("assets/Ready-enabled.png",
				STARTING_X + READY_MODIFIER_X, STARTING_Y,
				insetLeft, insetTop);
		readyDisabled = new GrayableImage("assets/Ready-disabled.png",
				STARTING_X + READY_MODIFIER_X, STARTING_Y,
				insetLeft, insetTop);
		beginning = new GrayableImage("assets/Play-beginning.png",
				STARTING_X + BEG_MODIFIER_X, STARTING_Y + BEG_MODIFIER_Y,
				insetLeft, insetTop);
		backfast = new GrayableImage("assets/Play-backfast.png",
				STARTING_X + BACK2_MODIFIER_X, STARTING_Y + BACK2_MODIFIER_Y,
				insetLeft, insetTop);
		back = new GrayableImage("assets/Play-back.png",
				STARTING_X + BACK1_MODIFIER_X, STARTING_Y + BACK1_MODIFIER_Y,
				insetLeft, insetTop);
		pause = new GrayableImage("assets/Play-pause.png",
				STARTING_X + PAUSE_MODIFIER_X, STARTING_Y + PAUSE_MODIFIER_Y,
				insetLeft, insetTop);
		forward = new GrayableImage("assets/Play-forward.png",
				STARTING_X + FORW1_MODIFIER_X, STARTING_Y + FORW1_MODIFIER_Y,
				insetLeft, insetTop);
		forwardfast = new GrayableImage("assets/Play-forwardfast.png",
				STARTING_X + FORW2_MODIFIER_X, STARTING_Y + FORW2_MODIFIER_Y,
				insetLeft, insetTop);
		skip = new GrayableImage("assets/Play-skip.png",
				STARTING_X + SKIP_MODIFIER_X, STARTING_Y + SKIP_MODIFIER_Y,
				insetLeft, insetTop);
		
	}

	public void update(boolean hasFocus, boolean isVisible) {
		
	}
	
	public void updateTransition(double position, int direction) {
		
	}
	
	public void draw(Graphics2D gPen) {
		
		// Bottom part, conditional, playback buttons
		beginning.draw(gPen);
		backfast.draw(gPen);
		back.draw(gPen);
		pause.draw(gPen);
		forward.draw(gPen);
		forwardfast.draw(gPen);
		skip.draw(gPen);
		
		// Top part, under phase bar
		undo.draw(gPen);
		redo.draw(gPen);
		if (!isReady) readyEnabled.draw(gPen);
		else readyDisabled.draw(gPen);
		
		// Top part, phase bar
		phase.draw(gPen);
	}
	
	public void mouseReleased(MouseEvent e) {if (mainBounds.contains(e.getPoint())) {
		if (undo.containsPoint(e.getPoint())) {
			AudioManager.get().play(Sound.BattleStart);
		}
		else if (redo.containsPoint(e.getPoint())) {
		}
		else if (readyEnabled.containsPoint(e.getPoint())) {
			// TODO more shit
			isReady = true;
		}
	}
//	if playback controls are active TODO
	else if (playbackBounds.contains(e.getPoint())) {
		if (beginning.containsPoint(e.getPoint())) {
		}
		else if (backfast.containsPoint(e.getPoint())) {
		}
		else if (back.containsPoint(e.getPoint())) {
		}
		else if (pause.containsPoint(e.getPoint())) {
		}
		else if (forward.containsPoint(e.getPoint())) {
		}
		else if (forwardfast.containsPoint(e.getPoint())) {
		}
		else if (skip.containsPoint(e.getPoint())) {
		}
	}
	}
	
	public void mouseMoved(MouseEvent e) {
		IGrayableImage currentlyOver = null;
		
		if (mainBounds.contains(e.getPoint())) {
			if (undo.containsPoint(e.getPoint())) {
				currentlyOver = undo;
			}
			else if (redo.containsPoint(e.getPoint())) {
				currentlyOver = redo;
			}
			else if (readyEnabled.containsPoint(e.getPoint())) {
				currentlyOver = readyEnabled;
			}
		}
//		if playback controls are active TODO
		else if (playbackBounds.contains(e.getPoint())) {
			if (beginning.containsPoint(e.getPoint())) {
				currentlyOver = beginning;
			}
			else if (backfast.containsPoint(e.getPoint())) {
				currentlyOver = backfast;
			}
			else if (back.containsPoint(e.getPoint())) {
				currentlyOver = back;
			}
			else if (pause.containsPoint(e.getPoint())) {
				currentlyOver = pause;
			}
			else if (forward.containsPoint(e.getPoint())) {
				currentlyOver = forward;
			}
			else if (forwardfast.containsPoint(e.getPoint())) {
				currentlyOver = forwardfast;
			}
			else if (skip.containsPoint(e.getPoint())) {
				currentlyOver = skip;
			}
		}
		
		if (selectedImage != null) {
			selectedImage.setHovering(false);
		}
		if (currentlyOver != null) {
			currentlyOver.setHovering(true);
			selectedImage = currentlyOver;
			e.consume();
		}
	}
	
	public void stateChanged(StateEvent e) {
		isReady = false;
		
		if (e.getNewState() instanceof StartingState) {
			// TODO draw text "Waiting..."
		}
		else if (e.getNewState() instanceof UnitPlacementState) {
			// TODO draw text "Placing"
		}
		else if (e.getNewState() instanceof MoveState) {
			// TODO draw text "Moving"
		}
		else if (e.getNewState() instanceof AttackState) {
			// TODO draw text "Attack"
		}
		else if (e.getNewState() instanceof DoneState) {
			// TODO draw text "Victory!/Defeat!"
		}
	}

}
