package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;
import edu.rit.se.sse.rapdevx.gui.Sprite;

/**
 * POJO representing an instance of a unit/ship.
 *
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class Unit {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private int player_num;
	private int hp;
	private String gid;
	private Point location;
	
	private ShipClass shipClass;
	
	//TODO load from asset library
	private Sprite image = new Sprite("assets/ship.png");
	
	public int getPlayer_num() {
		return player_num;
	}
	public void setPlayer_num(int player_num) {
		this.player_num = player_num;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getClassid() {
		return shipClass.getGid();
	}
	public void setClassid(String classid) {
		this.shipClass = null;
		
		for (ShipClass shipClass : AssetLibrary.getShipClasses()) {
			if (shipClass.getGid().equals(classid)) {
				this.shipClass = shipClass;
				return;
			}
		}
		
		//TODO throw/print error
	}
	public String getgid() {
		return gid;
	}
	public void setgid(String gid) {
		this.gid = gid;
	}
	
	public int getX() {
		return location.x;
	}
	public void setX(int x) {
		this.location.x = x;
	}
	
	public int getY() {
		return location.y;
	}
	public void setY(int y) {
		this.location.y = y;
	}
	
	public Point getLocation() {
		return this.location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public int getMaxHP() {
		return shipClass.getMaxhp();
	}
	public String getClassName() {
		//TODO real names?
		return gid;
	}
	
	public void draw(Graphics2D gPen) {
		image.draw(gPen, location.x, location.y);
	}
	

	/**
	 * Creates and maps to an Unit object.
	 * 
	 * @return The mapped Unit as an Unit object. or null if error.
	 */
	public static Unit fromJSON(String incomingJson){

		try {
			Unit unit = mapper.readValue(incomingJson, Unit.class);
			return unit;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Creates a JSON file from an Unit object.
	 * 
	 * @param Unit
	 */
	public void toJSON(Unit unit){
		try {
			mapper.writeValue(new File("UnitFromJava.json"), unit);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Unit(){
		
	}
	public void setTuple(Hashtable<Integer, Integer> tuple) {
		int x = tuple.elements().nextElement();
		int y = tuple.elements().nextElement();
		
		this.location = new Point(x, y);
	}
	public Hashtable<Integer, Integer> getTuple() {
		Hashtable<Integer, Integer> tuple = new Hashtable<Integer, Integer>();
		tuple.put(this.location.x, this.location.y);
		return tuple;
	}
	
}

