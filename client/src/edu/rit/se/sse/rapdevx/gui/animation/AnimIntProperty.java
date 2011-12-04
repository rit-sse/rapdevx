package edu.rit.se.sse.rapdevx.gui.animation;

public class AnimIntProperty extends AnimProperty {

	private int startValue;
	private int endValue;
	
	private int currentValue;
	
	public AnimIntProperty(int startValue, int endValue) {
		super();
		
		this.startTime = startValue;
		this.endValue = endValue;
		this.currentValue = startValue;
	}
	
	public boolean isFinished() {
		return false;
	}

	public void update() {
		
	}
	
	public int getCurrentValue() {
		return currentValue;
	}

}
