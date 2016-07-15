package me.gamerzking.core.collectible.ui;

import me.gamerzking.core.collectible.types.CollectibleType;
import me.gamerzking.core.collectible.ui.page.MorphPage;
import me.gamerzking.core.menu.MenuItem;
import me.gamerzking.core.menu.MenuPage;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class CollectibleMenu extends MenuPage {

    private JavaPlugin plugin;

    public CollectibleMenu(JavaPlugin plugin) {

        super(plugin, "Collectibles", 5);

        this.plugin = plugin;
    }

    @Override
    public void buildPage() {

        addItem(8, CollectibleType.MORPH.getItem(), new MenuItem() {

            @Override
            public void onClick(Player player, InventoryClickEvent event) {

                MorphPage page = new MorphPage(plugin);

                page.buildPage();
                page.openInventory(player);
            }
        });
    }
}