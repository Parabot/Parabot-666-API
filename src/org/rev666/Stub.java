package org.rev666;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;
import java.util.Properties;

public class Stub implements AppletStub {
	public static Properties client_parameters = new Properties();

	static {
		client_parameters.put("java_arguments",
				"-Xmx102m -Dsun.java2d.noddraw=true");
	}

	@Override
	public void appletResize(int w, int h) {

	}

	@Override
	public AppletContext getAppletContext() {
		return null;
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public URL getCodeBase() {
		try {
			return new URL("http://urltowebclient.com");
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	@Override
	public String getParameter(String paramString) {
		return client_parameters.getProperty(paramString);
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
