package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;

/**
 * POJO representing a Unit's Move on the server side of things. Will be
 * annotate in order to have Jackson data bindings.
 * 
 * @author Ben Nicholas
 * @author Paul Cassidy
 */
public class MovementOrder {

	private String unitid;
	private LinkedList<Point> path = new LinkedList<Point>();
	private String gid;

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public LinkedList<Point> getPath() {
		return path;
	}

	public void setPath(LinkedList<Point> path) {
		this.path = path;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * Creates and maps to an MovementOrder object.
	 * 
	 * @return The mapped MovementOrder as an MovementOrder object. or null if
	 *         error.
	 */
	public static MovementOrder fromJSON(String incomingJson) {
		Gson gson = new Gson();
		return gson.fromJson(incomingJson, MovementOrder.class);
	}

	/**
	 * Creates a JSON file from an MovementOrder object.
	 * 
	 * @param MovementOrder
	 */
	public void toJSON(MovementOrder movementOrder) {
		Gson gson = new Gson();
		String json = gson.toJson(movementOrder);

		try {
			FileWriter file = new FileWriter(new File("MovementOrderFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public MovementOrder() {

	}

}
