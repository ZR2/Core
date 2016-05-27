package me.gamerzking.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;

/**
 * Created by GamerzKing on 5/21/2016.
 */
public class UtilColor {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilColor() {}

    /**
     * Gets the byte data from the ChatColor provided.
     *
     * @param color The color you're getting the data from.
     * @return The data from the specified color.
     */

    public static byte getColorData(ChatColor color) {

        switch(color) {

            case WHITE: return (byte) 0;
            case GOLD: return (byte) 1;
            case LIGHT_PURPLE: return (byte) 2;
            case AQUA: return (byte) 3;
            case YELLOW: return (byte) 4;
            case GREEN: return (byte) 5;
            case DARK_GRAY: return (byte) 7;
            case GRAY: return (byte) 8;
            case DARK_AQUA: return (byte) 9;
            case DARK_PURPLE: return (byte) 10;
            case BLUE: return (byte) 11;
            case DARK_BLUE: return (byte) 11;
            case DARK_GREEN: return (byte) 13;
            case RED: return (byte) 14;
            case DARK_RED: return (byte) 14;

            default: return (byte) 15;
        }
    }

    /**
     * Gets the color base from the ChatColor provided.
     *
     * @param color The ChatColor you are getting the color from.
     * @return The {@link Color} closest to the specified color.
     */

    public static Color getColorBase(ChatColor color) {

        switch (color) {

            case WHITE: return Color.WHITE;
            case GOLD: return Color.ORANGE;
            case LIGHT_PURPLE: return Color.PURPLE;
            case AQUA: return Color.AQUA;
            case YELLOW: return Color.YELLOW;
            case GREEN: return Color.GREEN;
            case DARK_GRAY: return Color.GRAY;
            case GRAY: return Color.GRAY;
            case DARK_AQUA: return Color.AQUA;
            case DARK_PURPLE: return Color.PURPLE;
            case BLUE: return Color.BLUE;
            case DARK_BLUE: return Color.BLUE;
            case DARK_GREEN: return Color.GREEN;
            case RED: return Color.RED;
            default: return Color.WHITE;
        }
    }
}