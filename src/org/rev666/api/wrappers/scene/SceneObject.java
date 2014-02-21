package org.rev666.api.wrappers.scene;

import org.rev666.Loader;
import org.rev666.accessors.Client;
import org.rev666.accessors.InteractiveObject;
import org.rev666.api.methods.Calculations;
import org.rev666.api.wrappers.packet.Packet;

/**
 * 
 * @author Everel
 *
 */
public class SceneObject {
	public static final int TYPE_INTERACTIVE = 0;
	
	private org.rev666.accessors.Animable accessor;
	private int x;
	private int y;
	private Tile cached ;
	private long actionKey;
	private int type;
	
	public SceneObject(org.rev666.accessors.Animable accessor, int x, int y, int type) {
		this.accessor = accessor;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Tile getLocation() {
		if(cached == null) {
			final Client client = Loader.getClient();
			cached = new Tile(client.getBaseX() + x, client.getBaseY() + y);
		}
		return cached;
	}
	
	public int getId() {
		try {
			return ((InteractiveObject) accessor).method57((byte) 115);
		} catch(AbstractMethodError e) {
			return -1;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public long getActionKey() {
		if(actionKey == 0L) {
			actionKey = Loader.getClient().getActionKey(accessor, x, y);
		}
		return actionKey;
	}

	public int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}
	
	public void interact(int id) {
		Packet packet = new Packet(id, getActionKey(), getX(), getY(), "<col=00ffff>Rocks");
		packet.execute();
	}
	

}
