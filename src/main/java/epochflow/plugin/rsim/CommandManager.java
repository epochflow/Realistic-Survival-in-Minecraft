package epochflow.plugin.rsim;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;

import epochflow.plugin.rsim.gui.GUIManager;

public class CommandManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (command.getName().equalsIgnoreCase("rsim") && sender instanceof Player)
			{
				if (args.length == 1)
				{
					if (args[0].equals("gui"))
						GUIManager.getInstance().showWindow(player, "RSIM_MAIN");
				}
			}
			
			//어드민 편의 커맨드
			if (command.getName().equalsIgnoreCase("tj") && sender instanceof Player)
			{
				player.setGameMode(GameMode.SURVIVAL);
			}
			
			if (command.getName().equalsIgnoreCase("zm") && sender instanceof Player)
			{
				player.setGameMode(GameMode.CREATIVE);
			}
			
			if (command.getName().equalsIgnoreCase("tm") && sender instanceof Player)
			{
				player.setGameMode(GameMode.SPECTATOR);
			}
			
			if (command.getName().equalsIgnoreCase("vl") && sender instanceof Player)
			{
				double playerHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
				player.setHealth(playerHealth);
				player.setFoodLevel(20);
			}
		}
				
		return false;
	}
}
