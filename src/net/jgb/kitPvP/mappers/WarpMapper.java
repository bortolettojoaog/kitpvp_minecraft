package net.jgb.kitPvP.mappers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import net.jgb.kitPvP.entities.WarpEntity;
import net.jgb.kitPvP.enums.WarpStateEnum;

@SuppressWarnings("deprecation")
public class WarpMapper {

	public static WarpEntity fromConfig(ConfigurationSection warpSection, String name) {
		if (warpSection != null) {
            String warpWorld = warpSection.getString("world");
            double x = warpSection.getDouble("x");
            double y = warpSection.getDouble("y");
            double z = warpSection.getDouble("z");
            float pitch = (float) warpSection.getDouble("pitch");
            float yaw = (float) warpSection.getDouble("yaw");
            String iconString = warpSection.getString("icon");
            String[] iconProperties = iconString.split(":");
            
            ItemStack icon = new ItemStack(Material.getMaterial(iconProperties[0]));
            icon.setDurability((short) Double.parseDouble(iconProperties[1]));
            
			MaterialData data = new MaterialData(icon.getType(), Byte.parseByte(iconProperties[2]));
            icon.setData(data);
            
            WarpStateEnum state = WarpStateEnum.PRIVATE.getDisplayName().equals(warpSection.getString("state")) ? WarpStateEnum.PRIVATE : WarpStateEnum.PUBLIC;

            WarpEntity warp = new WarpEntity(Bukkit.getWorld(warpWorld), x, y, z, pitch, yaw, icon, state);
            warp.setWarpName(name);
            
            return warp;
        }
		return null;
	}
}
