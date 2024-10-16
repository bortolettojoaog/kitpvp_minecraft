package net.jgb.kitPvP.utils.customs;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class CustomRecipe {

	public CustomRecipe() {
		
	}
	
    public void soupRecipe(Material... materials) {
        ShapelessRecipe recipe = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        for (Material material : materials) {
            recipe.addIngredient(material);
        }

        Bukkit.addRecipe(recipe);
    }

	public void soupRecipe(Material firstMaterial, Material secondMaterial, int firstData, int secondData) {
        ShapelessRecipe recipe = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        recipe.addIngredient(firstMaterial.getNewData((byte)firstData));
        recipe.addIngredient(secondMaterial.getNewData((byte)secondData));

        Bukkit.addRecipe(recipe);
    }
	
    public ItemStack createItem(Material material, int amount, String displayName) {
    	ItemStack itemStack = new ItemStack(material, amount);
    	ItemMeta itemMeta = itemStack.getItemMeta();
    	itemMeta.setDisplayName(displayName);
    	itemStack.setItemMeta(itemMeta);
    	
    	return itemStack;
    }
    
    public ItemStack createItem(Material material, int amount, String displayName, List<String> lore) {
    	ItemStack itemStack = new ItemStack(material, amount);
    	ItemMeta itemMeta = itemStack.getItemMeta();
    	itemMeta.setDisplayName(displayName);
    	itemMeta.setLore(lore);
    	itemStack.setItemMeta(itemMeta);
    	
    	return itemStack;
    }
    
    public ItemStack createItem(Material material, int amount, String displayName, List<String> lore, int durability) {
    	ItemStack itemStack = createItem(material, amount, displayName, lore);
    	ItemMeta itemMeta = itemStack.getItemMeta();
    	itemStack.setDurability((short)durability);
    	itemStack.setItemMeta(itemMeta);
    	
    	return itemStack;
    }
    
    public ItemStack createItem(Material material, int amount, String displayName, List<String> lore, short durability, Map<Enchantment, Integer> enchantments) {
    	ItemStack itemStack = createItem(material, amount, displayName, lore, durability);
    	ItemMeta itemMeta = itemStack.getItemMeta();
    	for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();
            itemMeta.addEnchant(enchantment, level, false);
        }
    	itemStack.setItemMeta(itemMeta);
    	
    	return itemStack;
    }
}