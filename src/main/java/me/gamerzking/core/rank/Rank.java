package me.gamerzking.core.rank;

import net.md_5.bungee.api.ChatColor;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public enum Rank {

    PLAYER(""),

    HELPER("&9Helper"),
    MOD("&2Mod"),

    ADMIN("&cAdmin"),
    OWNER("&cOwner");

    private String name;

    /**
     * @param name The name of the rank.
     */

    Rank(String name) {

        this.name = name;
    }

    /**
     * Gets the tag/prefix of the rank.
     *
     * @param withColor Whether the String will return with color.
     * @param withBrackets Whether the String will return with surrounding brackets.
     * @return The name with color/brackets, as specified.
     */

    public String getName(boolean withColor, boolean withBrackets) {

        String out = name;

        if (withColor)
            out = ChatColor.translateAlternateColorCodes('&', name);

        if (withBrackets)
            out = "[" + name + "]";

        return out;
    }
}
