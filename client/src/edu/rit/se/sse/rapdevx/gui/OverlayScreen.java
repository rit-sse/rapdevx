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

public class OverlayScreen extends Screen implements StateListener
{

	private static final int STARTING_X_MODIFIER = 212;

	private static final int UNDO_MODIFIER_X = 3;
	private static final int REDO_MODIFIER_X = 35;
	private static final int PHASE_MODIFIER_X = 64;
	private static final int READY_MODIFIER_X = 287;

	private static final int TURN_CONTROL_Y = 0;

	private IGrayableImage undo;
	private IGrayableImage redo;
	private IGrayableImage phase;
	private IGrayableImage readyEnabled;
	private IGrayableImage readyDisabled;

	private IGrayableImage[] turnControlImages = new IGrayableImage[7];
	private String[] turnControlImageStr = { "beginning", "backfast", "back",
			"pause", "forward", "forwardfast", "skip" };

	private IGrayableImage selectedImage;

	private boolean isReady;

	private Rectangle mainBounds;
	private Rectangle playbackBounds;

	public OverlayScreen(int width, int height, int insetLeft, int insetTop)
	{
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

		for (int x = 0; x < 7; ++x)
		{
			turnControlImageStr[x] = "assets/Play-" + turnControlImageStr[x]
					+ ".png";
			turnControlImages[x] = new GrayableImage(turnControlImageStr[x],
					STARTING_X + 40 * x + 52, STARTING_Y + TURN_CONTROL_Y,
					insetLeft, insetTop);
		}

		undo = new GrayableImage("assets/Undo.png", STARTING_X
				+ UNDO_MODIFIER_X, STARTING_Y, insetLeft, insetTop);
		redo = new GrayableImage("assets/Redo.png", STARTING_X
				+ REDO_MODIFIER_X, STARTING_Y, insetLeft, insetTop);
		phase = new GrayableImage("assets/StatusBar.png", STARTING_X
				+ PHASE_MODIFIER_X, STARTING_Y, insetLeft, insetTop);
		readyEnabled = new GrayableImage("assets/Ready-enabled.png", STARTING_X
				+ READY_MODIFIER_X, STARTING_Y, insetLeft, insetTop);
		readyDisabled = new GrayableImage("assets/Ready-disabled.png",
				STARTING_X + READY_MODIFIER_X, STARTING_Y, insetLeft, insetTop);
	}

	public void update(boolean hasFocus, boolean isVisible)
	{

	}

	public void updateTransition(double position, int direction)
	{

	}

	public void draw(Graphics2D gPen)
	{

		// Bottom part, conditional, playback buttons
		for(IGrayableImage img : turnControlImages)
		{
			img.draw(gPen);
		}

		// Top part, under phase bar
		undo.draw(gPen);
		redo.draw(gPen);
		if (!isReady)
			readyEnabled.draw(gPen);
		else
			readyDisabled.draw(gPen);

		// Top part, phase bar
		phase.draw(gPen);
	}

	public void mouseReleased(MouseEvent e)
	{
		if (mainBounds.contains(e.getPoint()))
		{
			if (undo.containsPoint(e.getPoint()))
			{
				AudioManager.get().play(Sound.BattleStart);
			}
			else if (redo.containsPoint(e.getPoint()))
			{
			}
			else if (readyEnabled.containsPoint(e.getPoint()))
			{
				// TODO more shit
				isReady = true;
			}
		}
		// if playback controls are active TODO
		else if (playbackBounds.contains(e.getPoint()))
		{
			for(IGrayableImage img : turnControlImages)
			{
				if(img.containsPoint(e.getPoint()))
				{
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e)
	{
		IGrayableImage currentlyOver = null;

		if (mainBounds.contains(e.getPoint()))
		{
			if (undo.containsPoint(e.getPoint()))
			{
				currentlyOver = undo;
			}
			else if (redo.containsPoint(e.getPoint()))
			{
				currentlyOver = redo;
			}
			else if (readyEnabled.containsPoint(e.getPoint()))
			{
				currentlyOver = readyEnabled;
			}
		}
		// if playback controls are active TODO
		else if (playbackBounds.contains(e.getPoint()))
		{
			for(IGrayableImage img : turnControlImages)
			{
				if(img.containsPoint(e.getPoint()))
				{
					currentlyOver = img;
				}
			}
		}

		if (selectedImage != null)
		{
			selectedImage.setHovering(false);
		}
		if (currentlyOver != null)
		{
			currentlyOver.setHovering(true);
			selectedImage = currentlyOver;
			e.consume();
		}
	}

	public void stateChanged(StateEvent e)
	{
		isReady = false;

		if (e.getNewState() instanceof StartingState)
		{
			// TODO draw text "Waiting..."
		}
		else if (e.getNewState() instanceof UnitPlacementState)
		{
			// TODO draw text "Placing"
		}
		else if (e.getNewState() instanceof MoveState)
		{
			// TODO draw text "Moving"
		}
		else if (e.getNewState() instanceof AttackState)
		{
			// TODO draw text "Attack"
		}
		else if (e.getNewState() instanceof DoneState)
		{
			// TODO draw text "Victory!/Defeat!"
		}
	}

}
