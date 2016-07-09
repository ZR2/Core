package me.gamerzking.core.utils;

import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

/**
 * Created by GamerzKing on 5/2/2016.
 */

public class UtilPlayer {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilPlayer() {
    }

    /**
     * Resets the player by clearing their effects, clearing their inventory, etc.
     *
     * @param player The player you're resetting.
     */

    public static void reset(Player player) {

        player.setGameMode(GameMode.SURVIVAL);
        UtilInventory.clearInventory(player);

        player.setAllowFlight(false);
        player.setFlySpeed(0.1F);

        player.setSprinting(false);
        player.setSneaking(false);

        player.setFoodLevel(20);
        player.setSaturation(3F);
        player.setExhaustion(0F);

        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());

        player.setFireTicks(0);
        player.setFallDistance(0);

        player.eject();
        player.leaveVehicle();

        player.setLevel(0);
        player.setExp(0F);

        for (PotionEffect potion : player.getActivePotionEffects())
            player.removePotionEffect(potion.getType());

        DisguiseAPI.undisguiseToAll(player);
    }

	/**
	 * Gets the closest player to the {@link Location} provided.
	 *
	 * @param location The location relative to the player.
	 * @param ignore The entities to ignore relative to the location.
	 * @return The nearest player to the location, excluding the ignored entities.
	 */

	public static Player getClosestPlayer(Location location, Entity ignore) {

		double maxDistance = 0;

		for (Player player : location.getWorld().getPlayers()) {

            if (player.isDead())
                continue;

            if (ignore != null && ignore.equals(player))
                continue;

            double distance = UtilMath.distance(player.getLocation(), location);

            if (distance < maxDistance)
                return player;
		}

		return null;
	}

	/**
	 * Gets the entity in the players sight.
	 *
	 * @param player The player whom your getting the vision/sight from.
	 * @param expandBoxes How far the hitboxes should expand.
     * @return Any entities in the players sight. If there are none, returns null.
     */

	public static Entity getEntityInSight(Player player, float expandBoxes) {

		for (Entity e : player.getWorld().getEntities()) {

			Vector toEntity = e.getLocation().toVector().subtract(player.getLocation().toVector());
			Vector direction = player.getLocation().getDirection();

			double dot = toEntity.normalize().dot(direction);

			if (dot > 1 - expandBoxes && dot < 1 + expandBoxes)
				return e;
		}

		return null;
	}

	/**
	 * Sends the designated packet to the specific player.
	 *
	 * @param player The player you're sending the packet to.
	 * @param packet The packet you're sending to the player.
	 */

	public static void sendPacket(Player player, Object packet) {

		try {

			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", UtilReflection.getNmsClass("Packet")).invoke(playerConnection, packet);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}