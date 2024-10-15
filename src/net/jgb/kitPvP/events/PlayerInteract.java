package net.jgb.kitPvP.events;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.constants.ItemConstants;
import net.jgb.kitPvP.constants.PlayerConstants;
import net.jgb.kitPvP.enums.ItemEnum;
import net.jgb.kitPvP.enums.PlayerModeEnum;
import net.jgb.kitPvP.state.RootState;
import net.jgb.kitPvP.utils.CustomInventory;
import net.jgb.kitPvP.utils.CustomPlayerInventory;
import net.jgb.kitPvP.utils.Item;
import net.jgb.kitPvP.utils.Message;

import java.util.Arrays;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
	
	private Item itemUtils;
	private Message messageUtils;
	private CustomPlayerInventory inventoryUtils;
	private RootState rootState;
	
	public PlayerInteract() {
		this.itemUtils = Main.getUtils().itemUtils();
		this.messageUtils = Main.getUtils().messageUtils();
		this.inventoryUtils = Main.getUtils().customPlayerInventoryUtils();
		this.rootState = Main.getRootState();
	}
	
    @EventHandler
    public void onDrinkSoup(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand() == null) return;

        if (this.itemUtils.checkHeldItem(player, Material.MUSHROOM_SOUP)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);

                double regeneration = PlayerConstants.SOUP_REGENETATION;
                double health = player.getHealth();
                double food = player.getFoodLevel();

                if (health >= PlayerConstants.MAX_LIFE_LEVEL && food >= PlayerConstants.MAX_FOOD_LEVEL)
                    return;
                    
                
                if (health >= PlayerConstants.MAX_LIFE_LEVEL && food < PlayerConstants.MAX_FOOD_LEVEL) {
                    player.setFoodLevel(20);
                    player.setItemInHand(new ItemStack(Material.BOWL));
                    return;
                }

                double regeneration_result = (health + regeneration);
                
                player.setHealth(regeneration_result >= PlayerConstants.MAX_LIFE_LEVEL ? PlayerConstants.MAX_LIFE_LEVEL : regeneration_result);

                player.setFoodLevel(20);
                player.setItemInHand(new ItemStack(Material.BOWL));
                
                player.playSound(player.getLocation(), Sound.DRINK, 1.0F, 1.0F);

                return;
            }
        }
    }
    
    @EventHandler
    public void onOpenMenu(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
    	
    	if (player.getItemInHand() == null) return;
    	
    	if (!this.itemUtils.checkHeldItem(player, ItemEnum.KIT.getMaterial())) return;
    	
    	if (!ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName()).equals(ItemEnum.KIT.getDisplayName())) return;
    	
    	if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
    		event.setCancelled(true);
    		this.inventoryUtils.openMenuInventory(player);
    	}
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        Material material = item.getType();
        boolean customItem = item.getItemMeta().getDisplayName() != null &&
        		Arrays.stream(ItemEnum.values())
                      .filter(items -> items.getDisplayName().equals(ChatColor.stripColor(item.getItemMeta().getDisplayName())))
                      .count() >= 1;
        
    	if (customItem) {
    		event.getPlayer().sendMessage(this.messageUtils.getErrorPrefix() + " §cYou cannot drop this item!");
    		event.setCancelled(true);
    		return;
    	}
                
    	boolean soupItem = material.equals(Material.MUSHROOM_SOUP) || material.equals(Material.RED_MUSHROOM) || material.equals(Material.BROWN_MUSHROOM) || material.equals(Material.BOWL);
        if (soupItem) {
            Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    event.getItemDrop().getLocation().getWorld().playEffect(event.getItemDrop().getLocation(), Effect.SMOKE, Material.REDSTONE.ordinal());
                    event.getItemDrop().remove();
                }
            }, 20L * 3);
            
            return;
        }
        
        boolean canDrop = Arrays.stream(ItemConstants.INTERACTIVE_ITEMS).filter(items -> material.name().contains(items)).count() >= 1;
        if (!canDrop)
        	event.getItemDrop().remove();
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();
        Material material = item.getType();
        boolean canPickup = Arrays.stream(ItemConstants.INTERACTIVE_ITEMS).filter(items -> material.name().contains(items)).count() >= 1;
        if (!canPickup)
        	event.setCancelled(true);
        event.setCancelled(false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        player.sendMessage(this.messageUtils.getMessagePrefix() + " §7be very welcome, §f" + player.getName());
        
        this.inventoryUtils.addJoinInventory(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(null);
        player.sendMessage(this.messageUtils.getMessagePrefix() + " §7thanks for playing on our network!");
    }
    
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
    	if (!((event.getEntity()) instanceof Player)) { 
    		event.setCancelled(true);
    		return;
    	}
    	
    	Player entity = (Player) event.getEntity();
    	
    	if (!((event.getDamager()) instanceof Player)) return;
    	Player damager = (Player) event.getDamager();
    	
    	boolean isEntityFighting = this.rootState.comparePlayerMode(entity, PlayerModeEnum.FIGHTING);
    	boolean isDamagerFighting = this.rootState.comparePlayerMode(damager, PlayerModeEnum.FIGHTING);
    	
    	if (!isEntityFighting || !isDamagerFighting) {
    		event.setCancelled(true);
    		return;
    	}
    }
    
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
    	Player player = event.getPlayer();
    	
    	boolean isPlayerFighting = this.rootState.comparePlayerMode(player, PlayerModeEnum.FIGHTING);
    	
    	if ((player.isOp() || 
    			player.hasPermission(PlayerConstants.BUILD_PERMISSION)) && 
    				!isPlayerFighting) return;
    	
    	event.setCancelled(true);
    	return;
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
    	Player player = event.getPlayer();
    	
    	boolean isPlayerFighting = this.rootState.comparePlayerMode(player, PlayerModeEnum.FIGHTING);
    	
    	if ((player.isOp() || 
    			player.hasPermission(PlayerConstants.BUILD_PERMISSION)) && 
    				!isPlayerFighting) return;
    	
    	event.setCancelled(true);
    	return;
    }
    
    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
    	if (event.toWeatherState()) event.setCancelled(true);
    }
    
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {   
    	if (this.rootState == null) return;
    	
        Optional<CustomInventory> customInventory = this.rootState.getInventories().stream()
        		.filter(inventory -> inventory.getInventory().getTitle().equals(event.getInventory().getTitle())).findFirst();

        if (!customInventory.isPresent()) return;
        
        ItemStack item = event.getCurrentItem();

        if (item == null || !item.hasItemMeta()) return;
        
        String displayName = item.getItemMeta().getDisplayName();
        if (displayName == null) return;
        
        event.setCancelled(true);

        if (ChatColor.stripColor(displayName).equals(ItemEnum.NEXT_PAGE.getDisplayName())) {
            customInventory.get().nextPage();
        } else if (ChatColor.stripColor(displayName).equals(ItemEnum.PREVIOUS_PAGE.getDisplayName()))
        	customInventory.get().previousPage();
    }
}