package net.jgb.kitPvP.events;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.events.inventories.WarpConfigInventory;

public class Listeners {

    public Listeners() {
        Main.getPlugin().getServer().getPluginManager().registerEvents(new DefaultEvents(), Main.getPlugin());
        Main.getPlugin().getServer().getPluginManager().registerEvents(new WarpConfigInventory(), Main.getPlugin());
    }
}