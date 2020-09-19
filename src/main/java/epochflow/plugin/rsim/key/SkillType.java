package epochflow.plugin.rsim.key;

public enum SkillType {
	
	WEAPON_INCREASE_DAMAGE("weapon.increase_damage"),
	WEAPON_ETC("");

	private String key;
	
	SkillType(String string) {
		this.key = string;
	}

	public String getSkillMaxKey() {
		return key + ".max";
	}
	
	public String getSkillValueKey() {
		return key + ".value";
	}
	
	public String getSkillPointKey() {
		return key + ".point";
	}

	public String getUserKey() {
		return "skill." + key;
	}
	
}
