package me.gamerzking.core.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GamerzKing on 5/15/2016.
 */
public class UtilMaterial {

    private static Set<Material> swords = new HashSet<>();

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilMaterial() {}

    /**
     * Checks if the ItemStack provided is equal to the Material provided.
     *
     * @param itemStack The item determining the relationship.
     * @param material The material.
     * @return True if the items type is equal to the material. Otherwise, false.
     */

    public static boolean isMaterial(ItemStack itemStack, Material material) {

        if(itemStack == null)
            return false;

        return itemStack.getType().equals(material);
    }

    /**
     * Checks if the ItemStack provided is a sword.
     *
     * @param itemStack The item determining the relationship.
     * @return True if the items type is equal to a sword. Otherwise, false.
     */

    public static boolean isSword(ItemStack itemStack) {

        if (itemStack == null)
            return false;

        if (swords.isEmpty()) {

            swords.add(Material.WOOD_SWORD);
            swords.add(Material.STONE_SWORD);
            swords.add(Material.IRON_SWORD);
            swords.add(Material.GOLD_SWORD);
            swords.add(Material.DIAMOND_SWORD);
        }

        return swords.contains(itemStack.getType());
    }
}
