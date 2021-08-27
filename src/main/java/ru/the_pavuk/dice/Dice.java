package ru.the_pavuk.dice;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dice extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventsListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
