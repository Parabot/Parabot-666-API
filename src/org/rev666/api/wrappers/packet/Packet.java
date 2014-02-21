package org.rev666.api.wrappers.packet;

import org.rev666.Loader;
import org.rev666.accessors.Menu;
import org.rev666.menu.MenuGen;

/**
 * 
 * @author Everel
 *
 */
public class Packet {
	private int id;
	private long action1;
	private int action2;
	private int action3;
	private String interfaceString;
	
	public Packet(final int id, final long action1, final int action2, final int action3, final String interfaceString) {
		this.id = id;
		this.action1 = action1;
		this.action2 = action2;
		this.action3 = action3;
		this.interfaceString = interfaceString;
	}
	
	public Packet() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setAction1(long action1) {
		this.action1 = action1;
	}
	
	public void setAction2(int action2) {
		this.action2 = action2;
	}
	
	public void setAction3(int action3) {
		this.action3 = action3;
	}
	
	public void setInterfaceAction(String interfaceAction) {
		this.interfaceString = interfaceAction;
	}
	
	public void execute() {
		final Menu menu = MenuGen.menu;
		menu.setInterfaceAction(interfaceString);
		menu.setMenuAction1(action1);
		menu.setMenuAction2(action2);
		menu.setMenuAction3(action3);
		menu.setMenuActionId(id);
		Loader.getClient().doAction(menu);
	}

}
