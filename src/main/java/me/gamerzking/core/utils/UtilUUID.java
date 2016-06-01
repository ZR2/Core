package me.gamerzking.core.utils;

import java.util.UUID;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class UtilUUID {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilUUID() {}

    /**
     * Gets the raw unique id without hyphens.
     *
     * @param uuid The unique id you're removing the hyphens from.
     * @return The unique id, as a string, without hyphens.
     */

    public static String getRawUUID(UUID uuid){
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * Hyphenates the unique id.
     *
     * @param uuid The unique id you're hyphenating.
     * @return The unique id, as a string, with hyphens.
     */

    public static String getHyphenatedUUID(String uuid){

        StringBuilder builder = new StringBuilder(uuid);
        int index = builder.length() - 8;

        while (index > 0) {
            builder.insert(index, "-");
            index = index - 8;
        }

        return builder.toString();
    }
}
