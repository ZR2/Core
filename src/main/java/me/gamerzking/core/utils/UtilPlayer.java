package me.gamerzking.core.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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