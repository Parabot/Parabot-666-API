package org.rev666;

import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JMenuBar;

import org.objectweb.asm.tree.ClassNode;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.wrappers.Super;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev666.accessors.Client;
import org.rev666.canvas.SuperCanvas;
import org.rev666.debug.BotMenu;
import org.rev666.inject.Messages;
import org.rev666.inject.Packets;
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
			final Class<?> clientClass = classLoader.loadClass("Loader");
			//MenuGen.newInstance(classLoader.loadClass("TG")); // TODO: get rid of hardcoded classname
			Applet loaderApp = (Applet) clientClass.newInstance();
			loaderApp.init();
			return loaderApp;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	@Override
	public void injectHooks() {

		AddInterfaceAdapter.setAccessorPackage("org/rev666/accessors/");
		Packets.injectIdGetter();
		Packets.injectInvoker();
		Packets.injectDebug();
		Messages.injectListener();
		for (ClassNode node : Context.resolve().getClassPath().classes.values()) {
			if (node.superName.toLowerCase().contains("canvas")) {
				new Super(node.name, "org/rev666/canvas/SuperCanvas")
						.inject();
			}
		}
		super.injectHooks();

	}

	@Override
	public void addMenuItems(JMenuBar bar) {
		new BotMenu(bar).addItems();
	}

	@Override
	public URL getJar() {
		try {
			return new File("666.jar").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public URL getHooks() {
		try {
			return new File("hooks.xml").toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Client getClient() {
		return (Client) SuperCanvas.context.getClient();
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
