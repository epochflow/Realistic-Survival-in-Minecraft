package epochflow.plugin.rsim.key;

public enum StatusType implements EnumKey {
	
	EXP("status.exp"),
	
	LEVEL("status.level"),
	
	POINT("status.point");

	private String key;
	
	StatusType(String key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key;
	}
}
