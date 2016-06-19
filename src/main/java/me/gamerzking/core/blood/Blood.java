package me.gamerzking.core.blood;

import me.gamerzking.core.Core;
import me.gamerzking.core.utils.UtilReflection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by GamerzKing on 6/18/2016.
 */
public class Blood implements Listener {

    private static Method handle, sendPacket;
    private static Method center, distance, time, movement;
    private static Field playerConnection;
    private static Constructor<?> constructor, borderConstructor;
    private static Object constant;

    private Core plugin;

    /**
     * @param plugin The instance of the plugin you're listening for.
     */

    public Blood(Core plugin) {

        this.plugin = plugin;
        plugin.registerEvents(this);
    }

    static {
        try {

            handle = UtilReflection.getClass("org.bukkit.craftbukkit", ".entity.CraftPlayer").getMethod("getHandle");
            playerConnection = UtilReflection.getClass("net.minecraft.server", "EntityPlayer").getField("playerConnection");

            for (Method method : UtilReflection.getClass("net.minecraft.server", "PlayerConnection").getMethods()) {

                if (method.getName().equals("sendPacket")) {

                    sendPacket = method;
                    break;
                }
            }

            Class<?> enumBorder = UtilReflection.getClass("net.minecraft.server", "EnumWorldBorderAction");

            constructor = UtilReflection.getClass("net.minecraft.server", "PacketPlayOutWorldBorder").getConstructor(UtilReflection.getClass("net.minecraft.server", "WorldBorder"), enumBorder);
            borderConstructor = UtilReflection.getClass("net.minecraft.server", "WorldBorder").getConstructor();

            Method[] methods = UtilReflection.getClass("net.minecraft.server", "WorldBorder").getMethods();

            String setCenter = "setCenter";
            String setWarningDistance = "setWarningDistance";
            String setWarningTime = "setWarningTime";
            String transitionSizeBetween = "transitionSizeBetween";

            if (UtilReflection.containsMethod(methods, transitionSizeBetween))
                transitionSizeBetween = "a";

            if (UtilReflection.containsMethod(methods, setWarningTime))
                setWarningTime = "b";

            if (UtilReflection.containsMethod(methods, setWarningDistance))
                setWarningDistance = "c";

            if (UtilReflection.containsMethod(methods, setCenter))
                setCenter = "c";

            center = UtilReflection.getClass("net.minecraft.server", "WorldBorder").getMethod(setCenter, Double.class, Double.class);
            distance = UtilReflection.getClass("net.minecraft.server", "WorldBorder").getMethod(setWarningDistance, Integer.class);
            time = UtilReflection.getClass("net.minecraft.server", "WorldBorder").getMethod(setWarningTime, Integer.class);
            movement = UtilReflection.getClass("net.minecraft.server", "WorldBorder").getMethod(transitionSizeBetween, Double.class, Double.class, Long.class);

            for (Object o : enumBorder.getEnumConstants()) {
                if (o.toString().equals("INITIALIZE")) {

                    constant = o;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the border to the specific player to the specified percentage.
     *
     * @param player     The player the border is being sent to.
     * @param percentage The intensity of the border warning.
     */

    private void setEffect(Player player, int percentage) {

        int distance = -10000 * percentage + 1300000;
        sendWorldBorderPacket(player, distance, 200000, 200000, 0);

        System.out.println("Set " + percentage + "% tint for player: " + player.getName());
    }

    /**
     * Sends the border to the player, and fades after time.
     *
     * @param player     The player you're sending the border to.
     * @param percentage The intensity percentage of the border.
     */

    private void sendEffect(Player player, int percentage) {

        setEffect(player, percentage);
        fadeEffect(player, percentage, 5);
    }

    /**
     * Fades the current world border blood effect from the player.
     *
     * @param player     The player you're removing the border from.
     * @param percentage The percentage/speed of the blood effect fade.
     * @param time       The time it will take to fade.
     */

    private void fadeEffect(Player player, int percentage, long time) {

        int distance = -10000 * percentage + 1300000;
        sendWorldBorderPacket(player, 0, 200000D, distance, 1000 * time + 4000);

        System.out.println("Sent fade border for player: " + player.getName());
    }

    /**
     * Removes the blood effect from the player specified.
     *
     * @param player The player you're removing the effect from.
     */

    private void removeEffect(Player player) {

        sendWorldBorderPacket(player, 0, 200000D, 200000D, 0);

        System.out.println("Removed effect from player: " + player.getName());
    }

    /**
     * Sends the world border effect to the player specified.
     *
     * @param player         The player you're sending the packet to.
     * @param borderDistance The distance of the world border, relative to the player.
     * @param oldRadius      The old world border distance.
     * @param newRadius      The new world border distance.
     * @param delay          The delay/time it will take to take effect.
     */

    private void sendWorldBorderPacket(Player player, int borderDistance, double oldRadius, double newRadius, long delay) {

        try {

            Object worldBorder = borderConstructor.newInstance(this);

            center.invoke(worldBorder, player.getLocation().getX(), player.getLocation().getY());
            distance.invoke(worldBorder, borderDistance);
            time.invoke(worldBorder, 15);
            movement.invoke(worldBorder, oldRadius, newRadius, delay);

            Object packet = constructor.newInstance(worldBorder, constant);
            sendPacket.invoke(playerConnection.get(handle.invoke(player)), packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        double health = player.getHealth();
        double maxHealth = player.getMaxHealth();

        sendEffect(player, (int) ((health * 100) / maxHealth));
    }

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        sendEffect(player, (int) (player.getHealth() + event.getAmount()));
    }
}