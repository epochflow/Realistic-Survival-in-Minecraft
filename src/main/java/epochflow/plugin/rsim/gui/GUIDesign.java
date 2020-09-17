package epochflow.plugin.rsim.gui;

import org.bukkit.Material;
import epochflow.plugin.rsim.Configs;
import epochflow.plugin.rsim.key.LangKey;
import epochflow.plugin.rsim.key.StatusType;
import epochflow.plugin.rsim.util.Util;

// TODO 언어 분리
public class GUIDesign {
	
	public static void InitGUI()
	{
		MainGUI();
		
		EnvGUI();
		
		SkillGUI();
		WeaponGUI();
	}
	
	private static void MainGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_MAIN", Util.getLangString(LangKey.GUI_MAIN_TITLE.getKey()), 1);
		
		window.setItem(2, new GUIStaticItem(Util.getLangString(LangKey.GUI_MAIN_SKILL.getKey()))
				.setItem(Material.BOOK)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_SKILL")));
		
		window.setItem(4, new GUIStaticItem(Util.getLangString(LangKey.GUI_MAIN_ENVIROMENT.getKey()))
				.setItem(Material.OAK_SAPLING)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_ENV")));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void SkillGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_SKILL", Util.getLangString(LangKey.GUI_SKILL_TITLE.getKey()), 1);
		
		window.setItem(0, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_WEAPON.getKey()))
				.setItem(Material.NETHERITE_SWORD)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_SKILL_WEAPON")));
		window.setItem(1, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_ARMOR.getKey())).setItem(Material.SHIELD));
		window.setItem(2, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_MAGIC.getKey())).setItem(Material.ENCHANTED_BOOK));
		window.setItem(4, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_TRAIT.getKey())).setItem(Material.NETHER_STAR));
		window.setItem(6, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_CRAFT.getKey())).setItem(Material.CRAFTING_TABLE));
		window.setItem(7, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_FARM.getKey())).setItem(Material.IRON_HOE));
		window.setItem(8, new GUIStaticItem(Util.getLangString(LangKey.GUI_SKILL_RESOURCE.getKey())).setItem(Material.DIAMOND));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void WeaponGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_SKILL_WEAPON", "RSiM - 전투 특화", 5);
		
		window.setItem(22, new GUIDynamicItem(Material.EXPERIENCE_BOTTLE)
				.setName("§r남은 포인트 @v", (p) -> String.valueOf(Configs.getUserConfig(p).getInt(StatusType.POINT.getKey()))));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void EnvGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_ENV", "RSiM - 환경", 1);
		
		window.setItem(0, new GUIStaticItem("§r온도").setItem(Material.IRON_INGOT));
		
		GUIManager.getInstance().registerWindow(window);
	}
}
