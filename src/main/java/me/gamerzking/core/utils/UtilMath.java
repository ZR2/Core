package me.gamerzking.core.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by GamerzKing on 7/8/2016.
 */
public class UtilMath {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilMath() {}

    /**
     * Gets the distance between two {@code Locations}
     *
     * @param a The first Location.
     * @param b The second Location.
     * @return the distance between the two locations.
     */

    public static double distance(Location a, Location b) {
        return distance(a.toVector(), b.toVector());
    }

    /**
     * Gets the distance between two {@code Vectors}
     *
     * @param a The first Vector.
     * @param b The second Vector.
     * @return the distance between the two vectors.
     */

    public static double distance(Vector a, Vector b) {
        return a.subtract(b).length();
    }

    /**
     * Returns a vector rotated around the X axis.
     *
     * @param vector The vector you're rotating.
     * @param angle The angle the vector is being rotated at.
     * @return the rotated vector.
     */

    public Vector rotateAroundX(Vector vector, double angle) {

        angle = Math.toRadians(angle);

        double y, z, cos, sin;

        cos = Math.cos(angle);
        sin = Math.sin(angle);

        y = vector.getY() * cos - vector.getZ() * sin;
        z = vector.getY() * sin + vector.getZ() * cos;

        return vector.setY(y).setZ(z);
    }

    /**
     * Returns a vector rotated around the Y axis.
     *
     * @param vector The vector you're rotating.
     * @param angle The angle the vector is being rotated at.
     * @return the rotated vector.
     */

    public Vector rotateAroundY(Vector vector, double angle) {

        angle = Math.toRadians(angle);

        double x, z, cos, sin;

        cos = Math.cos(angle);
        sin = Math.sin(angle);

        x = vector.getX() * cos + vector.getZ() * sin;
        z = vector.getX() * -sin + vector.getZ() * cos;

        return vector.setX(x).setZ(z);
    }

    /**
     * Turns a speed into a vector.
     *
     * @param location The location from which the vector will be calculated.
     * @param speed The speed in meters per second.
     * @return The vector with the speed applied.
     */
    public static Vector getSpeedAsVector(Location location, double speed) {

        Vector direction = location.getDirection();
        return direction.multiply(speed / 20);
    }

    /**
     * Trims the specified double to the degrees specified.
     *
     * @param degree How many decimal places you are trimming to.
     * @param decimal The decimal you're trimming.
     * @return the trimmed decimal.
     */

    public static double trim(int degree, double decimal) {

        String format = "#.#";

        for (int i=1 ; i<degree ; i++)
            format += "#";

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat(format, symbols);

        return Double.valueOf(decimalFormat.format(decimal));
    }
}