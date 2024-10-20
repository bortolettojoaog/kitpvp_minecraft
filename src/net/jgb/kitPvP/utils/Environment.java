package net.jgb.kitPvP.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;

import net.jgb.kitPvP.enums.WorldEnum;

public class Environment {
	
	public WorldEnum checkPlayerWorld(Player player) {
        World world = player.getWorld();
        World.Environment environment = world.getEnvironment();
        
        if (environment == World.Environment.NORMAL) {
        	return WorldEnum.NORMAL;
        } else if (environment == World.Environment.NETHER) {
        	return WorldEnum.NETHER;
        } else if (environment == World.Environment.THE_END) {
        	return WorldEnum.THE_END;
        } else {
        	return WorldEnum.UNKNOWN;
        }
    }
}
