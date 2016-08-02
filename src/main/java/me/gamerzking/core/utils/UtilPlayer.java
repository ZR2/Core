package me.gamerzking.core.utils;

import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.ChatColor;
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

    private final static int CENTER_PX = 154;

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilPlayer() {}

	public static void sendCenteredMessage(Player player, String message) {

        if(message == null || message.equals(""))
            player.sendMessage("");

        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()) {

            if(c == '§') {
                previousCode = true;

            } else if(previousCode) {
                previousCode = false;

                if(c == 'l' || c == 'L') {
                    isBold = true;

                } else {
                    isBold = false;
                }

            } else {

                DefaultFontInfo fontInfo = DefaultFontInfo.getDefaultFontInfo(c);

                messagePxSize += isBold ? fontInfo.getBoldLength() : fontInfo.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;

        StringBuilder sb = new StringBuilder();

        while(compensated < toCompensate) {

            sb.append(" ");
            compensated += spaceLength;
        }

        player.sendMessage(sb.toString() + message);
    }

    /**
     * Resets the player by clearing their effects, clearing their inventory, etc.
     *
     * @param player The player you're resetting.
     */

    public static void resetPlayer(Player player) {

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

        Player closestPlayer = null;
		double maxDistance = 0;

		for (Player player : location.getWorld().getPlayers()) {

            if (player.isDead())
                continue;

            if (ignore != null && ignore.equals(player))
                continue;

            double distance = UtilMath.distance(player.getLocation(), location);

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
}