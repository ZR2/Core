package me.gamerzking.core.collectible.event;

import me.gamerzking.core.collectible.types.Collectible;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class CollectibleActivateEvent extends Event implements Cancellable {

    private Player player;
    private Collectible collectible;

    /**
     * @param player The player that activated the collectible.
     * @param collectible The collectible that was activated.
     */

    public CollectibleActivateEvent(Player player, Collectible collectible) {

        this.player = player;
        this.collectible = collectible;
    }

    private boolean cancelled = false;
    private static final HandlerList handlers = new HandlerList();

    public Player getPlayer() {
        return player;
    }

    public Collectible getCollectible() {
        return collectible;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}