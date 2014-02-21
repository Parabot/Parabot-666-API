package org.rev666.api.methods;

import org.rev666.Loader;
import org.rev666.accessors.Client;
import org.rev666.api.wrappers.scene.Tile;
import org.rev666.api.wrappers.scene.TilePath;

/**
 * 
 * @author Everel
 *
 */
public class Walking {
	
	public static boolean isDestinationSet() {
		final Client client = Loader.getClient();
		return client.getDestinationX() > 0 && client.getDestinationY() > 0;
	}
	
	public static Tile getDestination() {
		final Client client = Loader.getClient();
		if(!isDestinationSet()) {
			return new Tile(-1, -1);
		}
		return new Tile(client.getBaseX() + client.getDestinationX(), client.getBaseY() + client.getDestinationY());
	}
	
	public static TilePath getPath(final Tile[] tileArray) {
		return new TilePath(tileArray);
	}
	
	/**
	 * Determines if bot is ready to click next tile
	 * @return whether bot is ready to click next tile
	 */
	public static final boolean readyForNextTile() {
		return !isMoving() || !isDestinationSet() || getDestination().distanceTo() < 5;
	}
	
	/**
	 * Determines if player is walking
	 * @return whether player is walking
	 */
	public static final boolean isMoving() {
		return Players.getLocal().isWalking();
	}

}
