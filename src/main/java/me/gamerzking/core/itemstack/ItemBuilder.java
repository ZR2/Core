package me.gamerzking.core.itemstack;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.util.*;

/**
 * Created by GamerzKing on 5/10/2016.
 */
public class ItemBuilder {

	private ItemStack itemStack;
	private ItemMeta itemMeta;

	/**
	 * @param material The material the item will be built with.
	 */

	public ItemBuilder(Material material) 
	{
		this(material, 1);
	}

	/**
	 * @param itemStack The ItemStack being used to build the item.
	 */

	public ItemBuilder(ItemStack itemStack) 
	{
		this.itemStack = itemStack;
	}

	/**
	 * @param material The material the item will be built with.
	 * @param amount The quanity of items that will be built.
	 */

	public ItemBuilder(Material material, int amount) 
	{
		this(material, amount, new ItemStack(material, amount).getItemMeta());
	}
	
	public ItemBuilder(Material material, int amount, ItemMeta meta) 
	{
		this.itemStack = new ItemStack(material, amount);
		this.itemMeta = meta;
	}


	/**
	 * @return Creates and returns a copied build of the item.
	 */

	public ItemBuilder clone() 
	{
		return new ItemBuilder(itemStack.getType(), itemStack.getAmount(), itemMeta);
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

		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
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

		SkullMeta skullMeta = (SkullMeta) itemMeta;
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

		itemMeta.addEnchant(enchantment, level, true);
		return this;
	}

	/**
	 * Adds an enchantment glow effect to the ItemStack.
	 *
	 * @return The item with the enchantment glow effect applied.
	 */

	public ItemBuilder addGlow() {
		// TODO: 6/17/2016
		return this;
	}

	/**
	 * Sets the item to unbreakable, if specified.
	 *
	 * @param unbreakable Whether the items durability will become unbreakable.
	 * @return The item with the boolean applied.
	 */

	public ItemBuilder setUnbreakable(boolean unbreakable) {

		itemMeta.spigot().setUnbreakable(unbreakable);
		return this;
	}

	/**
	 * Sets the lore of the item.
	 *
	 * @param lore The lore you are applying to the item.
	 * @return The item with the lore applied.
	 */

	public ItemBuilder setLore(String... lore){

		itemMeta.setLore(Arrays.asList(lore));
		return this;
	}

	/**
	 * Sets the color of wool, stained glass, or clay.
	 *
	 * @param color The color you are applying to the item.
	 * @return The item with the specified color applied to it.
	 */

	@SuppressWarnings("deprecation")
	public ItemBuilder setColor(DyeColor color) {

		if(itemStack.getType() != Material.WOOL && itemStack.getType() != Material.STAINED_GLASS && itemStack.getType() != Material.STAINED_GLASS_PANE && itemStack.getType() != Material.STAINED_CLAY)
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

		LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;

		leatherArmorMeta.setColor(color);
		itemStack.setItemMeta(leatherArmorMeta);

		return this;
	}

	/**
	 * 
	 * @param hidden Should all ItemFlags be hidden or shown.
	 * @return The item with the modefied itemflags.
	 */
	public ItemBuilder setAllFlags(boolean hidden)
	{
		if (hidden)
		{
			for (ItemFlag flag : ItemFlag.values())
			{
				if (flag == null) continue;
				itemMeta.addItemFlags(flag);
			}
			itemMeta.addItemFlags(ItemFlag.values());
		}
		else
		{
			for (ItemFlag flag : ItemFlag.values())
			{
				if (flag == null) continue;
				if (!itemMeta.hasItemFlag(flag)) continue;
				itemMeta.removeItemFlags(flag);
			}
		}
		return this;
	}

	/**
	 * 
	 * Hides or shows a specific item flag.
	 * 
	 * @param flag The flag that will be shown/hidden.
	 * @param hide If the flag should be hidden or not.
	 * @return The item with the modified ItemFlag.
	 */
	public ItemBuilder setFlag(ItemFlag flag, boolean hide) {

        if (hide && itemStack.hasItemMeta())
            itemMeta.addItemFlags(flag);

        else
            itemMeta.removeItemFlags(flag);
		
		return this;
	}
	
	
	/**
	 * 
	 * Sets the amount of items in an item stack.
	 * 
	 * @param amount The amount of items the itemstack will have.
	 * @return The item with the modified amount.
	 */
	public ItemBuilder setAmount(int amount)
	{
		itemStack.setAmount(amount);
		
		return this;
	}

	/**
	 * @return The built item.
	 */

	public ItemStack build() 
	{
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}