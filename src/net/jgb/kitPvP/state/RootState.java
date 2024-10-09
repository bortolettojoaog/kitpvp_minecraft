package net.jgb.kitPvP.state;

import java.util.HashMap;
import java.util.UUID;

import net.jgb.kitPvP.enums.PlayerModeEnum;

public class RootState {
	
	public static HashMap<UUID, PlayerModeEnum> players_mode = new HashMap<>();

}
