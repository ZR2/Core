package me.gamerzking.core.cooldown;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by GamerzKing on 7/25/2016.
 */
public class Cooldown implements Listener {

    private Map<UUID, Map<String, Long>> users = new HashMap<>();

    public Cooldown(JavaPlugin plugin) {

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Uses an ability
     *
     * @param player
     * @param ability
     * @param cooldown
     * @return Whether the cooldown has expired or not.
     */

    public boolean useAbility(Player player, String ability, long cooldown) {

        return true;
    }

    public Map<String, Long> getCooldown(Player player) {

        if(users.containsKey(player.getUniqueId()))
            users.put(player.getUniqueId(), new HashMap<>());

        return users.get(player.getUniqueId());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        // Clearing the cooldown for the player who died
        getCooldown(event.getEntity()).clear();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        // Clearing the cooldown for the player who quit
        users.remove(event.getPlayer().getUniqueId());
    }
}