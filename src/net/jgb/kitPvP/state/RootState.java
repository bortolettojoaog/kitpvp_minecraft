package net.jgb.kitPvP.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.jgb.kitPvP.enums.PlayerModeEnum;
import net.jgb.kitPvP.utils.customs.CustomConfig;
import net.jgb.kitPvP.utils.customs.CustomPaginatedInventory;

public class RootState {
	
	private HashMap<UUID, PlayerModeEnum> players_mode;
	private List<CustomPaginatedInventory> inventories;
	private List<UUID> players_jumping;
	private HashMap<String, CustomConfig> configs;
	
	public RootState() {
		this.players_mode = new HashMap<>();
		this.inventories = new ArrayList<>();
		this.players_jumping = new ArrayList<>();
		this.configs = new HashMap<>();
	}

	public void clean() {
		this.players_mode.clear();
		this.inventories.clear();
		this.players_jumping.clear();
		this.configs.clear();
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
	
	public void setInventorys(List<CustomPaginatedInventory> inventories) {
		this.inventories = inventories;
	}
	
	public void addInventory(CustomPaginatedInventory inventory) {
		this.inventories.add(inventory);
	}
	
	public void removeInventory(CustomPaginatedInventory inventory) {
		this.inventories.removeIf(inv -> inv.equals(inventory));
	}
	
	public List<CustomPaginatedInventory> getInventories() {
		return this.inventories;
	}
	
	public void setPlayerJumping(List<UUID> players_jumping) {
		this.players_jumping = players_jumping;
	}
	
	public void addPlayerJumping(UUID playerUUID) {
		this.players_jumping.add(playerUUID);
	}
	
	public void removePlayerJumping(UUID playerUUID) {
		this.players_jumping.remove(playerUUID);
	}
	
	public List<UUID> getPlayersJumping() {
		return this.players_jumping;
	}
	
	public void addConfig(String name, CustomConfig config) {
		this.configs.put(name, config);
	}
	
	public void removeConfig(String name) {
		this.configs.remove(name);
	}
	
	public HashMap<String, CustomConfig> getConfigs() {
		return this.configs;
	}
}
