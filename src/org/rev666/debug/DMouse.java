package org.rev666.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev666.canvas.SuperCanvas;

/**
 * 
 * @author Everel
 *
 */
public class DMouse extends AbstractDebugger {
	private boolean enabled;

	@Override
	public void paint(Graphics g) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Mouse Location: [").append(SuperCanvas.getMouseX()).append(", ").append(SuperCanvas.getMouseY()).append("]");
		PaintDebugger.getInstance().addLine(stringBuilder.toString());
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
