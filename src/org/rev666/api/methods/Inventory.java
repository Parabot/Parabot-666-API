package org.rev666.api.methods;

import org.parabot.environment.api.utils.Time;
import org.rev666.api.wrappers.packet.Packet;

/**
 * 
 * @author Everel
 *
 */
public class Inventory {
	
	/**
	 * Clears the inventory
	 */
	public static void clear() {
		final Packet packet = new Packet();
		packet.setAction1(8);
		packet.setId(1006);
		packet.setAction3(44498944);
		for(int slot = 0; slot < 28; slot++) {
			packet.setAction2(slot);
			Time.sleep(20);
			packet.execute();
		}
	}
	
	

}
