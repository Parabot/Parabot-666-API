package org.rev666.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev666.api.methods.SceneObjects;
import org.rev666.api.wrappers.scene.SceneObject;

/**
 * 
 * @author Everel
 *
 */
// TODO
public class DObjects extends AbstractDebugger {

	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		for(final SceneObject sceneObject : SceneObjects.getObjects()) {
			System.out.println(String.format("Tile: %s ID: %d", sceneObject.getLocation().toString(), sceneObject.getId()));
		}
	}

}
