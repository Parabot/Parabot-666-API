package org.rev666.accessors;

public interface Client {
	
	public Menu getMenu();
	
	public Ground[][][] getGround();
	
	public int getBaseX();
	
	public int getBaseY();
	
	public Player getLocalPlayer();
	
	public long getActionKey(Animable animable, int localX, int localY);
	
	public void doAction(Menu menu);
	
	public void setRights(int rights);
	
	public byte[][][] getSettings();

	public byte[][][] getGroundByteArray();

	public TileData[] getTileData();
	
	public Toolkit getToolkit();
	
	public Toolkit getToolkit1();
	
	public Toolkit getToolkit2();
	
	public int getDestinationX();
	
	public int getDestinationY();

}
