package org.rev666.api.wrappers.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.rev666.Loader;
import org.rev666.api.methods.Calculations;
import org.rev666.api.methods.Game;
import org.rev666.api.wrappers.packet.Packet;

/**
 * 
 * @author Everel
 *
 */
public class Tile {
	private int x;
	private int y;
	private int z;
	
	public Tile(int x, int y) {
		this(x, y, 0);
	}
	
	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLocalX() {
		return (x - Loader.getClient().getBaseX());
	}
	
	public int getLocalY() {
		return (y - Loader.getClient().getBaseY());
	}
	
	public int getPlane() {
		return z;
	}
	
	public String toString() {
		return String.format("X: %d, Y: %d, Z: %d", getX(), getY(), getPlane());
	}
	
	public int distanceTo() {
		return (int) Calculations.distanceTo(this);
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Tile) {
			Tile t = (Tile) object;
			return t.getX() == this.getX() && t.getY() == this.getY() && t.getPlane() == this.getPlane();
		}
		return false;
	}
	
	/**
	 * Draws the tile
	 * 
	 * @param graphics
	 * @param color
	 */
	public void draw(final Graphics g, final Color color) {
		final Point pn = Calculations.tileToScreen(this, 0, 0, 0);
		final Point px = Calculations.tileToScreen(new Tile(getX() + 1, getY()), 0, 0, 0);
		final Point py = Calculations.tileToScreen(new Tile(getX(), getY() + 1), 0, 0, 0);
		final Point pxy = Calculations.tileToScreen(new Tile(getX() + 1, getY() + 1), 0, 0, 0);
		if (!Game.isOnScreen(pn) || !Game.isOnScreen(px) || !Game.isOnScreen(py) || !Game.isOnScreen(pxy)) {
			return;
		}
		g.setColor(color);
		g.drawPolygon(new int[] { py.x, pxy.x, px.x, pn.x }, new int[] { py.y, pxy.y, px.y, pn.y }, 4);
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
		g.fillPolygon(new int[] { py.x, pxy.x, px.x, pn.x }, new int[] { py.y, pxy.y, px.y, pn.y }, 4);
	}

	public boolean isOnMinimap() {
		return distanceTo() < 16;
	}

	public void walkMM() {
		Packet packet = new Packet(44, 1, getLocalX(), getLocalY(), "");
		packet.execute();
		
	}


}
