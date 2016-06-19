package me.gamerzking.core.utils;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by GamerzKing on 4/15/2016.
 */
public class UtilFirework {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilFirework() {}

    /**
     * Plays a firework effect at the designated location.
     *
     * @param location The location the firework effect will be played at.
     * @param fireworkEffect The effect you are projecting at the location.
     */

    public static void playFirework(Location location, FireworkEffect fireworkEffect) {

        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta data = firework.getFireworkMeta();

        data.clearEffects();
        data.setPower(1);
        data.addEffect(fireworkEffect);

        firework.setFireworkMeta(data);

        try {

            Class<?> entityFireworkClass = UtilReflection.getClass("net.minecraft.server.", "EntityFireworks");
            Class<?> craftFireworkClass = UtilReflection.getClass("org.bukkit.craftbukkit", "entity.CraftFirework");

            Object objectFirework = craftFireworkClass.cast(firework);
            Method handle = firework.getClass().getMethod("getHandle");

            Object entityFirework = handle.invoke(firework);

            Field expectedLifespan = entityFireworkClass.getDeclaredField("expectedLifespan");
            Field ticksFlown = entityFireworkClass.getDeclaredField("ticksFlown");

            ticksFlown.setAccessible(true);
            ticksFlown.setInt(entityFirework, expectedLifespan.getInt(entityFirework) - 1);
            ticksFlown.setAccessible(false);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}