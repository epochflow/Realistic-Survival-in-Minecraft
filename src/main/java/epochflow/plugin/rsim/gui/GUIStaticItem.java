package epochflow.plugin.rsim.gui;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

/**
 * ������ ������ (��ư ��) �� ���� �� ����մϴ�.
 */
public class GUIStaticItem extends GUIItem {
	
	private String name = null;
	private String lores = null;
	private boolean isSelfHead = false;
	
	public GUIStaticItem() {}
	
	/**
	 * ������ ������ Ÿ���̸� ������ �ϳ��� �����ؾ��մϴ�.<p>
	 * ������ �������� {@link GUIDynamicItem} �� ���<p>
	 * {@link #setItem()} ������ ����<p>
	 * {@link #setNameHead()} ���� �Ӹ� ����<p>
	 * {@link #setCustomHead()} textures Ŀ���� �Ӹ� ����
	 * 
	 * @param name �� �� ������ ���� �� �ֽ��ϴ�.
	 */
	public GUIStaticItem(String name)
	{
		this.name = name;		
		setAmount(1);
	}
	
	/**
	 * ������ ���¸� �����ϱ����� �����ؾ��մϴ�.
	 */
	public GUIStaticItem setLores(String lores)
	{
		this.lores = lores;
		return this;
	}
	
	public GUIStaticItem setItem(Material mat)
	{
		setType(mat);
		ItemMeta meta = getItemMeta();
		meta.setUnbreakable(true);
		meta.setDisplayName(name);
		meta.setLore(getLoreSplit(lores));
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
		setItemMeta(meta);		
		return this;
	}
	
	public GUIStaticItem setNameHead(String name)
	{
		return setCustomHead(getPlayerTextures(name));
	}
	
	public GUIStaticItem setSelfHead()
	{
		setType(Material.PLAYER_HEAD);
		isSelfHead = true;
		ItemMeta meta = getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(getLoreSplit(lores));
		setItemMeta(meta);
		return this;
	}
	
	public GUIStaticItem setCustomHead(String textures)
	{
		this.setType(Material.PLAYER_HEAD);
        
        SkullMeta meta = (SkullMeta) getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(getLoreSplit(lores));
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        
        profile.getProperties().put("textures", new Property("textures", textures));
        
        try
        {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);            
        }
        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
        {
            error.printStackTrace();
        }
        setItemMeta(meta);
        return this;
	}

	@Override
	public GUIStaticItem getItem(Player player) {
		
		if (isSelfHead)
		{
			SkullMeta meta = (SkullMeta) getItemMeta();
			meta.setOwningPlayer(player);
			setItemMeta(meta);
		}
		return this;
	}
}
