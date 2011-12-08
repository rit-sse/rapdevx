package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import edu.rit.se.sse.rapdevx.gui.Sprite;

/**
 * POJO representing an instance of a unit/ship.
 * 
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class Unit {

	private String gid;
	private int player_num;
	private int hp;
	private Point location;

	@SerializedName("classid")
	private ShipClass shipClass;

	// TODO load from asset library
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

	public ShipClass getShipClass() {
		return shipClass;
	}

	public void setShipClass(ShipClass shipClass) {
		this.shipClass = shipClass;
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
		// TODO real names?
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
	public static Unit fromJSON(String incomingJson) {
		// Load the json by looking up a ship class by id in the asset library
		Gson gson = new Gson();
		return gson.fromJson(incomingJson, Unit.class);
	}

	/**
	 * Creates a JSON file from an Unit object.
	 * 
	 * @param Unit
	 */
	public void toJSON(Unit unit) {
		Gson gson = new Gson();
		String json = gson.toJson(unit);

		try {
			FileWriter file = new FileWriter(new File("UnitFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public Unit() {

	}

}