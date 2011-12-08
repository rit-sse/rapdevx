package edu.rit.se.sse.rapdevx.clientmodels;

import edu.rit.se.sse.rapdevx.api.dataclasses.Unit;

public class Attack {

	//private Ability ability;
	
	private Unit sourceUnit = null;
	private Unit targetUnit = null;
	
	public void setSourceUnit(Unit unit) {
		this.sourceUnit = unit;
	}
	
	public Unit getSourceShip() {
		return sourceUnit;
	}
	
	public void setTargetUnit(Unit unit) {
		this.targetUnit = unit;
	}
	
	public Unit getTargetUnit() {
		return targetUnit;
	}
	
}
