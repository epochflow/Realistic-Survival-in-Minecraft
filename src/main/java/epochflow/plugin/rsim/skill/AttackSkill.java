package epochflow.plugin.rsim.skill;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import epochflow.plugin.rsim.Configs;
import epochflow.plugin.rsim.gui.GUIManager;
import epochflow.plugin.rsim.key.SkillType;
import epochflow.plugin.rsim.key.StatusType;
import epochflow.plugin.rsim.util.AutoYaml;

public class AttackSkill {
	
	public static boolean usePoint(Player player, SkillType type)
	{
		AutoYaml config = Configs.getInstance().getUserConfig(player);
		int currentLevel = config.getInt(type.getUserKey());
		int point = config.getInt(StatusType.POINT.getKey());
		int maxLevel = Configs.getInstance().getSkillConfig().getInt(type.getSkillMaxKey());
		int reqPoint = Configs.getInstance().getSkillConfig().getInt(type.getSkillPointKey());
		
		if (checkPreSkill(type))
		{
			if (currentLevel < maxLevel)
			{
				if (point >= reqPoint)
				{
					config.set(StatusType.POINT.getKey(), point - reqPoint);
					config.set(type.getUserKey(), currentLevel + 1);
					config.save();
					GUIManager.getInstance().showWindow(player, "RSIM_SKILL_WEAPON");
					return true;
				}
				else
					player.sendMessage("포인트가 부족합니다");
			}
			else
				player.sendMessage("이미 최대 레벨입니다!");			
		}
		
		return false;
	}
	
	private static boolean checkPreSkill(SkillType type)
	{
		return true;
	}
	
	public static void applyIncreaseDamage(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Player)
		{
			Player player = (Player) e.getDamager();
			double value = Configs.getInstance().getSkillConfig().getDouble(SkillType.WEAPON_INCREASE_DAMAGE.getSkillValueKey());
			int level = Configs.getInstance().getUserConfig(player).getInt(SkillType.WEAPON_INCREASE_DAMAGE.getUserKey());
			if (level > 0)
				e.setDamage(value * level + e.getDamage());
		}
	}
}
