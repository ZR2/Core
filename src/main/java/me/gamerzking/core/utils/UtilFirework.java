package me.gamerzking.core.utils;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftFirework;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

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
        ((CraftFirework) firework).getHandle().expectedLifespan = 1;
    }
}