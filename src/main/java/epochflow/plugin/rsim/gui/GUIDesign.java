package epochflow.plugin.rsim.gui;

import org.bukkit.Material;
import epochflow.plugin.rsim.Configs;
import epochflow.plugin.rsim.key.StatusType;

// TODO 언어 분리
public class GUIDesign {
	
	public static void InitGUI()
	{
		MainGUI();
		
		EnvGUI();
		
		TraitGUI();
		WeaponGUI();
	}
	
	private static void MainGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_MAIN", "RSiM", 1);
		
		window.setItem(2, new GUIStaticItem("§r특화 기술")
				.setItem(Material.BOOK)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_TRAIT")));
		
		window.setItem(4, new GUIStaticItem("§r환경")
				.setItem(Material.OAK_SAPLING)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_ENV")));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void TraitGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_TRAIT", "RSiM - 특화", 1);
		
		window.setItem(0, new GUIStaticItem("§r전투")
				.setItem(Material.NETHERITE_SWORD)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_TRAIT_WEAPON")));
		window.setItem(1, new GUIStaticItem("§f방어").setItem(Material.SHIELD));
		window.setItem(2, new GUIStaticItem("§f마법").setItem(Material.ENCHANTED_BOOK));
		window.setItem(4, new GUIStaticItem("§f특성").setItem(Material.NETHER_STAR));
		window.setItem(7, new GUIStaticItem("§f제작").setItem(Material.CRAFTING_TABLE));
		window.setItem(8, new GUIStaticItem("§f농사").setItem(Material.IRON_HOE));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void WeaponGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_TRAIT_WEAPON", "RSiM - 전투 특화", 5);
		
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
