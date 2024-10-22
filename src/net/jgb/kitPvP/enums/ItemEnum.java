package net.jgb.kitPvP.enums;

import org.bukkit.Material;

import net.jgb.kitPvP.Main;

public enum ItemEnum {
	
	BOX(Main.getLanguage().BOX_ITEM, Material.EMERALD),
	KIT(Main.getLanguage().KIT_ITEM, Material.CHEST),
	TRAIN(Main.getLanguage().TRAIN_ITEM, Material.DIAMOND_SWORD),
	NEXT_PAGE(Main.getLanguage().NEXT_PAGE_INVENTORY, Material.ARROW),
	PREVIOUS_PAGE(Main.getLanguage().PREVIOUS_PAGE_INVENTORY, Material.ARROW);
	
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
