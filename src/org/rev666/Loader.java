package org.rev666;

import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JMenuBar;

import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.hooks.HookFile;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev666.accessors.Client;
import org.rev666.debug.BotMenu;
import org.rev666.script.ScriptEngine;

/**
 * 
 * @author Everel
 *
 */
@ServerManifest(author = "Everel", name = "Basic 666 Provider", type = Type.INJECTION, version = 0.1)
public class Loader extends ServerProvider {

	@Override
	public Applet fetchApplet() {
		try {
			final Context context = Context.resolve();
			final ASMClassLoader classLoader = context.getASMClassLoader();
			final Class<?> clientClass = classLoader.loadClass("client");
			//MenuGen.newInstance(classLoader.loadClass("TG")); // TODO: get rid of hardcoded classname
			LoaderApplet applet = new LoaderApplet();
			applet.provide(clientClass);
			applet.init();
			applet.load();
			return applet;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	@Override
	public void injectHooks() {
		AddInterfaceAdapter.setAccessorPackage("org/rev666/accessors/");
		/*Packets.injectIdGetter();
		Packets.injectInvoker();
		Packets.injectDebug();
		Messages.injectListener();*/
		super.injectHooks();

	}

	@Override
	public void addMenuItems(JMenuBar bar) {
		new BotMenu(bar).addItems();
	}

	@Override
	public URL getJar() {
		try {
			return new File("C:/666.jar").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HookFile getHookFile() {
		try {
			return new HookFile(new File("C:/666hooks.xml"), HookFile.TYPE_XML);
		} catch (MalformedURLException e) {
			System.err.println("Error occured while loading hook file");
			throw new RuntimeException(e);
		}
	}

	public static Client getClient() {
		return (Client) Context.resolve().getClient();
	}

	@Override
	public void initScript(Script script) {
		ScriptEngine.getInstance().setScript(script);
		ScriptEngine.getInstance().init();
	}

	@Override
	public void unloadScript(Script script) {
		ScriptEngine.getInstance().unload();
	}

}
