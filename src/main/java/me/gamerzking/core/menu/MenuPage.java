package me.gamerzking.core.menu;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 6/3/2016.
 */
public abstract class MenuPage implements Listener {

    private ItemStack placeholder;
    private Inventory inventory;

    private Map<Integer, MenuItem> items = new HashMap<>();

    public MenuPage(JavaPlugin plugin, String name, int rows) {
        this(plugin, name, rows, null);
    }

    public MenuPage(JavaPlugin plugin, String name, int rowsAmt, ItemStack placeholder) {

        this.placeholder = placeholder;

        int rows = Math.min(6, Math.max(1, rowsAmt));
        int size = rows * 9;

        // Creating the inventory
        inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', name));

        if (placeholder != null) {
            for (int i = 0; i < size; i++) {

                // Adds the placeholder to the items (so it can't be clicked)
                addItem(i, placeholder, null);
            }
        }

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Builds the completed menu page.
     */

    public abstract void buildPage();

    /**
     * Adds the specified item to the menu page.
     *
     * @param slot      The slot the item will be added to.
     * @param itemStack The ItemStack you're applying to the slot.
     */

    public void addItem(int slot, ItemStack itemStack) {

        inventory.setItem(slot, itemStack);
    }

    /**
     * Adds the specified item to the menu page.
     *
     * @param slot      The slot the item will be added to.
     * @param itemStack The ItemStack you're applying to the slot.
     * @param item      The item that you're adding (handles interaction).
     */

    public void addItem(int slot, ItemStack itemStack, MenuItem item) {

        items.put(slot, item);
        inventory.setItem(slot, itemStack);
    }

    /**
     * Opens the completed menu page to the specified player.
     *
     * @param player The player that will see the menu.
     */

    public void openInventory(Player player) {

        // Open Inventory
        player.openInventory(inventory);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        int slot = event.getSlot();

        if (!items.containsKey(slot))
            return;

        if(!event.getClickedInventory().equals(inventory))
            return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        MenuItem item = items.get(slot);

        if (event.getCurrentItem().equals(placeholder) || item != null)
            item.onClick(player, event);
    }

    public Map<Integer, MenuItem> getItems() {
        return items;
    }
}