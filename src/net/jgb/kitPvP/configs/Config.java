package net.jgb.kitPvP.configs;

import net.jgb.kitPvP.utils.CustomConfig;

abstract class Config {
	
	private CustomConfig config;
	
	public Config(String configName) {
		this.setConfig(new CustomConfig(configName));
		
		createConfig();
	}
	
	public abstract void createConfig(); 
	
	public void saveConfig() {
		this.config.save();
	}
	
	public void reloadConfig() {
		this.config.reload();
	}

	public CustomConfig getConfig() {
		return config;
	}

	public void setConfig(CustomConfig config) {
		this.config = config;
	}
}
