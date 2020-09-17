package epochflow.plugin.rsim.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import epochflow.plugin.rsim.level.LevelManager;

public class LevelListener implements Listener {

	@EventHandler
	private void onPlayerExpChange(PlayerExpChangeEvent e)
	{
		if (e.getAmount() > 0)
		{
			Player player = (Player) e.getPlayer();
			LevelManager.addExp(player, e.getAmount());
		}
	}
}
