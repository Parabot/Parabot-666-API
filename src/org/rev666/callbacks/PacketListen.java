package org.rev666.callbacks;

import org.rev666.accessors.Menu;

/**
 * 
 * @author Everel
 *
 */
public class PacketListen {
	
	public static void debug(Menu menu) {
		if(menu == null) {
			return;
		}
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
