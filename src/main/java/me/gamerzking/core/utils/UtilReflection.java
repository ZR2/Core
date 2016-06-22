package me.gamerzking.core.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Field;
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
     * Gets a CraftBukkit class by the name specified.
     *
     * @param name The name of the class you're searching for.
     * @return The class, if existing.
     * @throws ClassNotFoundException If the class doesn't exist, or isn't found.
     */

    public static Class<?> getBukkitClass(String name) throws ClassNotFoundException {

        return getClass("org.bukkit.craftbukkit", name);
    }

    /**
     * Gets an NMS class by the name specified.
     *
     * @param name The name of the class you're searching for.
     * @return The class, if existing.
     * @throws ClassNotFoundException If the class doesn't exist, or isn't found.
     */

    public static Class<?> getNmsClass(String name) throws ClassNotFoundException {

        return getClass("net.minecraft.server", name);
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

    /**
     * Gets the field from the class specified.
     *
     * @param clazz The class that may/may not contain the field.
     * @param name The name of the field you're searching for.
     * @return The accessible field (if existing). If it doesn't exist, returns null.
     */

    public static Field getField(Class<?> clazz, String name) {

        try {

            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);

            return field;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the method from the class specified.
     *
     * @param clazz The class that may/may not contain the method.
     * @param name The name of the method you're searching for.
     * @return The accessible method (if existing). If it doesn't exist, returns null.
     */

    public static Method getMethod(Class<?> clazz, String name, Class<?>... args) {

        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(name) && (args.length == 0 || isClass(args, method.getParameterTypes()))) {

                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    /**
     * Gets whether the two classes specified are equal.
     *
     * @param classA The first class you're comparing.
     * @param classB The second class you're comparing.
     * @return True if the classes are the same; otherwise, false.
     */

    private static boolean isClass(Class<?>[] classA, Class<?>[] classB) {

        boolean equal = true;

        if (classA.length != classB.length)
            return false;

        for (int i = 0; i < classA.length; i++) {
            if (classA[i] != classB[i]) {

                equal = false;
                break;
            }
        }

        return equal;
    }
}