package me.gamerzking.core;

import me.gamerzking.core.updater.Updater;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public class Core extends JavaPlugin {

    private static Core instance;

    @Override
    public void onEnable() {

        instance = this;

        new Updater(this);
    }

    @Override
    public void onDisable() {

        instance = null;
    }

    public void registerEvents(Listener... listeners) {

        for(Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, getInstance());
        }
    }

    public void unregisterEvents(Listener... listeners) {

        for(Listener listener : listeners) {
            HandlerList.unregisterAll(listener);
        }
    }

    public static Core getInstance() {
        return instance;
    }
}