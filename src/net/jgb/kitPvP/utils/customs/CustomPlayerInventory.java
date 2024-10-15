package net.jgb.kitPvP.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.constants.InventoryConstants;
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
	
	public void openMenuInventory(Player player) {	
		if (this.rootState == null) return;
		
		CustomInventory inventory = InventoryConstants.MENU_INVENTORY;
		
		Optional<CustomInventory> customInventory = this.rootState.getInventories().stream().filter(inv -> inv.equals(inventory)).findFirst();
		
		if (customInventory.isPresent()) {
			customInventory.get().openInventory(player);
			return;
		}
		
		this.rootState.addInventory(inventory);
		
		List<Integer> slots = InventoryConstants.MIDDLE_INVENTORY;
		
		inventory.setSlots(slots);
		
		for (int i = 1; i < 60; i++) {
			
			ItemStack item = Main.getUtils().customRecipeUtils().createItem(Material.STONE, 1, "§c§l" + i);
			inventory.addItem(item);
		}
		
		HashMap<Integer, ItemStack> navigation = new HashMap<Integer, ItemStack>();
		
		ItemStack next = this.customRecipe.createItem(ItemEnum.NEXT_PAGE.getMaterial(), 1, "§a" + ItemEnum.NEXT_PAGE.getDisplayName(), 
				Arrays.asList("", "§7§o- Página atual: §r{page}"));
		ItemStack previous = this.customRecipe.createItem(ItemEnum.PREVIOUS_PAGE.getMaterial(), 1, "§c" + ItemEnum.PREVIOUS_PAGE.getDisplayName(),
				Arrays.asList("", "§7§o- Página atual: §r{page}"));
		
		navigation.put(48, next);
		navigation.put(50, previous);
		
		inventory.setUnusableSlots(navigation);
		
		inventory.openInventory(player);
	}
}