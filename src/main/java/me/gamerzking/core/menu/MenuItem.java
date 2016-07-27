package me.gamerzking.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by GamerzKing on 6/3/2016.
 */
public abstract class MenuItem {

    /**
     * Called when a player clicks the item.
     *
     * @param player The player who clicked the item.
     * @param event The event called when the player clicks the item.
     */

    public abstract void onClick(Player player, InventoryClickEvent event);
}