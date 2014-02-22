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
		Context.getInstance().getPaintDebugger().addLine("Location: " + Players.getLocal().getLocation().toString());
		Context.getInstance().getPaintDebugger().addLine("Name: " + Players.getLocal().getName());
		Context.getInstance().getPaintDebugger().addLine("Animation: " + Players.getLocal().getAnimation());
		Context.getInstance().getPaintDebugger().addLine("Destination: " + Walking.getDestination().toString());
		Context.getInstance().getPaintDebugger().addLine("Walking: " + Walking.isMoving());
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
