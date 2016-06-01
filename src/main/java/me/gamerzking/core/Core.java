package me.gamerzking.core;

import me.gamerzking.core.command.CommandManager;
import me.gamerzking.core.guild.commands.GuildCommand;
import me.gamerzking.core.updater.Updater;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public class Core extends JavaPlugin {

    private static Core instance;

    private CommandManager commandManager;

    @Override
    public void onEnable() {

        instance = this;

        commandManager = new CommandManager();

        getCommandManager().addCommand(new GuildCommand());

        new Updater(this);
    }

    @Override
    public void onDisable() {

        instance = null;
    }

    public void registerEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, getInstance()));
    }

    public void unregisterEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener -> HandlerList.unregisterAll(listener));
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static Core getInstance() {
        return instance;
    }
}