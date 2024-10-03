package net.jgb.kitPvP.enums;

public enum ItemNames {
	
	EMERALD("Roleta da Sorte"),
	CHEST("Menu"),
	SWORD("Treinamento");
	
	String displayName;
	
	ItemNames(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
}
