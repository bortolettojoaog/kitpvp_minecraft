package net.jgb.kitPvP;

import net.jgb.kitPvP.events.Listeners;
import net.jgb.kitPvP.state.RootState;
import net.jgb.kitPvP.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main currentPlugin;
    private static Utils utils;
    private static RootState rootState;

    @Override
    public void onLoad() {
        setPlugin(this);
        setUtils(new Utils());
        setRootState(new RootState());

        Bukkit.getConsoleSender().sendMessage(utils.messageUtils().getInformationPrefix() +  " §7plugin loading...");
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(utils.messageUtils().getSucessPrefix() + " §7plugin successfully activated...");
        init();
    }

    @Override
    public void onDisable() {
    	Bukkit.getConsoleSender().sendMessage(utils.messageUtils().getErrorPrefix() + " §7Plugin successfully disabled...");
    	
        setPlugin(null);
        setUtils(null);
        
        getRootState().clean();
        setRootState(null);
    }

    public void init() {
        new Listeners();

        utils.customRecipeUtils().soupRecipe(Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.BOWL);
        utils.customRecipeUtils().soupRecipe(Material.CACTUS, Material.CACTUS, Material.BOWL);
        utils.customRecipeUtils().soupRecipe(Material.INK_SACK, Material.BOWL, 3, 0);
        utils.customRecipeUtils().soupRecipe(Material.PUMPKIN_SEEDS, Material.PUMPKIN_SEEDS, Material.BOWL);
    }

    public static Main getPlugin() {
        return currentPlugin;
    }

    private void setPlugin(Main main) {
    	currentPlugin = main;
    }

    public static Utils getUtils() {
        return utils;
    }
    
    private void setUtils(Utils utilsClass) {
    	utils = utilsClass;
    }
    
    public static RootState getRootState() {
        return rootState;
    }
    
    private void setRootState(RootState rootStateClass) {
    	rootState = rootStateClass;
    }
}