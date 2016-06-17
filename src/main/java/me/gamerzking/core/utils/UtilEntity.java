package me.gamerzking.core.utils;

import net.minecraft.server.v1_9_R2.EntityCreature;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftCreature;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

/**
 * Created by GamerzKing on 6/16/2016.
 */
public class UtilEntity {

    /**
     * Moves the entity towards the designated Location.
     *
     * @param entity The entity that's being moved.
     * @param target The location the target is moving towards.
     * @param speed The speed the entity will travel at.
     * @return Whether the entity made it to the location.
     */

    public static boolean moveEntity(Entity entity, Location target, double speed) {

        if (!(entity instanceof Creature))
            return false;

        if (entity.getLocation().toVector().subtract(target.toVector()).length() < 0.1)
            return false;

        if (entity.getLocation().toVector().subtract(target.toVector()).length() < 2)
            speed = Math.min(speed, 1);

        EntityCreature ec = ((CraftCreature)entity).getHandle();
        ec.getControllerMove().a(target.getX(), target.getY(), target.getZ(), speed);

        return true;
    }
}