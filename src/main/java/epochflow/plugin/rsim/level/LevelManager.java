package epochflow.plugin.rsim.level;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import epochflow.plugin.rsim.Configs;
import epochflow.plugin.rsim.key.LangKey;
import epochflow.plugin.rsim.key.PluginOptionType;
import epochflow.plugin.rsim.key.StatusType;
import epochflow.plugin.rsim.util.AutoYaml;
import epochflow.plugin.rsim.util.Util;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class LevelManager {	
	
	public static boolean addExp(Player player, int addExp)
	{
		// 계산 상수
		int levelBalance = Configs.getInstance().getPluginConfig().getInt(PluginOptionType.OPTION_LEVEL_BALANCE.getKey());
		int levelPoint = Configs.getInstance().getPluginConfig().getInt(PluginOptionType.OPTION_LEVEL_POINT.getKey());
		
		// TODO 유저 특화로 변경
		float userRange = 0.2f;
		float userBalance = 0.5f;
		
		// 경험치 계산
		int exp = addExp * levelBalance;
		int diff = (int)(exp * (1 + userRange) - exp * (1 - userRange)); // 최대 - 최소
		float rand = Util.Random(0, diff);
		int balanceDiff = (int)(diff * userBalance);
		exp = (int)(exp * (1 - userRange) + (rand < balanceDiff ? balanceDiff : rand)); // 최소 + 밸런스 보정
		
		// 레벨 계산
		AutoYaml config = Configs.getInstance().getUserConfig(player);
		int currentExp = config.getInt(StatusType.EXP.getKey()) + exp;
		int currentLevel = config.getInt(StatusType.LEVEL.getKey());
		int point = 0;
		
		// TODO 언어 분리
		player.sendMessage(Util.getLangString(LangKey.LEVEL_GET_EXP.getKey(), String.valueOf(exp)));
		while(true)
		{
			int requireExp = getLevelRequireExp(currentLevel + 1);
			if (currentExp >= requireExp)
			{
				currentLevel++;
				point += levelPoint;
				currentExp -= requireExp;
				player.sendTitle(Util.getLangString(LangKey.LEVEL_UP_TITLE.getKey(), String.valueOf(currentLevel)), 
						Util.getLangString(LangKey.LEVEL_UP_SUBTITLE.getKey(), String.valueOf(levelPoint)), 10, 70, 20);
			}
			else
			{
				String gauge = Util.getStringGauge(currentExp / (float)requireExp, "■", "□");
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
						"§aLv." + currentLevel + " " + currentExp + " " + gauge + " " + requireExp));
				break;
			}
		}
		
		config.set(StatusType.EXP.getKey(), currentExp);
		if (point > 0)
		{
			config.set(StatusType.LEVEL.getKey(), currentLevel);
			config.add(StatusType.POINT.getKey(), point);
			spawnLevelUpFirework(player);
		}
		config.save();
		
		return true;
	}
	
	public static int getLevelRequireExp(int level)
	{
		int levelConst = Configs.getInstance().getPluginConfig().getInt(PluginOptionType.OPTION_LEVEL_CONST.getKey());
		int levelDiv = Configs.getInstance().getPluginConfig().getInt(PluginOptionType.OPTION_LEVEL_DIVISION.getKey());
		int levelInc = Configs.getInstance().getPluginConfig().getInt(PluginOptionType.OPTION_LEVEL_INCREASE.getKey());
		
		double c1 = levelConst * Math.pow(2, level / levelDiv);
		double c2 = 1 + level % (double) levelInc / levelInc;
		double c3 = Math.pow(2, level / levelInc);
		return (int) (c1 * c2 * c3);
	}
	
	public static void spawnLevelUpFirework(Player player)
	{
		Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
		FireworkMeta meta = firework.getFireworkMeta();
		meta.addEffect(FireworkEffect.builder().withColor(Color.GREEN).withColor(Color.YELLOW).withFlicker().build());
		meta.setPower(1);
		firework.setFireworkMeta(meta);
	}
}
