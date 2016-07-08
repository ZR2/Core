package me.gamerzking.core.utils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 7/7/2016.
 */
public class UtilShape {

    /**
     * Gets a circle and returns the list of locations.
     *
     * Code originally created by: chasechocolate
     * Found on thread: https://bukkit.org/threads/post-your-most-advanced-code.148664/#post-1688525
     *
     * @param location The location of the circle (the center).
     * @param radius   The radius of the circle.
     * @param height   The height of the circle.
     * @param hollow   Whether the circle will be hollow.
     * @param sphere   Whether the circle will be a sphere.
     * @return The list of locations from the data given.
     */

    public static List<Location> getCircle(Location location, int radius, int height, boolean hollow, boolean sphere) {

        List<Location> locations = new ArrayList<>();

        int cx = location.getBlockX();
        int cy = location.getBlockY();
        int cz = location.getBlockZ();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (sphere ? cy - radius : cy); y < (sphere ? cy + radius : cy + height); y++) {

                    double distance = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);

                    if (distance < radius * radius && !(hollow && distance < (radius - 1) * (radius - 1)))
                        locations.add(new Location(location.getWorld(), x, y, z));
                }
            }
        }

        return locations;
    }
}