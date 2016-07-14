package me.gamerzking.core.utils;

/**
 * Created by GamerzKing on 5/21/2016.
 */
public class UtilNumber {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilNumber() {}

    /**
     * @param s The string that you're determining.
     * @return True if the string is an integer; otherwise, false.
     */

    public static boolean isInteger(String s) {

        try {
            Integer.parseInt(s);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * @param s The string that you're determining.
     * @return True if the string is a double; otherwise, false.
     */

    public static boolean isDouble(String s) {

        try {
            Double.parseDouble(s);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * @param s The string that you're determining.
     * @return True if the string is a float; otherwise, false.
     */

    public static boolean isFloat(String s) {

        try {
            Float.parseFloat(s);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * @param s The string that you're determining.
     * @return True if the string is a long; otherwise, false.
     */

    public static boolean isLong(String s) {

        try {
            Long.parseLong(s);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}