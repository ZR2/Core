package me.gamerzking.core.collectible.types;

import me.gamerzking.core.itemstack.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public enum CollectibleType {

    GADGET("Gadget", Material.ENCHANTED_BOOK, (byte) 0, "Hey!"),
    MORPH("Morph", Material.BLAZE_POWDER, (byte) 0, "Poof!");

    private String name;
    private String[] description;

    private Material material;
    private byte data;

    CollectibleType(String name, Material material, byte data, String... description) {

        this.name = name;
        this.description = description;

        this.material = material;
        this.data = data;
    }

    public ItemStack getItem() {

        ItemBuilder builder = new ItemBuilder(material);

        builder.setName(name);
        builder.setLore(description);

        builder.setDurability(data);

        return builder.build();
    }
}