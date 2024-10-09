package net.jgb.kitPvP.utils;

public class Utils {
	
	private CustomPlayerInventory customPlayerInventory;
	private CustomRecipe customRecipes;
	private Item itemUtils;
	private Message message;
	
	public Utils() {
		this.customRecipes = new CustomRecipe();
		this.customPlayerInventory = new CustomPlayerInventory(this.customRecipes);
		this.itemUtils = new Item();
		this.message = new Message();
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
}