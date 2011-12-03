/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;

/**
 * @author Cody Krieger
 * 
 */
public class AssetLibrary {

	private static Assets	assets;

	// private static Vector<AssetImage> images = new Vector<AssetImage>();
	// private static Vector<ShipClass> shipClasses = new Vector<ShipClass>();

	/**
	 * @return the assets
	 */
	public static Assets getAssets() {
		return assets;
	}

	/**
	 * @param assets
	 *            the assets to set
	 */
	public static void setAssets(Assets assets) {
		AssetLibrary.assets = assets;
	}

}
