package org.rev666;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;
import java.util.Properties;

public class Stub implements AppletStub {
	public static Properties clientParameters = new Properties();
	private static final String IP_ADDRESS = "server.dpkers.net";

	static {
		clientParameters.put("cabbase", "g.cab");
	    clientParameters
	      .put("java_arguments", "-Xmx102m -Dsun.java2d.noddraw=true");
	    clientParameters.put("colourid", "0");
	    clientParameters.put("worldid", "16");
	    clientParameters.put("lobbyid", "15");
	    clientParameters.put("lobbyaddress", IP_ADDRESS);
	    clientParameters.put("demoid", "0");
	    clientParameters.put("demoaddress", "");
	    clientParameters.put("modewhere", "0");
	    clientParameters.put("modewhat", "0");
	    clientParameters.put("lang", "0");
	    clientParameters.put("objecttag", "0");
	    clientParameters.put("js", "1");
	    clientParameters.put("game", "0");
	    clientParameters.put("affid", "0");
	    clientParameters.put("advert", "1");
	    clientParameters.put("settings", 
	      "wwGlrZHF5gJcZl7tf7KSRh0MZLhiU0gI0xDX6DwZ-Qk");
	    clientParameters.put("country", "0");
	    clientParameters.put("haveie6", "0");
	    clientParameters.put("havefirefox", "1");
	    clientParameters.put("cookieprefix", "");
	    clientParameters.put("cookiehost", IP_ADDRESS);
	    clientParameters.put("cachesubdirid", "0");
	    clientParameters.put("crashurl", "");
	    clientParameters.put("unsignedurl", "");
	    clientParameters.put("sitesettings_member", "1");
	    clientParameters.put("frombilling", "false");
	    clientParameters.put("sskey", "");
	    clientParameters.put("force64mb", "false");
	    clientParameters.put("worldflags", "8");
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
			return new URL("http://" + IP_ADDRESS);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	@Override
	public String getParameter(String paramString) {
		return clientParameters.getProperty(paramString);
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
