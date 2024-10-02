package net.jgb.kitPvP;

import net.jgb.kitPvP.events.Listeners;
import net.jgb.kitPvP.utils.CustomRecipes;
import net.jgb.kitPvP.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main currentPlugin;
    private static Message currentMessage;

    @Override
    public void onLoad() {
        setPlugin(this);
        setMessage(new Message());

        Bukkit.getConsoleSender().sendMessage(getMessage().getInformationPrefix().replace("{symbol}", "§6§l!") +  "§7plugin loading...");
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(getMessage().getSucessPrefix().replace("{symbol}", "§2§l!") + " §7plugin successfully activated...");
        init();
    }

    @Override
    public void onDisable() {
        setPlugin(null);
        Bukkit.getConsoleSender().sendMessage(getMessage().getErrorPrefix().replace("{symbol}", "§4§l!") + " §7Plugin successfully disabled...");
    }

    public void init() {
        new Listeners();

        CustomRecipes.soupRecipe(Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.BOWL);
        CustomRecipes.soupRecipe(Material.CACTUS, Material.CACTUS, Material.BOWL);
        CustomRecipes.soupRecipe(Material.INK_SACK, Material.BOWL, 3, 0);
    }

    public static Main getPlugin() {
        return currentPlugin;
    }

    private void setPlugin(Main main) {
    	currentPlugin = main;
    }

    public static Message getMessage() {
        return currentMessage;
    }

    private void setMessage(Message message) {
    	currentMessage = message;
    }
}