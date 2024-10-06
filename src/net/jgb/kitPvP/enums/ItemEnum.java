package net.jgb.kitPvP.enums;

public enum ItemEnum {
	
	EMERALD("Roleta da Sorte"),
	CHEST("Menu"),
	SWORD("Treinamento");
	
	String displayName;
	
	ItemEnum(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
}
