package net.jgb.kitPvP.events;

import net.jgb.kitPvP.Main;

public class Listeners {

    public Listeners() {
        Main.getPlugin().getServer().getPluginManager().registerEvents(new PlayerInteract(), Main.getPlugin());
    }
}