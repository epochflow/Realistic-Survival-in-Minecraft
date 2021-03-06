package epochflow.plugin.rsim;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.base.Charsets;

import epochflow.plugin.rsim.key.PluginOptionType;
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
	private AutoYaml langConfig = null;
	private HashMap<Player, AutoYaml> userConfig = new HashMap<>();
	
	public AutoYaml getPluginConfig()
	{
		if (pluginConfig == null)
		{
			File file = new File(Main.getInstance().getDataFolder(), "config.yml");
			if (!file.exists())
			{
				InputStream defaultConfig = Main.getInstance().getResource("config.yml");
				AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig, Charsets.UTF_8), file);
				config.save();
			}
			pluginConfig = AutoYaml.loadConfiguration(file);
			pluginConfig.updateVersion("config.yml");
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
				AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig, Charsets.UTF_8), file);
				config.save();
			}			
			skillConfig = AutoYaml.loadConfiguration(file);
			skillConfig.updateVersion("skills.yml");
		}
		return skillConfig;
	}
	
	public AutoYaml getLangConfig()
	{
		String lang = getPluginConfig().getString(PluginOptionType.LANGUAGE.getKey());
		if (langConfig == null)
		{
			File file = new File(Main.getInstance().getDataFolder() + "/Languages", lang + ".yml");
			if (!file.exists())
			{
				InputStream defaultConfig = Main.getInstance().getResource("Languages/" + lang + ".yml");
				if (defaultConfig != null)
				{
					AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig, Charsets.UTF_8), file);
					config.save();
				}
				else
				{
					Main.getInstance().getLogger().warning(lang + " is a language that does not exist.");
					return null;
				}
			}
			langConfig = AutoYaml.loadConfiguration(file);
			langConfig.updateVersion("Languages/ko-KR.yml");
		}
		return langConfig;
	}
	
	public AutoYaml getUserConfig(Player player)
	{
		if (!userConfig.containsKey(player))
		{
			String path = String.format("UserData/%s.yml", player.getUniqueId().toString());
			File file = new File(Main.getInstance().getDataFolder(), path);
			if (!file.exists())
			{
				InputStream defaultConfig = Main.getInstance().getResource("userdata.yml");
				AutoYaml config = AutoYaml.loadConfiguration(new InputStreamReader(defaultConfig, Charsets.UTF_8),file);
				config.save();
			}
			userConfig.put(player, AutoYaml.loadConfiguration(file));
		}
		return userConfig.get(player);
	}
}
