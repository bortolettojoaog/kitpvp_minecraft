package net.jgb.kitPvP.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.constants.PlayerConstants;
import net.jgb.kitPvP.entities.WarpEntity;
import net.jgb.kitPvP.enums.WarpStateEnum;
import net.jgb.kitPvP.enums.WorldEnum;
import net.jgb.kitPvP.utils.Environment;
import net.jgb.kitPvP.utils.Message;

public class WarpCommand implements CommandExecutor {

	private Message messageUtils;
	private Environment environmentUtils;
	
	public WarpCommand() {
		this.messageUtils = Main.getUtils().messageUtils();
		this.environmentUtils = Main.getUtils().environmentUtils();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(this.messageUtils.getErrorPrefix() + " §cOnly players can execute §rwarp §ccommand!");
			return false;
		}
		
		Player player = (Player) sender;
		
		if (cmd.equalsIgnoreCase("warp")) {
			if (args.length < 1 || args.length >= 3) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §c/warp §fhelp§c.");
				return false;
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					player.sendMessage(this.messageUtils.getInformationPrefix() + " §e§lWARP INFO");
					player.sendMessage("§7» /warp §rset §e<name>");
					player.sendMessage("§7» /warp §runset §e<name>");
					player.sendMessage("§7» /warp §rconfig §e<name> ");
					player.sendMessage("§7» /warp §e<warp name>");
					return false;
				} else {
					WarpEntity warp = Main.getConfigs().getWarpConfig().getWarpByName(args[0], this.environmentUtils.checkPlayerWorld(player));
					
					if (warp == null) {
						checkSubCommand(player, args[0]);
						return false;
					}
					
					player.sendMessage(this.messageUtils.getSucessPrefix() + " §aTeleported to §r{warp}§a warp!".replace("{warp}", args[0]));
					
					player.teleport(Main.getConfigs().getWarpConfig().getWarpLocation(warp));
					return true;
				}
			}
			
			checkSubSubCommand(player, args[0], args[1]);
		}
		return false;
	}
	
	private boolean checkSubCommand(Player player, String subCommand) {
		if (subCommand.equals("set") || subCommand.equals("unset") || subCommand.equals("config")) {
			player.sendMessage(this.messageUtils.getInformationPrefix() + " §e§lWARP INFO");
			player.sendMessage("§7» /warp §r{subCommand} §e<name>".replace("{subCommand}", subCommand));
		} else {
			player.sendMessage(this.messageUtils.getErrorPrefix() + " §c/warp §fhelp§c.");
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private boolean checkSubSubCommand(Player player, String subCommand, String warpName) {
		WorldEnum playerWorld = this.environmentUtils.checkPlayerWorld(player);
		WarpEntity warp = Main.getConfigs().getWarpConfig().getWarpByName(warpName, playerWorld);
		
		switch(subCommand.toLowerCase()) {
		case "set":
			if (!player.hasPermission(PlayerConstants.SET_WARP_PERMISSION)) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §cYou don't have permission!");
				return false;
			}
			
			if (warp != null) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §cThe warp '§r" + warpName + "§c' already exist!");
				return false;
			}
			
			player.sendMessage(this.messageUtils.getSucessPrefix() + " §aWarp §r{warp} §awas defined!".replace("{warp}", warpName));
			
			ItemStack icon = new ItemStack(Material.REDSTONE_BLOCK);
			
			Main.getConfigs().getWarpConfig().createSection("Warps." + this.environmentUtils.checkPlayerWorld(player) + "." + warpName);
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".world", player.getWorld().getName());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".x", player.getLocation().getX());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".y", player.getLocation().getY());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".z", player.getLocation().getZ());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".pitch", player.getLocation().getPitch());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".yaw", player.getLocation().getYaw());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".icon", icon.getType() + ":" + icon.getDurability() + ":" + icon.getData().getData());
			Main.getConfigs().getWarpConfig().add("Warps." + playerWorld + "." + warpName + ".state", WarpStateEnum.PUBLIC.getDisplayName());
			return true;
		case "unset":
			if (!player.hasPermission(PlayerConstants.UNSET_WARP_PERMISSION)) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §cYou don't have permission!");
				return false;
			}
			
			if (warp == null) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §cThe warp '§r" + warpName + "§c' don't exist!");
				return false;
			}
			
			Main.getConfigs().getWarpConfig().remove("Warps." + this.environmentUtils.checkPlayerWorld(player) + "." + warpName);
			
			player.sendMessage(this.messageUtils.getSucessPrefix() + " §aWarp §r{warp} §awas deleted!".replace("{warp}", warpName));
			return true;
		case "config":
			if (!player.hasPermission(PlayerConstants.CONFIG_WARP_PERMISSION)) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §cYou don't have permission!");
				return false;
			}
			
			if (warp == null) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " §cThe warp '§r" + warpName + "§c' don't exist!");
				return false;
			}
			
			player.sendMessage(this.messageUtils.getSucessPrefix() + " §aConfiguring warp!");
			Main.getUtils().customPlayerInventoryUtils().openConfigWarp(player, warp);
			return true;
		default:
			player.sendMessage(this.messageUtils.getErrorPrefix() + " §c/warp §fhelp§c.");
			return false;
		}
	}
}
