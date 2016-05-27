package me.gamerzking.core.punishment;

import me.gamerzking.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Created by GamerzKing on 5/18/2016.
 */
public class PunishmentHandler implements Listener {

    public PunishmentHandler() {

        Core.getInstance().registerEvents(this);
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {

        String name = event.getName();
    }
}