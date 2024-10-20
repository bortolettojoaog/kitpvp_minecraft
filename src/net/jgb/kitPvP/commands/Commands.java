package net.jgb.kitPvP.commands;

import net.jgb.kitPvP.Main;

public class Commands {
	
	public Commands() {
		Main.getPlugin().getCommand("warp").setExecutor(new WarpCommand());
		Main.getPlugin().getCommand("warps").setExecutor(new WarpsCommand());
	}

}
