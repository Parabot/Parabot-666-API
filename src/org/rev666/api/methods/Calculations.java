package org.rev666.api.methods;

import java.awt.Point;

import org.rev666.Loader;
import org.rev666.accessors.Client;
import org.rev666.accessors.LDToolkit;
import org.rev666.accessors.RenderData;
import org.rev666.accessors.TileData;
import org.rev666.api.wrappers.scene.Tile;

/**
 * 
 * @author Everel
 *
 */
public class Calculations {

	/**
	 * Calculates distance between local player and given tile
	 * 
	 * @param tile
	 * @return distance between local player and given tile
	 */
	public static final double distanceTo(Tile tile) {
		return distanceBetween(tile, Players.getLocal().getLocation());
	}

	/**
	 * Calculates distance between two given tiles
	 * 
	 * @param tile
	 *            point a
	 * @param tile
	 *            point b
	 * @return distance between two given tiles
	 */
	public static final double distanceBetween(Tile a, Tile b) {
		int x = b.getX() - a.getX();
		int y = b.getY() - a.getY();
		return Math.sqrt((x * x) + (y * y));
	}

	public static int tileHeight(final int x, final int y) {
		int p = 0;
		final int x1 = x >> 9;
		final int y1 = y >> 9;
		final byte[][][] settings = Loader.getClient().getGroundByteArray();
		if (settings != null && x1 >= 0 && x1 < 104 && y1 >= 0 && y1 < 104) {
			if (p <= 3 && (settings[1][x1][y1] & 2) != 0) {
				++p;
			}
			final TileData[] planes = Loader.getClient().getTileData();
			if (planes != null && p < planes.length && planes[p] != null) {
				final int[][] heights = planes[p].getHeights();
				if (heights != null) {
					final int x2 = x & 512 - 1;
					final int y2 = y & 512 - 1;
					final int start_h = heights[x1][y1] * (512 - x2)
							+ heights[x1 + 1][y1] * x2 >> 9;
					final int end_h = heights[x1][1 + y1] * (512 - x2)
							+ heights[x1 + 1][y1 + 1] * x2 >> 9;
					return start_h * (512 - y2) + end_h * y2 >> 9;
				}
			}
		}
		return 0;
	}
	
	// does the final calculation
	public static Point tileToScreen(int x, int y, double offsetX, double offsetY, int height) {
		final Client client = Loader.getClient();
		x -= client.getBaseX();
		y -= client.getBaseY();
		final double worldX = ((x + offsetX) * 512.0D);
		final double worldY = ((y + offsetY) * 512.0D);
		final int z = tileHeight((int) worldX, (int) worldY) + height;
		return worldToScreen((int) worldX, (int) worldY, z);
	}
	
	public static Point tileToScreen(int x, int y, int height) {
		return tileToScreen(x, y, 0.5D, 0.5D, height);
	}
	
	public static Point tileToScreen(final Tile tile, final int height) {
		return tileToScreen(tile.getX(), tile.getY(), height);
	}
	
	public static Point tileToScreen(final Tile tile) {
		return tileToScreen(tile, 0);
	}
	
	public static Point tileToScreen(final Tile tile, double offsetX, double offsetY, int height) {
		return tileToScreen(tile.getX(), tile.getY(), offsetX, offsetY, height);
	}

	public static Point worldToScreen(int x, int y, int z) {
		// paramInt1 = x, paramInt2 = z, paramInt3 = y
		final LDToolkit toolkit = (LDToolkit) Loader.getClient().getToolkit();
		final RenderData renderData = toolkit.getRenderData();
		float _z = renderData.getzOff() + (renderData.getzX() * x + renderData.getzY() * z + renderData.getzZ() * y);
		if ((_z < toolkit.getZMin()) || (_z > toolkit.getZMax())) {
			return new Point(-1, -1);
		}
		int _x = (int)(toolkit.getXMultiplier() * (renderData.getxOff() + (renderData.getxX() * x + renderData.getxY() * z + renderData.getxZ() * y)) / _z);
		int _y = (int)(toolkit.getYMultiplier() * (renderData.getyOff() + (renderData.getyX() * x + renderData.getyY() * z + renderData.getyZ() * y)) / _z);
		if ((_x >= toolkit.getAbsoluteX1()) && (_x <= toolkit.getAbsoluteX2()) && (_y >= toolkit.getAbsoluteY1()) && (_y <= toolkit.getAbsoluteY2())) {
			final int screenX = (_x - toolkit.getAbsoluteX1());
			final int screenY = (_y - toolkit.getAbsoluteY1());
			return new Point(screenX + 4, screenY + 4);
		}
		return new Point(-1, -1);
	}

}
