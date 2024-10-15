package net.jgb.kitPvP.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Jumpers {

	public void addVectorBasedOnAdjacentBlocks(Block block, Player player) {
		Block[] adjacentBlocks = {
			    block.getRelative(BlockFace.NORTH),
			    block.getRelative(BlockFace.SOUTH),
			    block.getRelative(BlockFace.EAST),
			    block.getRelative(BlockFace.WEST)
			};

		for (Block adjacent : adjacentBlocks) {
		    if (adjacent.getType().equals(Material.SPONGE)) {
		        Vector direction = player.getLocation().getDirection();

		        if (adjacent.getX() < block.getX()) { // OESTE
		            direction = new Vector(-1, 0, 0);
		        } else if (adjacent.getX() > block.getX()) { // LESTE
		            direction = new Vector(1, 0, 0);
		        } else if (adjacent.getZ() < block.getZ()) { // SUL
		            direction = new Vector(0, 0, -1);
		        } else if (adjacent.getZ() > block.getZ()) { // NORTE
		            direction = new Vector(0, 0, 1);
		        }

		        player.setVelocity(direction.multiply(6.0D).add(new Vector(0, 0.5D, 0)));

		        break;
		    }
		}
	}
}