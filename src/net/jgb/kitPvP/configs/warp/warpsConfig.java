package net.jgb.kitPvP.configs.warp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.configs.Config;
import net.jgb.kitPvP.entities.WarpEntity;
import net.jgb.kitPvP.enums.WarpStateEnum;
import net.jgb.kitPvP.enums.WorldEnum;
import net.jgb.kitPvP.mappers.WarpMapper;

public class warpsConfig extends Config {

	public warpsConfig(String configName) {
		super(configName);
	}

	@Override
	public void createConfig() {
		if (!this.getConfig().contains("Warps")) {
			this.getConfig().createSection("Warps");
			this.getConfig().createSection("Warps." + WorldEnum.NORMAL);
			this.getConfig().createSection("Warps." + WorldEnum.NETHER);
			this.getConfig().createSection("Warps." + WorldEnum.THE_END);
			this.getConfig().createSection("Warps." + WorldEnum.UNKNOWN);
		}
	}
	
	public List<WarpEntity> getAllWarpsByWorld(WorldEnum world) {
	    ConfigurationSection worldSection = this.getConfig().getConfigurationSection("Warps." + world);

	    if (worldSection == null || worldSection.getKeys(false).isEmpty())
	        return Collections.emptyList();

	    List<WarpEntity> warps = new ArrayList<WarpEntity>();

	    for (String warpName : worldSection.getKeys(false)) {
	        ConfigurationSection warpSection = worldSection.getConfigurationSection(warpName);

	        WarpEntity warp = WarpMapper.fromConfig(warpSection, warpName);
	        
	        if (warp == null) break;

	        warps.add(warp);
	    }
	    return warps;
	}
	
	public WarpEntity getWarpByName(String name, WorldEnum world) {
		ConfigurationSection worldSection = this.getConfig().getConfigurationSection("Warps." + world);

	    if (worldSection == null || worldSection.getKeys(false).isEmpty())
	        return null;
	    
	    boolean existWarp = worldSection.getKeys(false).stream().filter(warp -> warp.equals(name)) != null;
	    
	    if (!existWarp) return null;
	    
	    ConfigurationSection warpSection = worldSection.getConfigurationSection(name);
	    
	    return WarpMapper.fromConfig(warpSection, name);
	}
	
	public Location getWarpLocation(WarpEntity warp) {
		Location location = new Location(warp.getWorld(), warp.getX(), warp.getY(), warp.getZ());
		location.setPitch(warp.getPitch());
		location.setYaw(warp.getYaw());
		return location;
	}
	
	@SuppressWarnings("deprecation")
	public void changeIcon(ItemStack icon, String warpName, WorldEnum world) {
		this.getConfig().add("Warps." + world + "." + warpName + ".icon", icon.getType() + ":" + icon.getDurability() + ":" + icon.getData().getData());
	}
	
	public void removeWarp(String warpName, WorldEnum world) {
		this.getConfig().remove("Warps." + world + "." + warpName);
	}
	
	@SuppressWarnings("deprecation")
	public void createWarp(String warpName, WorldEnum world, Location location) {
		ItemStack icon = new ItemStack(Material.REDSTONE_BLOCK);
		
		Main.getConfigs().getWarpConfig().createSection("Warps." + world + "." + warpName);
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".world", location.getWorld().getName());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".x", location.getX());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".y", location.getY());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".z", location.getZ());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".pitch", location.getPitch());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".yaw", location.getYaw());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".icon", icon.getType() + ":" + icon.getDurability() + ":" + icon.getData().getData());
		Main.getConfigs().getWarpConfig().add("Warps." + world + "." + warpName + ".state", WarpStateEnum.PUBLIC.getDisplayName());
	}
}
