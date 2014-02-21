package org.rev666.debug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.parabot.core.Context;
import org.parabot.core.paint.PaintDebugger;

/**
 * 
 * @author Everel
 * 
 */
public class BotMenu {
	private final PaintDebugger paintDebugger;
	private final ActionListener menuDebugItemListener;
	private JMenuBar bar;

	public BotMenu(final JMenuBar bar) {
		this.bar = bar;
		this.paintDebugger = Context.resolve().getPaintDebugger();
		this.menuDebugItemListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JMenuItem) {
					final JMenuItem item = (JMenuItem) e.getSource();
					toggle(item.getText());
				}

			}
		};
	}

	public void addItems() {
		JMenu debug = new JMenu("Debug");

		debug.add(newDebugItem("Location"));
		paintDebugger.addDebugger("Location", new DLocation());

		debug.add(newDebugItem("Rights"));
		paintDebugger.addDebugger("Rights", new DRights());

		debug.add(newDebugItem("SceneObjects"));
		paintDebugger.addDebugger("SceneObjects", new DObjects());

		debug.add(newDebugItem("Mouse"));
		paintDebugger.addDebugger("Mouse", new DMouse());

		bar.add(debug);
	}

	public final JMenuItem newDebugItem(final String name) {
		final JMenuItem item = new JCheckBoxMenuItem(name);
		item.addActionListener(menuDebugItemListener);
		return item;
	}

	public final void toggle(final String name) {
		paintDebugger.toggle(name);
	}

}
