package me.gamerzking.core.collectible.ui.page;

import me.gamerzking.core.itemstack.ItemBuilder;
import me.gamerzking.core.menu.MenuItem;
import me.gamerzking.core.menu.MenuPage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class MorphPage extends MenuPage {

    public MorphPage(JavaPlugin plugin) {
        super(plugin, "Morphs", 5);
    }

    @Override
    public void buildPage() {

        addItem(0, new ItemBuilder(Material.STONE).setName("&aTesting!").build(), new MenuItem() {

            @Override
            public void onClick(Player player, InventoryClickEvent event) {

                player.closeInventory();
            }
        });
    }
}