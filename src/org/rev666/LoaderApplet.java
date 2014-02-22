package org.rev666;

import java.applet.Applet;

import org.parabot.core.paint.PaintDebugger;

/**
 * 
 * @author Everel
 *
 */
public class LoaderApplet extends Applet {
	private static final long serialVersionUID = -5804372016252617656L;
	private Class<?> client;
	public static PaintDebugger paintDebugger;

	@Override
	public void init() {
		Stub stub = new Stub();
		setStub(stub);
		paintDebugger = PaintDebugger.getInstance();
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
			applet.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}