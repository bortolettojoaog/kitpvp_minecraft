package net.jgb.kitPvP.configs;

import org.bukkit.configuration.ConfigurationSection;

import net.jgb.kitPvP.utils.customs.CustomConfig;

public abstract class Config {
	
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
	
    public Object get(String path) {
        return this.config.getFile().get(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return this.config.getFile().getConfigurationSection(path);
    }

    public void add(String path, Object object) {
    	this.config.getFile().set(path, object);

    	saveConfig();
    }

    public void remove(String path) {
    	this.config.getFile().set(path, null);

    	saveConfig();
    }

    public boolean contains(String path) {
        return this.config.getFile().contains(path);
    }

    public void createSection(String path) {
    	this.config.getFile().createSection(path);

    	saveConfig();
    }
}
