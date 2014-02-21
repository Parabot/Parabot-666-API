package org.rev666.api.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.parabot.environment.api.utils.Filter;
import org.rev666.Loader;
import org.rev666.accessors.AnimableNode;
import org.rev666.accessors.Client;
import org.rev666.accessors.Ground;
import org.rev666.api.wrappers.scene.SceneObject;

/**
 * 
 * @author Everel
 *
 */
public class SceneObjects {

	private static final Comparator<SceneObject> NEAREST_SORTER = new Comparator<SceneObject>() {

		@Override
		public int compare(SceneObject n1, SceneObject n2) {
			return n1.distanceTo() - n2.distanceTo();
		}

	};

	private static final Filter<SceneObject> ALL_FILTER = new Filter<SceneObject>() {

		@Override
		public boolean accept(SceneObject object) {
			return true;
		}

	};

	public static SceneObject[] getObjects(Filter<SceneObject> filter) {
		final Client client = Loader.getClient();
		final Ground[][] groundArr = client.getGround()[0];
		final ArrayList<SceneObject> objects = new ArrayList<SceneObject>();
		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				final Ground ground = groundArr[x][y];
				org.rev666.accessors.Animable object = null;
				if (ground == null) {
					continue;
				}
				for (AnimableNode node = ground.getAnimableList(); node != null; node = node
						.getNext()) {
					object = node.getAnimable();
					if (object != null) {
						final SceneObject sceneObject = new SceneObject(object,
								x, y, SceneObject.TYPE_INTERACTIVE);
						if (filter.accept(sceneObject)) {
							objects.add(sceneObject);
						}
					}
				}
			}
		}
		return objects.toArray(new SceneObject[objects.size()]);
	}

	public static SceneObject[] getObjects() {
		return getObjects(ALL_FILTER);
	}

	public static SceneObject[] getNearest() {
		SceneObject[] objects = getObjects(ALL_FILTER);
		Arrays.sort(objects, NEAREST_SORTER);
		return objects;
	}

	public static SceneObject[] getNearest(final int... ids) {
		SceneObject[] objects = getObjects(new Filter<SceneObject>() {

			@Override
			public boolean accept(SceneObject object) {
				for (final int id : ids) {
					if (id == object.getId()) {
						return true;
					}
				}
				return false;
			}

		});
		Arrays.sort(objects, NEAREST_SORTER);
		return objects;
	}

}
