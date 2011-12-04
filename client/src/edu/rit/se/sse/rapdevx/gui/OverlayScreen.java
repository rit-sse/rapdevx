package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import edu.rit.se.sse.rapdevx.clientstate.MoveState;
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
	
	public OverlayScreen(int width, int height) {
		super(width, height);

		int STARTING_X = (width / 2) - STARTING_X_MODIFIER;
		int STARTING_Y = 0;
		
		undo = new GrayableImage("assets/Undo.png",
				STARTING_X + UNDO_MODIFIER_X, STARTING_Y);
		redo = new GrayableImage("assets/Redo.png",
				STARTING_X + REDO_MODIFIER_X, STARTING_Y);
		phase = new GrayableImage("assets/StatusBar.png",
				STARTING_X + PHASE_MODIFIER_X, STARTING_Y);
		readyEnabled = new GrayableImage("assets/Ready-enabled.png",
				STARTING_X + READY_MODIFIER_X, STARTING_Y);
		readyDisabled = new GrayableImage("assets/Ready-disabled.png",
				STARTING_X + READY_MODIFIER_X, STARTING_Y);
		beginning = new GrayableImage("assets/Play-beginning.png",
				STARTING_X + BEG_MODIFIER_X, STARTING_Y + BEG_MODIFIER_Y);
		backfast = new GrayableImage("assets/Play-backfast.png",
				STARTING_X + BACK2_MODIFIER_X, STARTING_Y + BACK2_MODIFIER_Y);
		back = new GrayableImage("assets/Play-back.png",
				STARTING_X + BACK1_MODIFIER_X, STARTING_Y + BACK1_MODIFIER_Y);
		pause = new GrayableImage("assets/Play-pause.png",
				STARTING_X + PAUSE_MODIFIER_X, STARTING_Y + PAUSE_MODIFIER_Y);
		forward = new GrayableImage("assets/Play-forward.png",
				STARTING_X + FORW1_MODIFIER_X, STARTING_Y + FORW1_MODIFIER_Y);
		forwardfast = new GrayableImage("assets/Play-forwardfast.png",
				STARTING_X + FORW2_MODIFIER_X, STARTING_Y + FORW2_MODIFIER_Y);
		skip = new GrayableImage("assets/Play-skip.png",
				STARTING_X + SKIP_MODIFIER_X, STARTING_Y + SKIP_MODIFIER_Y);
		
	}

	public void update(boolean hasFocus, boolean isVisible) {
		
	}
	
	public void updateTransition(double position, int direction) {
		
	}
	
	public void draw(Graphics2D gPen) {
		
		// Bottom part, conditional, playback buttons
		beginning.draw(gPen);
//		backfast.draw(gPen);
//		back.draw(gPen);
//		pause.draw(gPen);
//		forward.draw(gPen);
//		forwardfast.draw(gPen);
//		skip.draw(gPen);
		
		// Top part, under phase bar
//		undo.draw(gPen);
//		redo.draw(gPen);
//		readyEnabled.draw(gPen);
		
		// Top part, phase bar
		phase.draw(gPen);

		readyEnabled.draw(gPen);
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void stateChanged(StateEvent e) {
		if (e.getNewState() instanceof MoveState) {
			//TODO add a move screen, remove this one
		}
	}

}
