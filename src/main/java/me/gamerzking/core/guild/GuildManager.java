package me.gamerzking.core.guild;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 5/20/2016.
 */
public class GuildManager {

    private JavaPlugin plugin;

    private Map<String, Guild> guilds = new HashMap<>();

    public GuildManager(JavaPlugin plugin) {

        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}