package me.gamerzking.core.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;

/**
 * Created by GamerzKing on 6/7/2016.
 */
public class UtilReflection {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilReflection() {}

    /**
     * Gets the specific class with the prefix, the server version, and the class name.
     *
     * @param prefix The prefix of the class (the package).
     * @param name The name of the class.
     * @return The class with the prefix and the name.
     * @throws ClassNotFoundException If the class doesn't exist, or isn't found.
     */

    public static Class<?> getClass(String prefix, String name) throws ClassNotFoundException {

        return Class.forName(prefix + "." + Bukkit.getVersion() + "." + name);
    }

    /**
     * Determines whether the array of methods contains the method name specified.
     *
     * @param methods The array of methods you're searching through.
     * @param methodName The method name you're searching for.
     * @return Whether the array of methods contains the method name.
     */

    public static boolean containsMethod(Method[] methods, String methodName) {

        for (Method m : methods) {
            if (m.getName().equals(methodName)) {

                return true;
            }
        }
        return false;
    }
}