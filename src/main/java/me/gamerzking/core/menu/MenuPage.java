package me.gamerzking.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by GamerzKing on 6/3/2016.
 */
public abstract class MenuPage {

    private Inventory inventory;
    private static HashMap<String, MenuPage> inventories = new HashMap<>();

    private HashMap<Integer, MenuItem> runs = new HashMap<>();
    private int currentOpen = 0;
    private boolean registered = false;

    public MenuPage(String name, int rows) {
        this(name, rows, null);
    }

    public MenuPage(String name, int rows, ItemStack placeholder) {

        int size = rows * 9;
        inventory = Bukkit.createInventory(null, size, name);

        if (placeholder != null) {
            for (int i = 0; i < size; i++) {

                inventory.setItem(i, placeholder);
            }
        }
        register();
    }

    public Inventory getSourceInventory() {
        return inventory;
    }

    public int getSize() {
        return inventory.getSize();
    }

    public void setItem(ItemStack itemstack, Integer slot, MenuItem executeOnClick) {
        setItem(itemstack, null, slot, executeOnClick);
    }

    public void setItem(ItemStack item, String displayName, int slot, MenuItem executeOnClick, String... description) {

        ItemMeta meta = item.getItemMeta();

        if (displayName != null) {
            meta.setDisplayName(displayName);
        }

        if (description != null) {

            List<String> lore = new ArrayList<String>();

            for (String s : description) {
                lore.add(ChatColor.GRAY + s);
            }
            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        inventory.setItem(slot, item);

        runs.put(slot, executeOnClick);
    }

    public void removeItem(int slot) {
        inventory.setItem(slot, new ItemStack(Material.AIR));
    }

    public void setItem(ItemStack itemstack, Integer slot) {
        inventory.setItem(slot, itemstack);
    }

    public static Listener getListener() {

        return new Listener() {

            @EventHandler
            public void onClick(InventoryClickEvent event) {

                if (event.getWhoClicked() instanceof Player) {

                    if (event.getCurrentItem() == null)
                        return;

                    if (inventories.containsKey(event.getClickedInventory().getName())) {

                        MenuPage current = inventories.get(event.getClickedInventory().getName());
                        event.setCancelled(true);

                        Player p = (Player) event.getWhoClicked();

                        if (current.runs.get(event.getSlot()) == null)
                            return;

                        current.runs.get(event.getSlot()).onClick(p, event.getClick());
                    }
                }
            }
        };
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        if (e.getPlayer() instanceof Player) {

            Inventory inventory;

            if ((inventory = e.getInventory()) == null) {
                return;
            }

            if (inventories.containsKey(inventory.getName())) {

                MenuPage current = inventories.get(inventory.getName());
                current.currentOpen--;

                if (current.currentOpen != 0)
                    return;

                current.unregister();
            }
        }
    }

    public void openInventory(Player player) {

        currentOpen++;
        register();
        player.openInventory(getSourceInventory());
    }

    private void register() {

        if (!registered) {

            inventories.put(inventory.getName(), this);
            registered = true;
        }
    }

    private void unregister() {

        if (registered) {

            inventories.remove(inventory.getName());
            registered = false;
        }
    }
}