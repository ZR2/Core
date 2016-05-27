package me.gamerzking.core.itemstack;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by GamerzKing on 5/10/2016.
 */
public class ItemBuilder {

    private static Field enchantmentNew;
    private static GlowEnchantment enchantment;

    static {
        try {

            enchantmentNew = Enchantment.class.getDeclaredField("acceptingNew");
            enchantmentNew.setAccessible(true);
            enchantmentNew.set(null, true);

            enchantment = new GlowEnchantment();
            EnchantmentWrapper.registerEnchantment(enchantment);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private ItemStack itemStack;

    /**
     * @param material The material the item will be built with.
     */

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * @param itemStack The ItemStack being used to build the item.
     */

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @param material The material the item will be built with.
     * @param amount The quanity of items that will be built.
     */

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * @return Creates and returns a copied build of the item.
     */

    public ItemBuilder clone() {
        return new ItemBuilder(itemStack);
    }

    /**
     * Sets the durability of the item being built.
     *
     * @param durability The durability you are applying to the item.
     * @return The item with the durability applied.
     */

    public ItemBuilder setDurability(short durability) {

        itemStack.setDurability(durability);
        return this;
    }

    /**
     * Sets the name of the item being built.
     *
     * @param name The name you are applying to the item.
     * @return The item with the name applied.
     */

    public ItemBuilder setName(String name) {

        itemStack.getItemMeta().setDisplayName(name);
        return this;
    }

    /**
     * Removes an enchantment from the item being built.
     *
     * @param enchantment The enchantment you are removing from the item.
     * @return The item with the enchantment removed.
     */

    public ItemBuilder removeEnchantment(Enchantment enchantment) {

        itemStack.removeEnchantment(enchantment);
        return this;
    }

    /**
     * Sets the owner of the skull item.
     *
     * @param owner The owner (players username) of the skull.
     * @return The item with the skull owner applied.
     */

    public ItemBuilder setSkullOwner(String owner) {

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(owner);

        return this;
    }

    /**
     * Adds an enchantment to the current item.
     *
     * @param enchantment The enchantment you are applying to the item.
     * @param level The level of the enchantment.
     * @return The item with the enchantment and level applied.
     */

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {

        itemStack.getItemMeta().addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Adds an enchantment glow effect to the ItemStack.
     *
     * @return The item with the enchantment glow effect applied.
     */

    public ItemBuilder addGlow() {

        addEnchant(enchantment, enchantment.getStartLevel());
        return this;
    }

    /**
     * Removes the enchantment glow affect associated with the ItemStack.
     *
     * @return The item with the enchantment glow effect removed.
     */

    public ItemBuilder removeGlow() {

        removeEnchantment(enchantment);
        return this;
    }

    /**
     * Sets the item to unbreakable, if specified.
     *
     * @param unbreakable Whether the items durability will become unbreakable.
     * @return The item with the boolean applied.
     */

    public ItemBuilder setUnbreakable(boolean unbreakable) {

        itemStack.getItemMeta().spigot().setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lore The lore you are applying to the item.
     * @return The item with the lore applied.
     */

    public ItemBuilder setLore(String... lore){

        itemStack.getItemMeta().setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Sets the color of the wool to the specified DyeColor.
     *
     * @param color The color you are applying to the wool.
     * @return The item with the specified color applied to it.
     */

    public ItemBuilder setWoolColor(DyeColor color) {

        if(itemStack.getType() != Material.WOOL)
            return this;

        itemStack.setDurability(color.getData());
        return this;
    }

    /**
     * Sets the color of the leather armor to the Color specified.
     *
     * @param color The color you are adding to the leather armor.
     * @return The item with the color applied to the armor.
     */

    public ItemBuilder setArmorColor(Color color) {

        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);

        return this;
    }

    /**
     * @return The built item.
     */

    public ItemStack build() {

        itemStack.setItemMeta(itemStack.getItemMeta());
        return itemStack;
    }
}