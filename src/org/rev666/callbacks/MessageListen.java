package org.rev666.callbacks;

import org.rev666.api.events.MessageEvent;
import org.rev666.script.ScriptEngine;


/**
 * 
 * @author Everel
 *
 */
public class MessageListen {

	public static final void messageReceived(int type,
			String someName1, int anInt1, String aString1,
			String message, boolean aBoolean1, String name,
			int anInt2, String aName2) {
		final MessageEvent messageEvent = new MessageEvent(type, name, message);
		ScriptEngine.getInstance().dispatch(messageEvent);
		
	}

}
