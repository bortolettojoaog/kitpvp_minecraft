package net.jgb.kitPvP.enums;

public enum WarpStateEnum {
	PUBLIC("Public"), PRIVATE("Private");
	
	private String displayName;
	
	WarpStateEnum(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
}
