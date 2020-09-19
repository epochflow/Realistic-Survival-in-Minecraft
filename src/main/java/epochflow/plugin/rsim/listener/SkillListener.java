package epochflow.plugin.rsim.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import epochflow.plugin.rsim.skill.AttackSkill;

public class SkillListener implements Listener {

	@EventHandler
	private void onEntityDamage(EntityDamageByEntityEvent e)
	{
		AttackSkill.applyIncreaseDamage(e);
	}
}
