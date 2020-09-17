package epochflow.plugin.rsim.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 동적인 아이템 (스킬, 정보 등) 을 만들 때 사용합니다.
 */
public class GUIDynamicItem extends GUIItem {

	// delegate 인터페이스
	public interface DynamicFunc<T>{
		public T Func(Player player);
	}
	
	private String name = null;
	private DynamicFunc<String>[] nameFuncs = null;
	private String lores = null;
	private DynamicFunc<String>[] loresFuncs = null;
	
	/**
	 * 동적인 아이템 타입이며 동적인 아이템은 {@link GUIStaticItem} 을 사용<p>
	 */
	public GUIDynamicItem(Material mat)
	{
		setAmount(1);
		setType(mat);
		ItemMeta meta = getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
		setItemMeta(meta);
	}
	
	/**
	 * @param name - @v 로 funcs 의 반환값을 순서대로 넣습니다.
	 */
	@SafeVarargs
	public final GUIDynamicItem setName(String name, DynamicFunc<String>... funcs)
	{
		this.name = name;
		nameFuncs = funcs;
		return this;
	}
	
	@SafeVarargs
	public final GUIDynamicItem setLores(String lores, DynamicFunc<String>... funcs)
	{
		this.lores = lores;
		loresFuncs = funcs;
		return this;
	}
	
	@Override
	public GUIItem getItem(Player player) {
		ItemMeta meta = getItemMeta();
		if (name != null)
			meta.setDisplayName(replaceValue(player, name, nameFuncs));
		if (lores != null)
			meta.setLore(getLoreSplit(replaceValue(player, lores, loresFuncs)));
		setItemMeta(meta);
		return this;
	}
	
	@SafeVarargs
	private final String replaceValue(Player player, String string, DynamicFunc<String>... funcs)
	{
		for (DynamicFunc<String> f : funcs)
			string = string.replaceFirst("@v", f.Func(player));
		return string;
	}

}
