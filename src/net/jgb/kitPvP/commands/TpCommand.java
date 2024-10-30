package net.jgb.kitPvP.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.utils.Environment;
import net.jgb.kitPvP.utils.Message;
import net.jgb.kitPvP.utils.languages.Language;

public class TpCommand implements CommandExecutor {
	
	private Message messageUtils;
	private Environment environmentUtils;
	private Language language;
	
	public TpCommand() {
		this.messageUtils = Main.getUtils().messageUtils();
		this.environmentUtils = Main.getUtils().environmentUtils();
		this.language = Main.getLanguage();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (cmd.equalsIgnoreCase("tp")) {
			Player player = (Player) sender;
			
			if (args.length < 1 || args.length >= 3) {
				player.sendMessage(this.messageUtils.getErrorPrefix() + " �c/tp �fhelp�c.");
				return false;
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					player.sendMessage(this.messageUtils.getInformationPrefix() + " �e�lWARP INFO");
					player.sendMessage("�7� /tp �e<player>");
					player.sendMessage("�7� /tp �r<player> �e<name>");
					player.sendMessage("�7� /tp �e<x> <y> <z>");
					player.sendMessage("�7� /tp �rr");
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
