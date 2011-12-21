/* TestJSONSync.java
 * 
 * Creates several DataClass objects to encode in JSON and diff with the same data from server
 */

package edu.rit.se.sse.rapdevx;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import edu.rit.se.sse.rapdevx.api.dataclasses.Ability;
import edu.rit.se.sse.rapdevx.api.dataclasses.AbilityUseOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.AssetImage;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.AttackResults;
import edu.rit.se.sse.rapdevx.api.dataclasses.MovementOrder;
import edu.rit.se.sse.rapdevx.api.dataclasses.Results;
import edu.rit.se.sse.rapdevx.api.dataclasses.Session;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipPlacement;
import edu.rit.se.sse.rapdevx.api.dataclasses.Status;
import edu.rit.se.sse.rapdevx.api.dataclasses.Unit;

public class TestJSONSync {

	public static void main(String[] args) {
		// DTO_Ability:
		Ability ab = new Ability();

		HashMap<String, Integer> ab_spec_damage = new HashMap<String, Integer>();
		ab_spec_damage.put("one", 1);
		ab_spec_damage.put("two", 2);

		ab.setRadius(10);
		ab.setName("name");
		ab.setDefaultDamage(5);
		ab.setSpecialDamages(ab_spec_damage);
		ab.setGid("this is a gid");

		// DTO_AbilityUsoOrder:
		AbilityUseOrder auo = new AbilityUseOrder();
		auo.setSrcid("srcidtest");
		auo.setTargetid("targetidtest");
		auo.setAbility("this is an ability");
		auo.setgid("gidtest");

		// DTO_AssetImage
		AssetImage ai = new AssetImage();
		ai.setFile("YWltZ3Rlc3QucG5n\n");
		ai.setGid("testTheGid");

		// Moved DTO_Asset to the bottom since it relys on another class

		// DTO_AttackResults
		AttackResults ar = new AttackResults();
		ArrayList<AbilityUseOrder> ar_auo_lst = new ArrayList<AbilityUseOrder>();
		ar_auo_lst.add(auo);

		ar.setResults(ar_auo_lst);

		// DTO_MovementOrder
		MovementOrder mo = new MovementOrder();

		LinkedList<Point> mo_res_lst = new LinkedList<Point>();
		mo_res_lst.add(new Point(23, 62));

		mo.setUnitid("thisisagid");
		mo.setGid("thisisagid");
		mo.setPath(mo_res_lst);

		// DTO_Results
		Results re = new Results();

		// DTO_Session
		Session se = new Session();

		Map<String, LinkedList<Hashtable<Integer, Integer>>> re_res = new Hashtable<String, LinkedList<Hashtable<Integer, Integer>>>();

		LinkedList<Hashtable<Integer, Integer>> re_res_tpl_lst = new LinkedList<Hashtable<Integer, Integer>>();
		Hashtable<Integer, Integer> tmp = new Hashtable<Integer, Integer>();

		tmp.put(23, 62);
		re_res_tpl_lst.push(tmp);

		re_res.put("thisisanid", re_res_tpl_lst);

		re.setResults(re_res);

		// DTO_ShipClass:
		ShipClass sc = new ShipClass();

		// ArrayList<String> sc_ab_lst = new ArrayList<String>();
		// sc_ab_lst.add("this is a gid");

		sc.setGid("thisisanid");
		sc.setImageid("imgtest.png");
		// sc.setAbilities(sc_ab_lst);
		sc.setMaxhp(999);
		sc.setRadius(10);
		sc.setPlacement_cost(5);

		// DTO_ShipPlacement
		ShipPlacement sp = new ShipPlacement();
		sp.setClassid("thisisanid");
		sp.setX(23);
		sp.setY(62);

		// DTO_Status
		Status st = new Status();
		LinkedList<String> st_lst = new LinkedList<String>();

		st.setMe(22);
		st.setPhase("phase2");
		st.setTurn(3);
		st.setPlayer_list(st_lst);

		// DTO_Unit
		Unit un = new Unit();

		// DTO_Assests
		Assets as = new Assets();

		ArrayList<Ability> as_abl_lst = new ArrayList<Ability>();
		as_abl_lst.add(ab);

		ArrayList<AssetImage> as_ai_lst = new ArrayList<AssetImage>();
		as_ai_lst.add(ai);

		ArrayList<ShipClass> as_sc_lst = new ArrayList<ShipClass>();
		as_sc_lst.add(sc);

		as.setWidth(12);
		as.setHeight(53);

		// Convert each object to JSON
		ab.toJSON(ab);
		auo.toJSON(auo);
		ai.toJSON(ai);
		as.toJSON(as);
		ar.toJSON(ar);
		mo.toJSON(mo);
		re.toJSON(re);
		sc.toJSON(sc);
		sp.toJSON(sp);
		st.toJSON(st);
		un.toJSON(un);
	}

}
