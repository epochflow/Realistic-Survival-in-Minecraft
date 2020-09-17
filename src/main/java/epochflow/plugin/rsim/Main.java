package epochflow.plugin.rsim;
import org.bukkit.plugin.java.JavaPlugin;

import epochflow.plugin.rsim.gui.GUIDesign;
import epochflow.plugin.rsim.gui.GUIManager;
import epochflow.plugin.rsim.listener.LevelListener;
import epochflow.plugin.rsim.listener.PlayerListener;
import epochflow.plugin.rsim.listener.SkillListener;
import sun.security.krb5.Config;

import org.bukkit.plugin.PluginManager;

public class Main extends JavaPlugin {
	
	private static Main instance;
	public static Main getInstance()
	{
		return instance;
	}
	
	public Main() {
		instance = this;
	}
	
	@Override
	public void onLoad() {		
		// 기본 파일 생성
		if (!getDataFolder().exists())
			getDataFolder().mkdir();		
		new Configs();
		Configs.getInstance().getPluginConfig();
		Configs.getInstance().getSkillConfig();
		Configs.getInstance().getLangConfig();
		
		// GUI 초기화
		new GUIManager();
		GUIDesign.InitGUI();
	}
	
	@Override
	public void onEnable()
	{
		// 이벤트 등록
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new LevelListener(), this);
		pm.registerEvents(new SkillListener(), this);
		pm.registerEvents(GUIManager.getInstance(), this);
		
		getCommand("rsim").setExecutor(new CommandManager());
		getCommand("tj").setExecutor(new CommandManager());
		getCommand("zm").setExecutor(new CommandManager());
		getCommand("tm").setExecutor(new CommandManager());
		getCommand("vl").setExecutor(new CommandManager());
	}
}
