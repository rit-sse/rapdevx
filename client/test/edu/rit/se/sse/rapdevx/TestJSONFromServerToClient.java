package edu.rit.se.sse.rapdevx;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import edu.rit.se.sse.rapdevx.api.dataclasses.Ability;
import edu.rit.se.sse.rapdevx.api.dataclasses.AssetImage;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;

public class TestJSONFromServerToClient {
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

		Ability fromServer = Ability
				.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/AbilityFromServer.json"));

		assertEquals(fromClient.getDefault_damage(),
				fromServer.getDefault_damage());
		assertEquals(fromClient.getGid(), fromServer.getGid());
		assertEquals(fromClient.getName(), fromServer.getName());
		assertEquals(fromClient.getRadius(), fromServer.getRadius());
		assertEquals(fromClient.getSpecial_damages(),
				fromServer.getSpecial_damages());
	}

	@Test
	public void testAssetImage() {
		AssetImage fromClient = new AssetImage();
		fromClient.setFile("YWltZ3Rlc3QucG5n\n");
		fromClient.setGid("testTheGid");

		AssetImage fromServer = AssetImage
				.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/AssetImageFromServer.json"));

		assertEquals(fromServer.getFile(), fromClient.getFile());
		assertEquals(fromServer.getGid(), fromClient.getGid());
	}

	@Test
	public void testShipClass() {
		ShipClass fromClient = new ShipClass();
		// DTO_Ability:
		Ability ab = new Ability();

		HashMap<String, Integer> ab_spec_damage = new HashMap<String, Integer>();
		ab_spec_damage.put("one", 1);
		ab_spec_damage.put("two", 2);

		ab.setRadius(10);
		ab.setName("name");
		ab.setDefault_damage(5);
		ab.setSpecial_damages(ab_spec_damage);
		ab.setGid("this is a gid");
		List<Ability> sc_ab_lst = new ArrayList<Ability>();
		sc_ab_lst.add(ab);

		fromClient.setGid("thisisanid");
		fromClient.setImageid("imgtest.png");
		fromClient.setAbilities(sc_ab_lst);
		fromClient.setMaxhp(999);
		fromClient.setRadius(10);
		fromClient.setPlacement_cost(5);

		ShipClass fromServer = ShipClass
				.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/ShipClassFromServer.json"));

		assertEquals(fromServer.getAbilities(), fromClient.getAbilities());
		assertEquals(fromServer.getGid(), fromClient.getGid());
		assertEquals(fromServer.getImageid(), fromClient.getImageid());
		assertEquals(fromServer.getMaxhp(), fromClient.getMaxhp());
		assertEquals(fromServer.getPlacement_cost(),
				fromClient.getPlacement_cost());
		assertEquals(fromServer.getRadius(), fromClient.getRadius());
		assertEquals(fromServer.getTypes(), fromClient.getTypes());
	}

	public void testAssets() {
		Ability ab = new Ability();

		HashMap<String, Integer> ab_spec_damage = new HashMap<String, Integer>();
		ab_spec_damage.put("one", 1);
		ab_spec_damage.put("two", 2);

		ab.setRadius(10);
		ab.setName("name");
		ab.setDefault_damage(5);
		ab.setSpecial_damages(ab_spec_damage);
		ab.setGid("this is a gid");
		
		ShipClass sc = new ShipClass();

		// ArrayList<String> sc_ab_lst = new ArrayList<String>();
		// sc_ab_lst.add("this is a gid");

		sc.setGid("thisisanid");
		sc.setImageid("imgtest.png");
		// sc.setAbilities(sc_ab_lst);
		sc.setMaxhp(999);
		sc.setRadius(10);
		sc.setPlacement_cost(5);
		
		AssetImage ai = new AssetImage();
		ai.setFile("YWltZ3Rlc3QucG5n\n");
		ai.setGid("testTheGid");
		
		Assets fromClient = new Assets();

		ArrayList<Ability> as_abl_lst = new ArrayList<Ability>();
		as_abl_lst.add(ab);

		ArrayList<AssetImage> as_ai_lst = new ArrayList<AssetImage>();
		as_ai_lst.add(ai);

		ArrayList<ShipClass> as_sc_lst = new ArrayList<ShipClass>();
		as_sc_lst.add(sc);

		fromClient.setWidth(12);
		fromClient.setHeight(53);
		
		Assets fromServer = Assets.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/AssetsFromServer.json"));
	
		assertEquals(fromServer.getAbilities(), fromClient.getAbilities());
		assertEquals(fromServer.getWidth(), fromClient.getWidth());
		assertEquals(fromServer.getHeight(), fromClient.getHeight());
		assertEquals(fromServer.getImages(), fromClient.getImages());
		assertEquals(fromServer.getShip_classes(), fromClient.getShip_classes());
	}

	
}
