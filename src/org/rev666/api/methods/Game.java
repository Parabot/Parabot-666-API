package org.rev666.api.methods;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Everel
 *
 */
public class Game {
	public static final Rectangle GAME_SCREEN = new Rectangle(4, 4, 515, 335);
	
	/**
	 * Determines if point is on screen
	 * @param p
	 * @return <b>true</b> if point is on screen, otherwise <b>false</b>.
	 */
	public static boolean isOnScreen(final Point p) {
		return GAME_SCREEN.contains(p);
	}

}
