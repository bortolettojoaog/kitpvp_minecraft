package net.jgb.kitPvP.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.entities.WarpEntity;
import net.jgb.kitPvP.utils.Environment;
import net.jgb.kitPvP.utils.Message;

public class WarpsCommand implements CommandExecutor {

	private Message messageUtils;
	private Environment environmentUtils;
	
	public WarpsCommand() {
		this.messageUtils = Main.getUtils().messageUtils();
		this.environmentUtils = Main.getUtils().environmentUtils();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(this.messageUtils.getErrorPrefix() + " �cOnly players can execute �rwarps �ccommand!");
			return false;
		}
		
		Player player = (Player) sender;
		
		if (cmd.equalsIgnoreCase("warps")) {
			if (args.length > 1) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " �c/warps �fhelp�c.");
				return false;
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					player.sendMessage(this.messageUtils.getInformationPrefix() + " �e�lWARPS INFO");
					player.sendMessage("�7� /warps");
					return false;
				} else {
					player.sendMessage(this.messageUtils.getErrorPrefix() + " �c/warps �fhelp�c.");
					return false;
				}
			}
			
			List<WarpEntity> warps = Main.getConfigs().getWarpConfig().getAllWarpsByWorld(this.environmentUtils.checkPlayerWorld(player));
			player.sendMessage(this.messageUtils.getInformationPrefix() + " �e�lWARPS");
			warps.forEach(warp -> player.sendMessage("�7� �e"+warp.getWarpName()));
			return true;
		}
		return false;
	}
}