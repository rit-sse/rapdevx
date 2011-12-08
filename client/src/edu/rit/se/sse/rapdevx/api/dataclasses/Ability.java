package edu.rit.se.sse.rapdevx.api.dataclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Ability {

	private String gid;
	private String name;
	private int radius;

	@SerializedName("default_damage")
	private int defaultDamage;

	@SerializedName("special_damages")
	private Map<String, Integer> specialDamages = new Hashtable<String, Integer>();

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Integer> getSpecialDamages() {
		return specialDamages;
	}

	public void setSpecialDamages(Map<String, Integer> specialDamages) {
		this.specialDamages = specialDamages;
	}

	public int getDefaultDamage() {
		return defaultDamage;
	}

	public void setDefaultDamage(int defaultDamage) {
		this.defaultDamage = defaultDamage;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * Creates and maps to an Ability object.
	 * 
	 * @return The mapped Ability as an Ability object. or null if error.
	 */
	public static Ability fromJSON(String incomingJson) {
		Gson gson = new Gson();
		return gson.fromJson(incomingJson, Ability.class);
	}

	/**
	 * Creates a JSON file from an Ability object.
	 * 
	 * @param Ability
	 */
	public void toJSON(Ability ability) {
		Gson gson = new Gson();
		String json = gson.toJson(ability);

		try {
			FileWriter file = new FileWriter(new File("AbilityFromJava.json"));
			file.write(json);
			file.close();
		} catch (IOException e) {
			System.err.println("Unable to write units json to file");
		}
	}

	public Ability() {

	}

	public boolean equals(Object other) {
		Ability ot = (Ability) other;
		return (radius == ot.getRadius()) && (name.equals(ot.getName()))
				&& (defaultDamage == ot.getDefaultDamage())
				&& (specialDamages.equals(ot.getSpecialDamages()))
				&& (gid.equals(ot.getGid()));
	}

}
