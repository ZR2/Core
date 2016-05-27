package me.gamerzking.core.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by GamerzKing on 5/2/2016.
 */
public class UtilPlayer {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilPlayer() {}

    /**
     * Gets the closest player to the {@link Location} provided.
     *
     * @param location The location relative to the player.
     * @param ignore The entities to ignore relative to the location.@@
     * @return The nearest player to the location, excluding the ignored entities.
     */

    public static Player getClosestPlayer(Location location, Entity ignore) {

        Player closestPlayer = null;
        double maxDistance = 0;

        for (Player player : location.getWorld().getPlayers()) {

            if (player.isDead())
                continue;

            if (ignore != null && ignore.equals(player))
                continue;

            double distance = player.getLocation().toVector().subtract(location.toVector()).length();

            if (closestPlayer == null || distance < maxDistance) {

                closestPlayer = player;
                maxDistance = distance;
            }
        }
        return closestPlayer;
    }
}