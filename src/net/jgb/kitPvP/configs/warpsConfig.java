package net.jgb.kitPvP.configs;

public class warpsConfig extends Config {

	public warpsConfig(String configName) {
		super(configName);
	}

	@Override
	public void createConfig() {
		if (!this.getConfig().contains("Warps")) {
			this.getConfig().createSection("Warps");
			this.getConfig().createSection("Warps.World");
			this.getConfig().createSection("Warps.Nether");
			this.getConfig().createSection("Warps.TheEnd");
		}
	}
}
