package me.gamerzking.core.guild.ui;

import me.gamerzking.core.itemstack.ItemBuilder;
import me.gamerzking.core.menu.MenuItem;
import me.gamerzking.core.menu.MenuPage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by GamerzKing on 6/6/2016.
 */
public class GuildShop extends MenuPage {

    public GuildShop() {

        super(
                "Guild Shop", 6);
    }

    @Override
    public void buildPage() {

        addItem(3, new ItemBuilder(Material.MELON).build(), new MenuItem() {

            @Override
            public void onClick(Player player, InventoryClickEvent event) {

                player.sendMessage("Hello!");
            }
        });
    }
}