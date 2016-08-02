package me.gamerzking.core.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GamerzKing on 6/9/2016.
 */
public class UtilInventory {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilInventory() {}

    /**
     * Clears the players inventory (including armor, item on cursor, etc.)
     *
     * @param player The players inventory you're resetting.
     */

    public static void clearInventory(Player player) {

        PlayerInventory inventory = player.getInventory();

        inventory.clear();
        inventory.setArmorContents(new ItemStack[4]);
        player.setItemOnCursor(new ItemStack(Material.AIR));

        player.saveData();
    }

    /**
     * Removes the item from the players inventory.
     *
     * @param player The players inventory that you're removing the items from.
     * @param material The type of Material that you're removing from the player.
     * @param data The data of the item you're removing from the player.
     * @param amount The amount of items that are being removed.
     */

    public static void removeItem(Player player, Material material, byte data, int amount) {

        if(!player.getInventory().contains(material, amount))
            return;

        for (int i : player.getInventory().all(material).keySet()) {

            if (amount <= 0)
                continue;

            ItemStack stack = player.getInventory().getItem(i);

            if (stack.getData() == null || stack.getData().getData() == data) {

                int foundAmount = stack.getAmount();

                if (amount >= foundAmount) {

                    amount -= foundAmount;
                    player.getInventory().setItem(i, null);

                } else {

                    stack.setAmount(foundAmount - amount);
                    player.getInventory().setItem(i, stack);

                    amount = 0;
                }
            }
        }

        player.updateInventory();
    }

    /**
     * Removes all of the specified data from the players inventory.
     *
     * @param player The players inventory that you're removing the items from.
     * @param type The type of Material that you're removing from the player.
     * @param data The data of the item that you're removing from the player.
     * @return The amount of items that match the description and data, that are removed.
     */

    public static int removeAll(Player player, Material type, byte data) {

        Set<ItemStack> remove = new HashSet<>();
        int count = 0;

        for (ItemStack item : player.getInventory().getContents()) {

            if (item == null)
                return 0;

            if (item.getType() == type) {
                if (data == -1 || item.getData() == null || (item.getData() != null && item.getData().getData() == data)) {

                    count += item.getAmount();
                    remove.add(item);
                }
            }
        }

        for (ItemStack item : remove)
            player.getInventory().remove(item);

        return count;
    }

    /**
     * Uses the item in the players main hand by removing items, or setting their item in hand to null.
     *
     * @param player The player that's using the item.
     */

    public static void useItemInHand(Player player) {

        if (player.getInventory().getItemInMainHand().getAmount() > 1)
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

        else
            player.getInventory().setItemInMainHand(null);

        player.updateInventory();
    }
}