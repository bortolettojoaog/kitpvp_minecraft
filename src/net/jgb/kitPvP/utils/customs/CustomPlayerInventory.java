package net.jgb.kitPvP.utils.customs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.constants.InventoryConstants;
import net.jgb.kitPvP.entities.WarpEntity;
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
        		Arrays.asList("", "§7§o- Use the §fright §7§o button to open", "  §7§othe §akits §7§oselection menu."));
        
        ItemStack emerald = this.customRecipe.createItem(ItemEnum.BOX.getMaterial(), 1, "§e§l" + ItemEnum.BOX.getDisplayName(),
        		Arrays.asList("", "§7§o- Use the §fright §7§o button to open", "  §7§oa mystery box that can win §akits§7§o and §bbenefits§7§o."));
        
        HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
        enchantments.put(Enchantment.DURABILITY, 10);
        ItemStack sword = this.customRecipe.createItem(ItemEnum.TRAIN.getMaterial(), 1, "§e§l" + ItemEnum.TRAIN.getDisplayName(),
        		Arrays.asList("", "§7§o- Use the §fright §7§o button to be", "  §7§oteleported to the §atraning arena§7§o."),
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
		
		CustomPaginatedInventory inventory = InventoryConstants.MENU_INVENTORY;
		
		Optional<CustomPaginatedInventory> customInventory = this.rootState.getInventories().stream().filter(inv -> inv.equals(inventory)).findFirst();
		
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
				Arrays.asList("", "§7§o- {currentPage}§r{page}"
						.replace("{currentPage}", Main.getLanguage().CURRENT_PAGE)));
		ItemStack previous = this.customRecipe.createItem(ItemEnum.PREVIOUS_PAGE.getMaterial(), 1, "§c" + ItemEnum.PREVIOUS_PAGE.getDisplayName(),
				Arrays.asList("", "§7§o- {currentPage}§r{page}"
						.replace("{currentPage}", Main.getLanguage().CURRENT_PAGE)));
		
		navigation.put(48, next);
		navigation.put(50, previous);
		
		inventory.setUnusableSlots(navigation);
		
		inventory.openInventory(player);
	}
	
	public void openConfigWarp(Player player, WarpEntity warp) {
		if (this.rootState == null) return;
		
		Inventory inventory = Bukkit.createInventory(player, 27, InventoryConstants.CONFIG_WARP_INVENTORY);
		
		ItemStack block = this.customRecipe.createItem(warp.getIcon().getType(), 1, "§bIcon", Arrays.asList("", "§7§o- Click §rnew item §7§oto change", "  §7§othe §aicon §7§oof this warp!"));
		block.setData(block.getData());
		block.setDurability(block.getDurability());
		
		ItemStack name = this.customRecipe.createItem(Material.NAME_TAG, 1, "§aChange Name", Arrays.asList("", "§7§o- Click with the §rleft §7§obutton to be able to", "  §7§ochange the §aname §7§oof this warp!", "§7§o- {currentName}§e{name}"
				.replace("{currentName}", Main.getLanguage().CURRENT_NAME)
				.replace("{name}", warp.getWarpName())));
		
		ItemStack visibility = this.customRecipe.createItem(Material.ITEM_FRAME, 1, "§eState", Arrays.asList("", "§7§o- Click with the §rleft §7§obutton to be able to", "  §7§ochange the §astate §7§oof this warp!", "§7§o- {currentState}§e{state}"
				.replace("{currentState}", Main.getLanguage().CURRENT_STATE)
				.replace("{state}", warp.getState().getDisplayName())));
		
		inventory.setItem(11, block);
		inventory.setItem(13, name);
		inventory.setItem(15, visibility);
		
		player.openInventory(inventory);
	}
	
	public void openBox(Player player) {
		if (this.rootState == null) return;
		
		CustomPaginatedInventory inventory = InventoryConstants.BOX_INVENTORY;
		
		Optional<CustomPaginatedInventory> customInventory = this.rootState.getInventories().stream().filter(inv -> inv.equals(inventory)).findFirst();
		
		if (customInventory.isPresent()) {
			customInventory.get().openInventory(player);
			return;
		}
		
		this.rootState.addInventory(inventory);
		
		for (int i = 1; i < 200; i++) {
			
			ItemStack item = Main.getUtils().customRecipeUtils().createItem(Material.STAINED_GLASS_PANE, 1, "§c§l" + i);
			inventory.addItem(item);
		}
		
		HashMap<Integer, ItemStack> navigation = new HashMap<Integer, ItemStack>();
		
		ItemStack next = this.customRecipe.createItem(ItemEnum.NEXT_PAGE.getMaterial(), 1, "§a" + ItemEnum.NEXT_PAGE.getDisplayName(), 
				Arrays.asList("", "§7§o- {currentPage}§r{page}"
						.replace("{currentPage}", Main.getLanguage().CURRENT_PAGE)));
		ItemStack previous = this.customRecipe.createItem(ItemEnum.PREVIOUS_PAGE.getMaterial(), 1, "§c" + ItemEnum.PREVIOUS_PAGE.getDisplayName(),
				Arrays.asList("", "§7§o- {currentPage}§r{page}"
						.replace("{currentPage}", Main.getLanguage().CURRENT_PAGE)));
		
		navigation.put(48, next);
		navigation.put(50, previous);
		
		inventory.setUnusableSlots(navigation);
		
		inventory.openInventory(player);
	}
}