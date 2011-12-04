package edu.rit.se.sse.rapdevx;

import static org.junit.Assert.*;

import java.util.HashMap;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import edu.rit.se.sse.rapdevx.api.dataclasses.*;

public class TestJSONFromServerToClient 
{
	private String readFile(String fileName) {
		Scanner reader = null;
		try {
			reader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reader.nextLine();
	}
	
	@Test
	public void testAbility() {
		Ability fromClient = new Ability();

		HashMap<String, Integer> ab_spec_damage = new HashMap<String, Integer>();
		ab_spec_damage.put("one", 1);
		ab_spec_damage.put("two", 2);
		
		fromClient.setRadius(10);
		fromClient.setName("name");
		fromClient.setDefault_damage(5);
		fromClient.setSpecial_damages(ab_spec_damage);
		fromClient.setGid("this is a gid");
		
		Ability fromServer = Ability.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/AbilityFromServer.json"));
		
		assertEquals(fromClient.getDefault_damage(), fromServer.getDefault_damage());
		assertEquals(fromClient.getGid(), fromServer.getGid());
		assertEquals(fromClient.getName(), fromServer.getName());
		assertEquals(fromClient.getRadius(), fromServer.getRadius());
		assertEquals(fromClient.getSpecial_damages(), fromServer.getSpecial_damages());
	}
	
	@Test
	public void testAssetImage() {
		AssetImage fromClient = new AssetImage();
		fromClient.setFile("YWltZ3Rlc3QucG5n\n");
		fromClient.setGid("testTheGid");
		
		AssetImage fromServer = AssetImage.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/AssetImageFromServer.json"));
		
		assertEquals(fromServer.getFile(), fromClient.getFile());
		assertEquals(fromServer.getGid(), fromClient.getGid());
	}
	
	@Test
	public void testShipClass() {
		
	}
}
