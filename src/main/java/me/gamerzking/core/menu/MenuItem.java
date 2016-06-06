package me.gamerzking.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * Created by GamerzKing on 6/3/2016.
 */
public abstract class MenuItem {

    public abstract void onClick(Player player, ClickType clickType);
}