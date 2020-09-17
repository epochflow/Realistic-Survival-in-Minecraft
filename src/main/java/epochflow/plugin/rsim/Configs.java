package epochflow.plugin.rsim;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.bukkit.entity.Player;

import epochflow.plugin.rsim.util.AutoYaml;

public class Configs {
	
	private static Configs instance;
	public static Configs getInstance()
	{
		return instance;
	}
	
	public Configs()
	{
		instance = this;
	}
	
	private AutoYaml pluginConfig = null;
	private AutoYaml skillConfig = null;
	
	public AutoYaml getPluginConfig()
	{
		if (pluginConfig == null)
		{
			File file = new File(Main.getInstance().getDataFolder(), "config.yml");
			if (!file.exists())
			{
				InputStream defaultConfig = Main.getInstance().getResource("config.yml");
				AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig), file);
				config.save();
			}
			pluginConfig = AutoYaml.loadConfiguration(file);
		}
		return pluginConfig;
	}
	
	public AutoYaml getSkillConfig()
	{
		if (skillConfig == null)
		{
			File file = new File(Main.getInstance().getDataFolder(), "skills.yml");
			if (!file.exists())
			{
				InputStream defaultConfig = Main.getInstance().getResource("skills.yml");
				AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig), file);
				config.save();
			}			
			skillConfig = AutoYaml.loadConfiguration(file);
		}
		return skillConfig;
	}
	
	public static AutoYaml getUserConfig(Player player)
	{
		String path = String.format("UserData/%s.yml", player.getUniqueId().toString());
		File file = new File(Main.getInstance().getDataFolder(), path);
		if (!file.exists())
		{
			InputStream defaultConfig = Main.getInstance().getResource("userdata.yml");
			AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig),file);
			config.save();
			return config;			
		}
		return AutoYaml.loadConfiguration(file);
	}
}
