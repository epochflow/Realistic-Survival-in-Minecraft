package epochflow.plugin.rsim.gui;

import org.bukkit.Material;
import epochflow.plugin.rsim.Configs;
import epochflow.plugin.rsim.key.StatusType;

// TODO ��� �и�
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
		
		window.setItem(2, new GUIStaticItem("��rƯȭ ���")
				.setItem(Material.BOOK)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_TRAIT")));
		
		window.setItem(4, new GUIStaticItem("��rȯ��")
				.setItem(Material.OAK_SAPLING)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_ENV")));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void TraitGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_TRAIT", "RSiM - Ưȭ", 1);
		
		window.setItem(0, new GUIStaticItem("��r����")
				.setItem(Material.NETHERITE_SWORD)
				.setAction((p) -> GUIManager.getInstance().showWindow(p, "RSIM_TRAIT_WEAPON")));
		window.setItem(1, new GUIStaticItem("��f���").setItem(Material.SHIELD));
		window.setItem(2, new GUIStaticItem("��f����").setItem(Material.ENCHANTED_BOOK));
		window.setItem(4, new GUIStaticItem("��fƯ��").setItem(Material.NETHER_STAR));
		window.setItem(7, new GUIStaticItem("��f����").setItem(Material.CRAFTING_TABLE));
		window.setItem(8, new GUIStaticItem("��f���").setItem(Material.IRON_HOE));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void WeaponGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_TRAIT_WEAPON", "RSiM - ���� Ưȭ", 5);
		
		window.setItem(22, new GUIDynamicItem(Material.EXPERIENCE_BOTTLE)
				.setName("��r���� ����Ʈ @v", (p) -> String.valueOf(Configs.getUserConfig(p).getInt(StatusType.POINT.getKey()))));
		
		GUIManager.getInstance().registerWindow(window);
	}
	
	private static void EnvGUI()
	{
		GUIWindow window = new GUIWindow("RSIM_ENV", "RSiM - ȯ��", 1);
		
		window.setItem(0, new GUIStaticItem("��r�µ�").setItem(Material.IRON_INGOT));
		
		GUIManager.getInstance().registerWindow(window);
	}
}
