package net.jgb.kitPvP.entities;

import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import net.jgb.kitPvP.enums.WarpStateEnum;

public class WarpEntity {   
	private String warpName;
    private World world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private ItemStack icon;
    private WarpStateEnum state;
    
    public WarpEntity(World world, double x, double y, double z, float pitch, float yaw, ItemStack icon, WarpStateEnum state) {
    	this.world = world;
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.pitch = pitch;
    	this.yaw = yaw;
    	this.icon = icon;
    	this.state = state;
    }
    
    public String getWarpName() {
    	return warpName;
    }
    
    public void setWarpName(String warpName) {
    	this.warpName = warpName;
    }
    
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}
	
	public WarpStateEnum getState() {
		return state;
	}

	public void setState(WarpStateEnum state) {
		this.state = state;
	}
}
