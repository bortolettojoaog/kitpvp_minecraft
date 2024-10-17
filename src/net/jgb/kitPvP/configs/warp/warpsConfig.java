package net.jgb.kitPvP.configs.warp;

import net.jgb.kitPvP.configs.Config;

public class warpsConfig extends Config {

	public warpsConfig(String configName) {
		super(configName);
	}

	@Override
	public void createConfig() {
		if (!this.getConfig().contains("Warps")) {
			this.getConfig().createSection("Warps");
			this.getConfig().createSection("Warps.Normal");
			this.getConfig().createSection("Warps.Nether");
			this.getConfig().createSection("Warps.TheEnd");
			this.getConfig().createSection("Warps.Unknown");
		}
	}
}
