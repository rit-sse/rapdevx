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
	public static Object getImages() {
		return AssetLibrary.assets.getImages();
	}

	/**
	 * @return a list of possible ship classes
	 */
	public static Object getShipClasses() {
		return AssetLibrary.assets.getShipClasses();
	}

}
