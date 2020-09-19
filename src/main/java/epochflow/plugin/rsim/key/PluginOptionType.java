package epochflow.plugin.rsim.key;

public enum PluginOptionType implements EnumKey {
	
	OPTION_LEVEL_CONST("option.level.const"),
	OPTION_LEVEL_DIVISION("option.level.division"),
	OPTION_LEVEL_INCREASE("option.level.increase"),
	OPTION_LEVEL_BALANCE("option.level.balance"),
	OPTION_LEVEL_POINT("option.level.point"),
	
	LANGUAGE("lang");

	private String key;
	
	PluginOptionType(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}

}
