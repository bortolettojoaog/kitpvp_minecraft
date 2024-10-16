package net.jgb.kitPvP.utils.customs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.jgb.kitPvP.Main;

import java.io.File;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class CustomConfig {

    private final String fileName;
    private File file;
    private FileConfiguration fileConfiguration;
    
    public CustomConfig(String fileName) {
    	this.fileName = fileName;
    	
    	setup();
    }

    public void setup() {
        this.file = new File(
                Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(
                        Main.getPlugin().getDescription().getName()
                )).getDataFolder(), this.fileName + ".yml"
        );

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (Exception e) {
                getLogger().warning("Error creating file " + fileName + ".yml");
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        if (!Main.getRootState().getConfigs().containsKey(this.fileName))
        	Main.getRootState().getConfigs().put(this.fileName, this);
    }

    public FileConfiguration getFile() {
        return this.fileConfiguration;
    }

    public void save() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (Exception e) {
            getLogger().warning("Error saving file " + fileName + ".yml");
        }

        reload();
    }

    public void reload() {
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        Main.getRootState().getConfigs().replace(this.fileName, this);
    }

    public Object get(String path) {
        return this.fileConfiguration.get(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return this.fileConfiguration.getConfigurationSection(path);
    }

    public void add(String path, Object object) {
        this.fileConfiguration.set(path, object);

        save();
    }

    public void remove(String path) {
        this.fileConfiguration.set(path, null);

        save();
    }

    public boolean contains(String path) {
        return this.fileConfiguration.contains(path);
    }

    public void createSection(String path) {
        this.fileConfiguration.createSection(path);

        save();
    }
}