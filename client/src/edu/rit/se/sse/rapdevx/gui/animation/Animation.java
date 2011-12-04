package edu.rit.se.sse.rapdevx.gui.animation;

import java.util.ArrayList;

import edu.rit.se.sse.rapdevx.events.AnimEvent;
import edu.rit.se.sse.rapdevx.events.AnimListener;
import edu.rit.se.sse.rapdevx.gui.drawable.Drawable;


public abstract class Animation implements Drawable {

	private ArrayList<AnimProperty> properties;
	private ArrayList<AnimListener> listeners;
	
	protected void addProperty(AnimProperty property) {
		properties.add(property);
	}
	
	protected void removeProperty(AnimProperty property) {
		properties.add(property);
	}
	
	public void update() {
		boolean finished = true;
		for (AnimProperty property : properties) {
			property.update();
			if (!property.isFinished())
				finished = false;
		}
		
		if (finished)
			notifyListeners(new AnimEvent(this));
	}

	public void addListener(AnimListener listener) {
		listeners.add(listener);
	}

	public void removeListener(AnimListener listener) {
		listeners.add(listener);
	}
	
	protected void notifyListeners(AnimEvent event) {
		for (AnimListener listener : listeners) {
			listener.animationFinished(new AnimEvent(this));
		}
	}
	
}
