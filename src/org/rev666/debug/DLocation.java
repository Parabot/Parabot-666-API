package org.rev666.debug;

import java.awt.Color;
import java.awt.Graphics;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.rev666.api.methods.Players;
import org.rev666.api.methods.Walking;

/**
 * 
 * @author Everel
 *
 */
public class DLocation extends AbstractDebugger {
	private boolean enabled;

	@Override
	public void paint(Graphics g) {
		Context.resolve().getPaintDebugger().addLine("Location: " + Players.getLocal().getLocation().toString());
		Context.resolve().getPaintDebugger().addLine("Name: " + Players.getLocal().getName());
		Context.resolve().getPaintDebugger().addLine("Animation: " + Players.getLocal().getAnimation());
		Context.resolve().getPaintDebugger().addLine("Destination: " + Walking.getDestination().toString());
		Context.resolve().getPaintDebugger().addLine("Walking: " + Walking.isMoving());
		Players.getLocal().getLocation().draw(g, Color.red);
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void toggle() {
		enabled = !enabled;
	}

}
