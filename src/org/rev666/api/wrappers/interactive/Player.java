package org.rev666.api.wrappers.interactive;

/**
 * 
 * @author Everel
 *
 */
public class Player extends Character {
	private org.rev666.accessors.Player accessor;
	
	public Player(org.rev666.accessors.Player accessor) {
		super(accessor);
		this.accessor = accessor;
	}
	
	public String getName() {
		return accessor.getName();
	}

}
