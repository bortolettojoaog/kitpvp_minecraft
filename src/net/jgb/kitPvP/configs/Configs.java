package net.jgb.kitPvP.configs;

import net.jgb.kitPvP.configs.warp.warpsConfig;

public class Configs {
	
	private warpsConfig warpConfig;
	
	public Configs(String warpConfigName) {
		this.setWarpConfig(new warpsConfig(warpConfigName));
	}

	public warpsConfig getWarpConfig() {
		return warpConfig;
	}

	public void setWarpConfig(warpsConfig warpConfig) {
		this.warpConfig = warpConfig;
	}
}
