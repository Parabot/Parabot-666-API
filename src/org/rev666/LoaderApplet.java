package org.rev666;

import java.applet.Applet;

import org.parabot.core.Context;
import org.rev666.accessors.Client;

/**
 * 
 * @author Everel
 *
 */
public class LoaderApplet extends Applet {
	private static final long serialVersionUID = -5804372016252617656L;
	private Class<?> client;

	@Override
	public void init() {
		Stub stub = new Stub();
		setStub(stub);
	}

	public void provide(Class<?> clazz) {
		try {
			client = clazz;
			clazz.getSuperclass().getDeclaredMethod("provideLoaderApplet", Applet.class).invoke(null, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			Object object = client.newInstance();
			Applet applet = (Applet) object;
			final Client client = (Client) object;
			Context.resolve().setClientInstance(client);
			applet.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}