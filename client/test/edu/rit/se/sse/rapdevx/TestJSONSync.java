/* TestJSONSync.java
 * 
 * Creates several DataClass objects to encode in JSON and diff with the same data from server
 */

package edu.rit.se.sse.rapdevx;

import java.util.ArrayList;  //For use as a List

import edu.rit.se.sse.rapdevx.api.dataclasses.*;

public class TestJSONSync {

	public static void main(String[] args) {
		//DTO_Ability:
		Ability ab = new Ability();
		
		//DTO_AbilityUsoOrder:
		AbilityUseOrder auo = new AbilityUseOrder();
		
		//DTO_AssetImage
		AssetImage ai = new AssetImage();
		
		//DTO_Assests
		Assets as = new Assets();
		
		//DTO_AttackResults
		AttackResults ar = new AttackResults();
		
		//DTO_MovementOrder
		MovementOrder mo = new MovementOrder();
		
		//DTO_Results
		Results re = new Results();
		
		//DTO_Session
		Session se = new Session();
		
		//DTO_ShipClas:
		ShipClass sc = new ShipClass();
		
		//DTO_ShipPlacement
		ShipPlacement sp = new ShipPlacement();
		
		//DTO_Status
		Status st = new Status();
		
		//DTO_Unit
		Unit un = new Unit();
		
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
