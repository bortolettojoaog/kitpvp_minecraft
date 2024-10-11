package net.jgb.kitPvP.state;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.jgb.kitPvP.enums.PlayerModeEnum;

public class RootState {
	
	private HashMap<UUID, PlayerModeEnum> players_mode;
	
	public RootState() {
		this.players_mode = new HashMap<>();
	}

	public void clean() {
		players_mode.clear();
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
}
