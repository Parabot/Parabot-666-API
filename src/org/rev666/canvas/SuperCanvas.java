package org.rev666.canvas;

import java.awt.AWTEvent;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.parabot.core.Context;
import org.parabot.environment.api.interfaces.Paintable;
import org.rev666.accessors.Client;
import org.rev666.accessors.Menu;
import org.rev666.api.methods.Inventory;
import org.rev666.api.methods.SceneObjects;
import org.rev666.api.wrappers.packet.Packet;
import org.rev666.api.wrappers.scene.SceneObject;
import org.rev666.api.wrappers.scene.Tile;

/**
 * 
 * @author Everel
 * 
 */
public class SuperCanvas extends Canvas {
	private static final long serialVersionUID = -4826920183875473313L;
	public static Context context;
	private static int x;
	private static int y;
	
	private BufferedImage gameBuffer;
	private BufferedImage botBuffer;

	public SuperCanvas() {
		botBuffer = new BufferedImage(765, 503, BufferedImage.TYPE_INT_RGB);
		gameBuffer = new BufferedImage(765, 503, BufferedImage.TYPE_INT_RGB);
		context = Context.resolve();
	}

	public void processEvent(AWTEvent awtevent) {
		if (awtevent instanceof MouseEvent) {
			if (awtevent.getID() == 505) {
				x = -1;
				y = -1;
			} else {
				x = ((MouseEvent) awtevent).getX();
				y = ((MouseEvent) awtevent).getY();
			}
		}
		if (awtevent instanceof KeyEvent) {
			final KeyEvent keyEvent = (KeyEvent) awtevent;
			if (keyEvent.getKeyCode() == KeyEvent.VK_0) {
				final Client client = (Client) context.getClient();
				final Menu menu = client.getMenu();
				if (menu != null) {
					final StringBuilder builder = new StringBuilder();
					builder.append("ID: ").append(menu.getMenuActionId())
							.append(" Action1: ").append(menu.getMenuAction1())
							.append(" Action2: ").append(menu.getMenuAction2())
							.append(" Action3: ").append(menu.getMenuAction3())
							.append(" String: ")
							.append(menu.getInterfaceAction());
					System.out.println(builder.toString());
				}
			}
			if (keyEvent.getKeyCode() == KeyEvent.VK_1) {
				/*final Client client = (Client) context.getClient();
				System.out.println(client.getGround());
				final Ground[][] groundArr = client.getGround()[0];
				for(int x = 0; x < 104; x++) {
					for(int y = 0; y < 104; y++) {
						final Object ground = groundArr[x][y].getObject();
						if(ground != null) {
							System.out.println(x+":"+y);
						}
					}
				}*/
				final Tile location = new Tile(3181, 3376, 0);
				for(final SceneObject sceneObject : SceneObjects.getObjects()) {
					if(location.equals(sceneObject.getLocation())) {
						//System.out.println(sceneObject.getHash());
						//System.out.println(System.identityHashCode(sceneObject));
						Packet packet = new Packet(45, sceneObject.getActionKey(), 53, 48, "<col=00ffff>Rocks");
						packet.execute();
					}
				}
				System.out.println(SceneObjects.getObjects().length);
			}
			if (keyEvent.getKeyCode() == KeyEvent.VK_2) {
				Inventory.clear();
			}
		}
		if (awtevent instanceof FocusEvent) {
			final FocusEvent focusEvent = (FocusEvent) awtevent;
			if (focusEvent.getID() == FocusEvent.FOCUS_LOST) {
				return;
			}
		}
		super.processEvent(awtevent);

	}

	@Override
	public Graphics getGraphics() {
		Graphics render = botBuffer.getGraphics();
		render.drawImage(gameBuffer, 0, 0, null);
		for (final Paintable p : context.getPaintables()) {
			p.paint(render);
		}
		context.getPaintDebugger().debug(render);
		render.dispose();
		Graphics g = super.getGraphics();
		try {
			g.drawImage(botBuffer, 0, 0, null);
		} catch (NullPointerException ignored) {

		}
		return gameBuffer.getGraphics();
	}

	public static int getMouseX() {
		return x;
	}

	public static int getMouseY() {
		return y;
	}

}
