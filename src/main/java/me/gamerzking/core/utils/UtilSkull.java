package me.gamerzking.core.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class UtilSkull {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilSkull() {
    }

    /**
     * Gets a skull from a URL.
     *
     * @param url The URL you're getting the skull from.
     * @return The skull with the skins texture encoded.
     */

    public ItemStack getSkull(String url) {

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        if (url.isEmpty())
            return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {

            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        head.setItemMeta(headMeta);
        return head;
    }
}