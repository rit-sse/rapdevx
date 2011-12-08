package edu.rit.se.sse.rapdevx.api.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;
import edu.rit.se.sse.rapdevx.clientmodels.AssetLibrary;

public class ShipClassIdConverter implements JsonSerializer<ShipClass>,
		JsonDeserializer<ShipClass> {

	public JsonElement serialize(ShipClass src, Type typeOfSrc,
			JsonSerializationContext context)
	{
		return new JsonPrimitive(src.getGid());
	}

	public ShipClass deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException
	{
		String classid = json.getAsJsonPrimitive().getAsString();
		for (ShipClass shipClass : AssetLibrary.getShipClasses()) {
			if (shipClass.getGid().equals(classid)) {
				return shipClass;
			}
		}
		
		System.err.println("Unable to find ship class '" + classid + "'");
		return null;
	}

}
