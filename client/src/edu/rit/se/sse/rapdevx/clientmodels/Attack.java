package edu.rit.se.sse.rapdevx.clientmodels;

public class Attack {

	//private Ability ability;
	
	private Ship sourceShip = null;
	private Ship targetShip = null;
	
	public void setSourceShip(Ship ship) {
		this.sourceShip = ship;
	}
	
	public Ship getSourceShip() {
		return sourceShip;
	}
	
	public void setTargetShip(Ship ship) {
		this.targetShip = ship;
	}
	
	public Ship getTargetShip() {
		return targetShip;
	}
	
}
