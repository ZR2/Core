package me.gamerzking.core.punishment;

import me.gamerzking.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Created by GamerzKing on 6/2/2016.
 */
public class PunishmentListener implements Listener {

    private Punishment punishment;

    /**
     * Registers the events.
     */

    public PunishmentListener(Punishment punishment) {

        Core.getInstance().registerEvents(this);

        this.punishment = punishment;
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {

        // If they are banned, disallow them from joining, and set the kick message
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        // If they are muted, send them a message, and cancel the event, cancelling the message from being sent
    }
}