/**
 * 
 */
package edu.rit.se.sse.rapdevx.clientmodels;

import java.util.List;

import edu.rit.se.sse.rapdevx.api.dataclasses.AssetImage;
import edu.rit.se.sse.rapdevx.api.dataclasses.Assets;
import edu.rit.se.sse.rapdevx.api.dataclasses.ShipClass;

/**
 * @author Cody Krieger
 * 
 */
public class AssetLibrary {

	private static Assets	assets;

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

	/**
	 * @return images for the current game
	 */
	public static List<AssetImage> getImages() {
		// return AssetLibrary.assets.getImages();
		return null;
	}

	/**
	 * @return a list of possible ship classes
	 */
	public static List<ShipClass> getShipClasses() {
		// return AssetLibrary.assets.getShipClasses();
		return null;
	}

}
