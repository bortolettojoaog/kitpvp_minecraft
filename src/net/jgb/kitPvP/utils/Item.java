package net.jgb.kitPvP.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Item {

	public Item() {
		
	}
	
	public boolean checkHeldItem(Player player, Material material) {
		return player.getItemInHand().getType().equals(material);
	}
}
