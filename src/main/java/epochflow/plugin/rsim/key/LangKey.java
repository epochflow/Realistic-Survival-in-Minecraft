package epochflow.plugin.rsim.key;

public enum LangKey implements EnumKey {
	
	LEVEL_GET_EXP("level.get_exp"),
	LEVEL_UP_TITLE("level.up_title"),
	LEVEL_UP_SUBTITLE("level.up_subtitle"),
	
	GUI_MAIN_TITLE("gui.main.title"),
	GUI_MAIN_SKILL("gui.main.skill"),
	GUI_MAIN_ENVIROMENT("gui.main.enviroment"),
	
	GUI_SKILL_TITLE("gui.skill.title"),
	GUI_SKILL_WEAPON("gui.skill.weapon"),
	GUI_SKILL_ARMOR("gui.skill.armor"),
	GUI_SKILL_MAGIC("gui.skill.magic"),
	GUI_SKILL_TRAIT("gui.skill.trait"),
	GUI_SKILL_FARM("gui.skill.farm"),
	GUI_SKILL_CRAFT("gui.skill.craft"),
	GUI_SKILL_RESOURCE("gui.skill.resource");

	private String key;
	
	LangKey(String string) {
		this.key = string;
	}

	@Override
	public String getKey() {
		return key;
	}

}
