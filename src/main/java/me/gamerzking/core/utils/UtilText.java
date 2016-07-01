package me.gamerzking.core.utils;

import org.apache.commons.lang.WordUtils;

import java.util.Collection;

/**
 * Created by GamerzKing on 5/15/2016.
 */
public class UtilText {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilText() {}

    /**
     * Coverts a List to a String.
     *
     * @param inputList The list you are converting.
     * @param comma Whether the output between entries will include commas.
     * @param <T> The generic type from the List.
     * @return The resulting String.
     */

    public static <T> String convertList(Collection<T> inputList, boolean comma) {

        String out = "";

        for (T array : inputList) {
            out += array.toString() + (comma ? ", " : " ");
        }

        if (out.length() > 0) {
            out = out.substring(0, out.length() - (comma ? 2 : 1));
        }

        return out;
    }

    /**
     * Gets whether the character specified is a vowel or not.
     *
     * @param character The character you're checking.
     * @return True if the character provided is a vowel.
     */

    public static boolean isVowel(char character) {

        return character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u';
    }

    /**
     * Gets the friendly name from an enumeration value.
     *
     * @param object The enumeration value you're getting the friendly name from.
     *
     * Example: OBSIDIAN -> Obsidian
     * Example: GLOWSTONE_DUST -> Glowstone Dust
     *
     * @return The name capitalized and spaced.
     */

    public static String getFriendlyName(Object object) {

        return WordUtils.capitalizeFully(object.toString().replace("_", ""));
    }

    /**
     * Parses through the String provided to return the correct value.
     *
     * @param string The String you're parsing.
     *
     * Example: Obsidian -> OBSIDIAN
     * Example: Glowstone Dust -> GLOWSTONE_DUST
     *
     * @return The correctly parsed name.
     */

    public static String parseFriendlyName(String string) {

        return string.replace(" ", "_").toUpperCase();
    }

    /**
     * Gets the amount of uppercase letters in the input.
     *
     * @param input The string you are getting the uppercase count from.
     * @return The amount of uppercase letters within the string.
     */

    public static int upperCaseCount(String input) {

        int count = 0;

        for (int i = 0; i < input.length(); i++) {

            char character = input.charAt(i);

            if (Character.isUpperCase(character))
                count++;
        }
        return count;
    }

    /**
     * Gets the amount of lowercase letters in the input.
     *
     * @param input The string you are getting the lowercase count from.
     * @return The amount of lowercase letters within the string.
     */

    public static int lowerCaseCount(String input) {

        int count = 0;

        for (int i = 0; i < input.length(); i++) {

            char character = input.charAt(i);

            if (Character.isLowerCase(character))
                count++;
        }
        return count;
    }
}
