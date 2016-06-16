package me.gamerzking.core.utils;

import org.bukkit.Bukkit;

/**
 * Created by GamerzKing on 6/7/2016.
 */
public class UtilReflection {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilReflection() {}

    public static Class<?> getClass(String prefix, String name) throws ClassNotFoundException {

        return Class.forName(prefix + Bukkit.getVersion() + "." + name);
    }
}