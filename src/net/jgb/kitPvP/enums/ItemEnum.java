package net.jgb.kitPvP.enums;

import org.bukkit.Material;

public enum ItemEnum {
	
	BOX("Roleta da Sorte", Material.EMERALD),
	KIT("Menu", Material.CHEST),
	TRAIN("Treinamento", Material.DIAMOND_SWORD);
	
	String displayName;
	Material material;
	
	ItemEnum(String displayName, Material material) {
		this.displayName = displayName;
		this.material = material;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public Material getMaterial() {
		return this.material;
	}
}
