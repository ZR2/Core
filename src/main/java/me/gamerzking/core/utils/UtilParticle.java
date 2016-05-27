package me.gamerzking.core.utils;

import net.minecraft.server.v1_9_R2.EnumParticle;
import net.minecraft.server.v1_9_R2.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public class UtilParticle {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilParticle() {}

    /**
     * Sends a particle effect.
     *
     * @param location The location the particle effect will be sent to.
     * @param particleType The type of particle that will be sent.
     * @param offsetX The offset in the x axis to the location.
     * @param offsetY The offset in the y axis to the location.
     * @param offsetZ The offset in the z axis to the location.
     * @param speed The speed of the particle.
     * @param count The amount of particles that will be displayed.
     * @param players The players that will see the particles.
     */

    public static void sendParticle(Location location, EnumParticle particleType, float offsetX, float offsetY, float offsetZ, float speed, int count, Player... players) {

        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particleType, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, count);

        for (Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    /**
     * Sends a colored particle effect.
     *
     * @param location The location the particle effect will be sent to.
     * @param red The red value of color.
     * @param green The green value of color.
     * @param blue The blue value of color.
     * @param players The players that will see the particles.
     */

    public static void sendColorParticle(Location location, float red, float green, float blue, Player... players) {
        sendParticle(location, EnumParticle.REDSTONE, red * 0.01F, green * 0.01F, blue * 0.01F, 1.0F, 0, players);
    }
}