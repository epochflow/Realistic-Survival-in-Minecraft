package epochflow.plugin.rsim.gui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import epochflow.plugin.rsim.util.Net;

public abstract class GUIItem extends ItemStack {
	
	// delegate 인터페이스
	public interface ExecuteAction{
		public void Action(Player player);
	}
	
	protected ExecuteAction ea = null;
	
	public GUIItem setAction(ExecuteAction action)
	{
		ea = action;
		return this;
	}
	
	public void executeAction(Player player)
	{
		if (ea != null)
			ea.Action(player);	
	}
	
	protected List<String> getLoreSplit (@Nullable String lores)
	{
		if (lores == null) return null;
		List<String> lore = new ArrayList<>();
		String[] splits = lores.split("::");
		for (String s : splits)
			lore.add(s);
		return lore;
	}
	
	protected String getPlayerTextures(String name)
	{
		try {
	        String result = Net.getURLContent("https://api.mojang.com/users/profiles/minecraft/" + name);
	        Gson g = new Gson();
	        JsonObject obj = g.fromJson(result, JsonObject.class);
	        String uid = obj.get("id").toString().replace("\"","");
	        String signature = Net.getURLContent("https://sessionserver.mojang.com/session/minecraft/profile/" + uid);
	        obj = g.fromJson(signature, JsonObject.class);
	        return obj.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
	    } catch (Exception ignored){ }
	    return null;
	}	
	
	public abstract GUIItem getItem(Player player);
}
