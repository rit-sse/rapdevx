package edu.rit.se.sse.rapdevx;

import static org.junit.Assert.assertTrue;

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
		Ability ab = new Ability();

		HashMap<String, Integer> ab_spec_damage = new HashMap<String, Integer>();
		ab_spec_damage.put("one", 1);
		ab_spec_damage.put("two", 2);
		
		ab.setRadius(10);
		ab.setName("name");
		ab.setDefault_damage(5);
		ab.setSpecial_damage(ab_spec_damage);
		ab.setGid("this is a gid");
		
		assertTrue(ab.equals(Ability.fromJSON(readFile("client/test/edu/rit/se/sse/rapdevx/json_sync/AbilityFromServer.json"))));
	}
}
