package net.jgb.kitPvP.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class Environment {
	
	public String checkPlayerWorld(Player player) {
        World world = player.getWorld();
        World.Environment environment = world.getEnvironment();
        
        if (environment == World.Environment.NORMAL) {
        	return "Normal";
        } else if (environment == World.Environment.NETHER) {
        	return "Nether";
        } else if (environment == World.Environment.THE_END) {
        	return "TheEnd";
        } else {
        	return "Unknown";
        }
    }
}
