package net.jgb.kitPvP.utils;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.enums.ItemEnum;

public class CustomPlayerInventory {

	public static void cleanPlayerInventory(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
		player.updateInventory();
	}
	
	public static void addJoinInventory(Player player) {
		cleanPlayerInventory(player);
		
        ItemStack chest = CustomRecipes.createItem(Material.CHEST, 1, "§e§l" + ItemEnum.CHEST.getDisplayName(),
        		Arrays.asList("", "§7§o- Utilize o botão §fdireito §7§opara abrir", "  §7§oo menu de seleção de §akits§7§o."));
        
        ItemStack emerald = CustomRecipes.createItem(Material.EMERALD, 1, "§e§l" + ItemEnum.EMERALD.getDisplayName(),
        		Arrays.asList("", "§7§o- Utilize o botão §fdireito §7§opara abrir", "  §7§ouma roleta podendo ganhar §akits§7§o e §befeitos§7§o."));
        
        HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
        enchantments.put(Enchantment.DURABILITY, 10);
        ItemStack sword = CustomRecipes.createItem(Material.DIAMOND_SWORD, 1, "§e§l" + ItemEnum.SWORD.getDisplayName(),
        		Arrays.asList("", "§7§o- Utilize o botão §fdireito §7§opara ser", "  §7§oteleportado para §aarena de treinamento§7§o."),
        		(short)0, enchantments);
        
        player.getInventory().setItem(0, emerald);
		player.getInventory().setItem(4, chest);
		player.getInventory().setItem(8, sword);
		
		player.updateInventory();
	}
}
