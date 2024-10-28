package net.jgb.kitPvP.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
