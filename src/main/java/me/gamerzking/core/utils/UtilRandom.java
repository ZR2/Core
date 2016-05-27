package me.gamerzking.core.utils;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by GamerzKing on 4/16/2016.
 */
public class UtilRandom {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilRandom() {}

    private static Random random = new Random();

    /**
     * Generates a random number.
     *
     * @param max The maximum number that can be generated.
     * @return The randomly generated number, no higher than the max value.
     */

    public static int getRandomInt(int max) {
        return getRandom().nextInt(max);
    }

    /**
     * Generates a random double.
     *
     * @param min The minimum number that can be generated.
     * @param max The maximum number that can be generated.
     * @return The randomly generated number, no lower than the min value, and no higher than the max value.
     */

    public static double getRangedDouble(double min, double max) {
        return min + (getRandom().nextDouble() * Math.abs(max - min));
    }

    /**
     * Gets a random element from a List.
     *
     * @param list The list you are getting the random element from.
     * @param <T> The generic type within the List.
     * @return The random element from the List.
     */

    public static <T> T getRandomElement(List<T> list) {
        return list.get(getRandom().nextInt(list.size()));
    }

    /**
     * Gets a random element from an array.
     *
     * @param array The array you are getting the random element from.
     * @param <T> The generic type of array.
     * @return The random element from the array.
     */

    public static <T> T getRandomElement(T[] array) {
        return array[getRandom().nextInt(array.length)];
    }

    /**
     * @return The random instance (instance variable)
     */

    public static Random getRandom() {
        return random;
    }
}