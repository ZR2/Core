package me.gamerzking.core.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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