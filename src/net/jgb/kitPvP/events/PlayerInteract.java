package net.jgb.kitPvP.events;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.constants.ItemConstants;
import net.jgb.kitPvP.enums.ItemEnum;
import net.jgb.kitPvP.utils.CustomPlayerInventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand() == null) return;

        if (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);

                double regeneration = 7;
                double health = player.getHealth();
                double food = player.getFoodLevel();

                if (health >= 20 && food >= 20) {
                    return;
                } else if (health >= 20 && food < 20) {
                    player.setFoodLevel(20);
                    player.setItemInHand(new ItemStack(Material.BOWL));
                    return;
                }

                if ((health + regeneration) >= 20) {
                    player.setHealth(20);
                } else {
                    player.setHealth(health + regeneration);
                }

                player.setFoodLevel(20);
                player.setItemInHand(new ItemStack(Material.BOWL));

                return;
            }
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        Material material = item.getType();
        boolean customItem = item.getItemMeta().getDisplayName() != null &&
        		Arrays.stream(ItemEnum.values())
                      .filter(items -> items.getDisplayName().equals(ChatColor.stripColor(item.getItemMeta().getDisplayName())))
                      .count() >= 1;
        
    	if (customItem) {
    		event.getPlayer().sendMessage(Main.getMessage().getErrorPrefix() + " §cYou cannot drop this item!");
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
    public void pickup(PlayerPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();
        Material material = item.getType();
        boolean canPickup = Arrays.stream(ItemConstants.INTERACTIVE_ITEMS).filter(items -> material.name().contains(items)).count() >= 1;
        if (!canPickup)
        	event.setCancelled(true);
        event.setCancelled(false);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        player.sendMessage(Main.getMessage().getMessagePrefix() + " §7be very welcome, §f" + player.getName());
        
        CustomPlayerInventory.addJoinInventory(player);
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(null);
        player.sendMessage(Main.getMessage().getMessagePrefix() + " §7thanks for playing on our network!");
    }
}