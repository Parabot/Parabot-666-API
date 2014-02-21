package org.rev666.api.methods;

import org.rev666.Loader;
import org.rev666.api.wrappers.interactive.Player;

/**
 * 
 * @author Everel
 *
 */
public class Players {
	
	/**
	 * Gets current/local/self player
	 * @return your player
	 */
	public static Player getLocal() {
		return new Player(Loader.getClient().getLocalPlayer());
	}

}
