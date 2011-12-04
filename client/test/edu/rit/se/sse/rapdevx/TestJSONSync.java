/* TestJSONSync.java
 * 
 * Creates several DataClass objects to encode in JSON and diff with the same data from server
 */

package edu.rit.se.sse.rapdevx;

import java.util.ArrayList;  //For use as a List
import java.util.HashMap;    // For use as a Map

import edu.rit.se.sse.rapdevx.api.dataclasses.*;

public class TestJSONSync {

	public static void main(String[] args) {
		//DTO_Ability:
		Ability ab = new Ability();

		HashMap<String, Integer> ab_spec_damage = new HashMap<String, Integer>();
		ab_spec_damage.put("one", 1);
		ab_spec_damage.put("two", 2);
		
		ab.setRadius(10);
		ab.setName("name");
		ab.setDefault_damage(5);
		ab.setSpecial_damage(ab_spec_damage);
		ab.setGid("this is a gid");
		
		
		//DTO_AbilityUsoOrder:
		AbilityUseOrder auo = new AbilityUseOrder();
		auo.setSrcID("srcidtest");
		auo.setTargetID("targetidtest");
		auo.setAbility("this is an ability");
		auo.setgID("gidtest");
		
		//DTO_AssetImage
		AssetImage ai = new AssetImage();
		ai.setFile("imgtest.png");
		ai.setGid("testTheGid");

		//Moved DTO_Asset to the bottom since it relys on another class

		//DTO_AttackResults
		AttackResults ar = new AttackResults();
		
		//DTO_MovementOrder
		MovementOrder mo = new MovementOrder();
		
		//DTO_Results
		Results re = new Results();
		
		//DTO_Session
		Session se = new Session();
		
		//DTO_ShipClass:
		ShipClass sc = new ShipClass();
		
		ArrayList<String> sc_ab_lst = new ArrayList<String>();
		sc_ab_lst.add("this is a gid");		
		
		sc.setAbilities(sc_ab_lst);
		sc.setMaxhp(999);
		sc.setRadius(10);
		sc.setPlacement_cost(5);
		
		//DTO_ShipPlacement
		ShipPlacement sp = new ShipPlacement();
		
		//DTO_Status
		Status st = new Status();
		
		//DTO_Unit
		Unit un = new Unit();
		
		//DTO_Assests
		Assets as = new Assets();
		as.setWidth(12);
		as.setHeight(53);
		
		//TODO: HERE
		ArrayList<Ability> as_abl_lst = new ArrayList<Ability>();
		as_abl_lst.add(ab);
		
		ArrayList<AssetImage> as_ai_lst = new ArrayList<AssetImage>();
		as_ai_lst.add(ai);
		
		ArrayList<ShipClass> as_sc_lst = new ArrayList<ShipClass>();
		as_sc_lst.add(sc);
		//as.setAb

		
		//Convert each object to JSON
		ab.toJSON(ab);
		auo.toJSON(auo);
		ai.toJSON(ai);
		as.toJSON(as);
		ar.toJSON(ar);
		mo.toJSON(mo);
		re.toJSON(re);
		se.toJSON(se);
		sc.toJSON(sc);
		sp.toJSON(sp);
		st.toJSON(st);
		un.toJSON(un);
		

	}

}
