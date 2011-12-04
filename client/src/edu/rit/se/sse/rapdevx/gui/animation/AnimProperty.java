package edu.rit.se.sse.rapdevx.gui.animation;

public abstract class AnimProperty {
	
	protected int duration;
	protected int startTime;
	protected int currentTime;

	public AnimProperty(int startTime, int duration) {
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public AnimProperty() {
		
	}

	public abstract boolean isFinished();
	public abstract void update();
	
}
