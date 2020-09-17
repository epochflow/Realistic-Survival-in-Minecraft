package epochflow.plugin.rsim.gui;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUIWindow {
	
	private String displayName;
	private String idName;
	private int size;
	private HashMap<Integer, GUIItem> items = new HashMap<>();
	
	public GUIWindow(String idName, String displayName, int line)
	{
		this.idName = idName;
		this.displayName = displayName;
		this.size = line * 9;
	}
	
	public void setItem(int slot, GUIItem item)
	{
		items.put(new Integer(slot), item);
	}
	
	public String getIdName()
	{
		return idName;
	}
	
	public Inventory createInventory(Player player)
	{
		Inventory inventory = Bukkit.createInventory(player, size, displayName);
		
		for (Map.Entry<Integer, GUIItem> item : items.entrySet())
			inventory.setItem(item.getKey().intValue(), item.getValue().getItem(player));
		
		return inventory;
	}
	
	public void executeEvent(Player player, int slot)
	{
		GUIItem item = items.get(new Integer(slot));
		if (item != null)
			item.executeAction(player);
	}
}
