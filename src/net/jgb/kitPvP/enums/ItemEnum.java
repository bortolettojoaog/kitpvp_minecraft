package net.jgb.kitPvP.enums;

import org.bukkit.Material;

public enum ItemEnum {
	
	BOX("Mystery Box", Material.EMERALD),
	KIT("Kit Menu", Material.CHEST),
	TRAIN("Training Arena", Material.DIAMOND_SWORD),
	NEXT_PAGE("Next Page", Material.ARROW),
	PREVIOUS_PAGE("Previous Page", Material.ARROW);
	
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
