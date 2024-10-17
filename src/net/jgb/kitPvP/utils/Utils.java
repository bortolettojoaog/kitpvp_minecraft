package net.jgb.kitPvP.utils;

import net.jgb.kitPvP.utils.customs.CustomPlayerInventory;
import net.jgb.kitPvP.utils.customs.CustomRecipe;

public class Utils {
	
	private CustomPlayerInventory customPlayerInventory;
	private CustomRecipe customRecipes;
	private Item itemUtils;
	private Message message;
	private Jumpers jumpers;
	private Environment environment;
	
	public Utils() {
		this.customRecipes = new CustomRecipe();
		this.customPlayerInventory = new CustomPlayerInventory(this.customRecipes);
		this.itemUtils = new Item();
		this.message = new Message();
		this.jumpers = new Jumpers();
		this.environment = new Environment();
	}
	
	public CustomPlayerInventory customPlayerInventoryUtils() {
		return this.customPlayerInventory;
	}
	
	public CustomRecipe customRecipeUtils() {
		return this.customRecipes;
	}
	
	public Item itemUtils() {
		return this.itemUtils;
	}
	
	public Message messageUtils() {
		return this.message;
	}
	
	public Jumpers jumperUtils() {
		return this.jumpers;
	}
	
	public Environment environmentUtils() {
		return this.environment;
	}
}
