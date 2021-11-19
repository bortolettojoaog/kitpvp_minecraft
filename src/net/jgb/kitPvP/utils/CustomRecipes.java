package net.jgb.kitPvP.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CustomRecipes {

    public static void soupRecipe(Material... materials) {
        ShapelessRecipe recipe = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        for (Material material : materials) {
            recipe.addIngredient(material);
        }

        Bukkit.addRecipe(recipe);
    }

    public static void soupRecipe(Material material1, Material material2, int dataMaterial1, int dataMaterial2) {
        ShapelessRecipe recipe = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        recipe.addIngredient(material1.getNewData((byte)dataMaterial1));
        recipe.addIngredient(material2.getNewData((byte)dataMaterial2));

        Bukkit.addRecipe(recipe);
    }

}
