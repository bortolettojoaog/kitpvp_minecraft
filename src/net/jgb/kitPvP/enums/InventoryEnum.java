package net.jgb.kitPvP.enums;

public enum InventoryEnum {
	
	KIT("Menu");
	
	String title;
	
	InventoryEnum(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
}
