package me.gamerzking.core.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by GamerzKing on 5/15/2016.
 */
public class UtilMaterial {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilMaterial() {}

    /**
     * Checks if the {@link org.bukkit.inventory.ItemStack} provided is equal to the Material provided.
     *
     * @param itemStack The item determining the relationship.
     * @param material The material.
     * @return True if the items type is equal to the material. Otherwise, false.
     */

    public static boolean isMaterial(ItemStack itemStack, Material material) {

        return itemStack != null && itemStack.getType().equals(material);

    }
}
