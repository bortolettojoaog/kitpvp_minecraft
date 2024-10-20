package net.jgb.kitPvP.events.inventories;

import java.util.Arrays;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.constants.InventoryConstants;
import net.jgb.kitPvP.enums.WarpStateEnum;
import net.jgb.kitPvP.enums.WorldEnum;
import net.jgb.kitPvP.state.RootState;
import net.jgb.kitPvP.utils.Environment;
import net.jgb.kitPvP.utils.Message;
import net.jgb.kitPvP.utils.customs.CustomRecipe;

@SuppressWarnings("deprecation")
public class WarpConfigInventory implements Listener {
	
	private RootState rootState;
	private CustomRecipe customRecipe;
	private Environment environment;
	private Message message;
	
	public WarpConfigInventory() {
		this.rootState = Main.getRootState();
		this.customRecipe = Main.getUtils().customRecipeUtils();
		this.environment = Main.getUtils().environmentUtils();
		this.message = Main.getUtils().messageUtils();
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (this.rootState == null || event.getInventory().getTitle() == null || !(event.getWhoClicked() instanceof Player)) return;
		
		if (!event.getInventory().getTitle().equals(InventoryConstants.CONFIG_WARP_INVENTORY)) return;
		
		Player player = (Player) event.getWhoClicked();
		
		ItemStack item = event.getCurrentItem();
		
		if (item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) return;
		
		if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("Icon")) {
			event.setCancelled(true);
			
			if (player.getItemOnCursor() != null && !player.getItemOnCursor().getType().equals(Material.AIR)) {
				ItemStack itemOnCursor = player.getItemOnCursor();
				
				item.setType(itemOnCursor.getType());
				
				MaterialData data = new MaterialData(item.getType(), itemOnCursor.getData().getData());
				item.setData(data);
				
				item.setDurability(itemOnCursor.getDurability());
				
				ItemStack changeNameItem = event.getInventory().getItem(13); 
				Optional<String> changeName = changeNameItem.getItemMeta().getLore().stream().filter(lore -> ChatColor.stripColor(lore).contains("Current Name")).findFirst();
				
				WorldEnum world = this.environment.checkPlayerWorld(player);
				
				player.setItemOnCursor(null);
				
				player.updateInventory();
				
				if (!changeName.isPresent()) {
					player.sendMessage(this.message.getErrorPrefix() + " §cError during edit this warp!");
					return;
				}
				
				String warpName = ChatColor.stripColor(changeName.get()).split(":")[1].replace(" ", "");
				
				if (Main.getConfigs().getWarpConfig().getWarpByName(warpName, world) == null) {
					player.sendMessage(this.message.getErrorPrefix() + " §cThe warp '§r" + warpName + "§c' don't exist!");
					return;
				}
				
				Main.getConfigs().getWarpConfig().changeIcon(itemOnCursor, warpName, world);
				player.sendMessage(this.message.getSucessPrefix() + " §aChanged icon from §r{warp} §awarp!".replace("{warp}", warpName));
			}
			
			return;
		}
		
		
		if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("State")) {
			String state = item.getItemMeta().getLore().contains("§e" + WarpStateEnum.PUBLIC.getDisplayName()) ? "§c" + WarpStateEnum.PRIVATE.getDisplayName() : "§e" + WarpStateEnum.PUBLIC.getDisplayName();
			
			ItemStack visibility = this.customRecipe.createItem(Material.ITEM_FRAME, 1, "§eState", Arrays.asList("", "§7§o- Clique com o botão §resquerdo para poder", "  §7§oalterar §aestado §7§odesta warp!", "§7§o- Current State: " + state));
			
			item.setItemMeta(visibility.getItemMeta());
			
			event.setCancelled(true);
			
			player.updateInventory();
		}
	}
}
