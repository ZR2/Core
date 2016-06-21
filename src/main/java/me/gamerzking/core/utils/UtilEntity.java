package me.gamerzking.core.utils;

import me.gamerzking.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
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

    public static void silence(LivingEntity entity) {

        entity.setMetadata("Silent", new FixedMetadataValue(Core.getInstance(), 1));
        Bukkit.broadcastMessage("Set the Metadata of the entity to silent, with a value of: " + entity.getMetadata("Silent"));

        /*

        try {

            Class<?> craftEntity = UtilReflection.getClass("org.bukkit.craftbukkit", "entity.CraftEntity");
            System.out.println("Searching for CraftEntity.java class...");

            Method handle = entity.getClass().getMethod("getHandle");

            Object entityHandle = handle.invoke(entity);
            Class<?> compound = UtilReflection.getClass("net.minecraft.server", "NBTTagCompound");

            Field fieldC = craftEntity.getDeclaredField("c");
            //Field fieldF = craftEntity.getDeclaredField("f");

            fieldC.setAccessible(true);
            fieldC.set("Silent", 1);
            fieldC.setAccessible(false);

        } catch (Exception e) {

            //e.printStackTrace();
            Bukkit.broadcastMessage("Class not found!");
        }

        */
    }

    /**
     * Moves the entity towards the designated Location.
     *
     * @param entity The entity that's being moved.
     * @param target The location the target is moving towards.
     * @param speed  The speed the entity will travel at.
     * @return Whether the entity made it to the location.
     */

/*
    public static boolean moveEntity(Entity entity, Location target, double speed) {

        if (!(entity instanceof Creature))
            return false;

        if (entity.getLocation().toVector().subtract(target.toVector()).length() < 0.1)
            return false;

        if (entity.getLocation().toVector().subtract(target.toVector()).length() < 2)
            speed = Math.min(speed, 1);

        EntityCreature creature = ((CraftCreature) entity).getHandle();
        creature.getControllerMove().a(target.getX(), target.getY(), target.getZ(), speed);

        return true;
    }
    */
}