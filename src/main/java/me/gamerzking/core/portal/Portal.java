package me.gamerzking.core.portal;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class Portal implements PluginMessageListener {

    private Plugin plugin;

    /**
     * @param plugin The plugin you're sending the message to.
     */

    public Portal(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        if (!channel.equals("BungeeCord"))
            return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
    }

    /**
     * Sends a player to the designated server name.
     *
     * @param player The player you're sending to the server.
     * @param server The server you're sending the player to.
     */

    public void sendToServer(Player player, String server) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Connect");
        out.writeUTF(server);

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}