package net.jgb.kitPvP.configs;

public class Configs {
	
	private warpsConfig itemConfig;
	
	public Configs(String itemConfigName) {
		this.setItemConfig(new warpsConfig(itemConfigName));
	}

	public warpsConfig getItemConfig() {
		return itemConfig;
	}

	public void setItemConfig(warpsConfig itemConfig) {
		this.itemConfig = itemConfig;
	}
}
