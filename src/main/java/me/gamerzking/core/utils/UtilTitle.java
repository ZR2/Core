package me.gamerzking.core.utils;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Created by GamerzKing on 6/22/2016.
 */
public class UtilTitle {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilTitle() {}

    /**
     * Sends a title to the designated player.
     *
     * @param player The player you're sending the packet to.
     * @param title The text that will be displayed as the title.
     * @param subtitle The text that will be displayed as the sub-title.
     * @param fadeIn How long it takes for the title to fade in.
     * @param stay How long the title will stay, before fading.
     * @param fadeOut How long it takes for the title to fade out.
     */

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {

        try {

            Object enumTitle = UtilReflection.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object enumSubTitle = UtilReflection.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);

            Object chatComponent = UtilReflection.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}");
            Object subChatComponent = UtilReflection.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}");

            Constructor<?> titleConstructor = UtilReflection.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getConstructor(UtilReflection.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getDeclaredClasses()[0], UtilReflection.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent"), int.class, int.class, int.class);

            Object packetTitle = titleConstructor.newInstance(enumTitle, chatComponent, fadeIn, stay, fadeOut);
            Object packetSubtitle = titleConstructor.newInstance(enumSubTitle, subChatComponent, fadeIn, stay, fadeOut);

            UtilPlayer.sendPacket(player, packetTitle);
            UtilPlayer.sendPacket(player, packetSubtitle);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}