package me.gamerzking.core.utils;

import me.gamerzking.core.Core;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by GamerzKing on 6/16/2016.
 */
public class UtilEntity {

    /**
     * Silences the specified entity.
     *
     * @param entity The entity you're silencing.
     */

    public static void silence(Entity entity) {

        entity.setMetadata("Silent", new FixedMetadataValue(Core.getInstance(), 1));
    }

    /**
     * Moves the entity towards the designated Location.
     *
     * @param ent    The entity that's being moved.
     * @param target The location the target is moving towards.
     * @param speed  The speed the entity will travel at.
     * @return Whether the entity made it to the location.
     */

    public static boolean moveEntity(Entity ent, Location target, double speed) {

        if (!(ent instanceof Creature))
            return false;

        if (UtilMath.distance(ent.getLocation(), target) < 0.1)
            return false;

        if (UtilMath.distance(ent.getLocation(), target) < 2)
            speed = Math.min(speed, 1);

        //EntityCreature creature = ((CraftCreature) entity).getHandle();
        //creature.getControllerMove().a(target.getX(), target.getY(), target.getZ(), speed);

        return true;
    }
}