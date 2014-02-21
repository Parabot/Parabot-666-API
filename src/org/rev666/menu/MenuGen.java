package org.rev666.menu;

import java.lang.reflect.Constructor;

import org.rev666.accessors.Menu;

/**
 * 
 * @author Everel
 *
 */
public class MenuGen {
	public static Menu menu = null;

	public static void newInstance(Class<?> menuClass) {
		for (final Constructor<?> constructor : menuClass
				.getDeclaredConstructors()) {
			constructor.setAccessible(true);
			try {
				menu = (Menu) constructor.newInstance(null, null, 0, 0, 0, 0,
						0, 0, false, false, 0, false);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

}
