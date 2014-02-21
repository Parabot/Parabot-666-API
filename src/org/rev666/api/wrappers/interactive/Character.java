package org.rev666.api.wrappers.interactive;

import java.awt.Point;

import org.rev666.Loader;
import org.rev666.accessors.Animation;
import org.rev666.accessors.Animator;
import org.rev666.accessors.Client;
import org.rev666.api.methods.Calculations;
import org.rev666.api.wrappers.scene.Tile;

/**
 * 
 * @author Everel
 *
 */
public class Character {
	private org.rev666.accessors.Character accessor;

	public Character(org.rev666.accessors.Character accessor) {
		this.accessor = accessor;
	}

	public int getAnimation() {
		final Animator animator = accessor.getAnimator();
		if (animator == null) {
			return -1;
		}
		final Animation animation = animator.getAnimation();
		if (animation == null) {
			return -1;
		}
		return animation.getId();
	}
	
	public int tileHeight() {
		return Calculations.tileHeight(accessor.getX(), accessor.getY());
	}
	
	public Point toScreen() {
		return Calculations.tileToScreen(this.getLocation());
	}
	
	public int getWalkingQueueIndex() {
		return accessor.getWalkingQueueIndex();
	}
	
	public boolean isWalking() {
		return getWalkingQueueIndex() != 0;
	}
	
	public Tile getLocation() {
		final Client client = Loader.getClient();
		return new Tile(client.getBaseX() + (accessor.getX() >> 9),
				client.getBaseY() + (accessor.getY() >> 9));
	}

}
