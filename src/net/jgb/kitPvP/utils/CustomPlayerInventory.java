package net.jgb.kitPvP.utils;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.enums.ItemEnum;
import net.jgb.kitPvP.enums.PlayerModeEnum;
import net.jgb.kitPvP.state.RootState;

public class CustomPlayerInventory {

	private CustomRecipe customRecipe;
	private RootState rootState;
	
	public CustomPlayerInventory(CustomRecipe customRecipe) {
		this.customRecipe = customRecipe;
		this.rootState = Main.getRootState();
	}
	
	public void cleanPlayerInventory(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
		player.updateInventory();
	}
	
	public void addJoinInventory(Player player) {
		cleanPlayerInventory(player);
		
        ItemStack chest = this.customRecipe.createItem(ItemEnum.KIT.getMaterial(), 1, "§e§l" + ItemEnum.KIT.getDisplayName(),
        		Arrays.asList("", "§7§o- Utilize o botão §fdireito §7§opara abrir", "  §7§oo menu de seleção de §akits§7§o."));
        
        ItemStack emerald = this.customRecipe.createItem(ItemEnum.BOX.getMaterial(), 1, "§e§l" + ItemEnum.BOX.getDisplayName(),
        		Arrays.asList("", "§7§o- Utilize o botão §fdireito §7§opara abrir", "  §7§ouma roleta podendo ganhar §akits§7§o e §befeitos§7§o."));
        
        HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
        enchantments.put(Enchantment.DURABILITY, 10);
        ItemStack sword = this.customRecipe.createItem(ItemEnum.TRAIN.getMaterial(), 1, "§e§l" + ItemEnum.TRAIN.getDisplayName(),
        		Arrays.asList("", "§7§o- Utilize o botão §fdireito §7§opara ser", "  §7§oteleportado para §aarena de treinamento§7§o."),
        			(short)0, enchantments);
        
        player.getInventory().setItem(0, emerald);
		player.getInventory().setItem(4, chest);
		player.getInventory().setItem(8, sword);
		
		if (this.rootState != null)
			this.rootState.updatePlayerMode(player, PlayerModeEnum.HOLDING);
		
		player.updateInventory();
	}
}
