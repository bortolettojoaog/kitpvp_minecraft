package net.jgb.kitPvP.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.jgb.kitPvP.enums.PlayerModeEnum;
import net.jgb.kitPvP.utils.CustomInventory;

public class RootState {
	
	private HashMap<UUID, PlayerModeEnum> players_mode;
	private List<CustomInventory> inventories;
	
	public RootState() {
		this.players_mode = new HashMap<>();
		this.inventories = new ArrayList<>();
	}

	public void clean() {
		this.players_mode.clear();
		this.inventories.clear();
	}
	
	public boolean comparePlayerMode(Player player, PlayerModeEnum playerMode) {
		return this.players_mode.containsKey(player.getUniqueId()) && this.players_mode.get(player.getUniqueId()).equals(playerMode);
	}
	
	public void updatePlayerMode(Player player, PlayerModeEnum playerMode) {
		this.players_mode.put(player.getUniqueId(), playerMode);
	}
	
	public void resetPlayerMode(Player player) {
		this.players_mode.put(player.getUniqueId(), PlayerModeEnum.HOLDING);
	}
	
	public PlayerModeEnum getPlayerMode(Player player) {
		if (!this.players_mode.containsKey(player.getUniqueId()))
			updatePlayerMode(player, PlayerModeEnum.HOLDING);
		
		return this.players_mode.get(player.getUniqueId());
	}
	
	public void setInventorys(List<CustomInventory> inventories) {
		this.inventories = inventories;
	}
	
	public void addInventory(CustomInventory inventory) {
		this.inventories.add(inventory);
	}
	
	public void removeInventory(CustomInventory inventory) {
		this.inventories.removeIf(inv -> inv.equals(inventory));
	}
	
	public List<CustomInventory> getInventories() {
		return this.inventories;
	}
}
