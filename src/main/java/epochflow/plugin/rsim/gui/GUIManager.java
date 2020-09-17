package epochflow.plugin.rsim.gui;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIManager implements Listener {
	
	private static GUIManager instance = new GUIManager();
	public static GUIManager getInstance()
	{
		return instance;
	}
	
	// GUIWindow
	HashMap<String, GUIWindow> windows = new HashMap<>();
	HashMap<Player, Inventory> lastInventory = new HashMap<>();
	HashMap<Player, String> lastWindow = new HashMap<>();
	
	@EventHandler
	private void onInventoryClick(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		if (e.getInventory() != lastInventory.get(player)) return;
		
		e.setCancelled(true);
		
		ItemStack clickedItem = e.getCurrentItem();
		
		if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
		
		GUIWindow currentWindow = windows.get(lastWindow.get(player));

		currentWindow.executeEvent(player, e.getRawSlot());
	}
	
	public void showWindow(Player player, String idName)
	{		
		if (windows.containsKey(idName))
		{
			GUIWindow window = windows.get(idName);
			lastInventory.put(player, window.createInventory(player));
			lastWindow.put(player, idName);
			player.openInventory(lastInventory.get(player));
		}
	}
	
	public boolean registerWindow(GUIWindow window)
	{
		if (windows.containsKey(window.getIdName()))
			return false;
		windows.put(window.getIdName(), window);
		return true;
	}
	
	public boolean unregisterWindow(String idName)
	{
		if (windows.containsKey(idName))
		{
			windows.remove(idName);
			return true;
		}
		return false;
	}
}
