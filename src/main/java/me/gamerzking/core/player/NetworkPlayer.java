package me.gamerzking.core.player;

import me.gamerzking.core.Core;
import me.gamerzking.core.rank.Rank;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public class NetworkPlayer {

    private UUID uuid;

    private Rank rank;
    private int balance;

    public NetworkPlayer(UUID uuid) {

        this.rank = Rank.PLAYER;
        this.balance = 0;
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setBalance(int value) {
        this.balance = value;
    }

    public int getBalance() {
        return this.balance;
    }

    public void sendTitle(String title, String subtitle) {
        this.getPlayer().sendMessage(title + ", " + subtitle);
    }

    public void sendActionBar(String message) {

        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
        ((CraftPlayer) this.getPlayer()).getHandle().playerConnection.sendPacket(ppoc);
    }

    public void sendToServer(String serverName) {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(serverName);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.getPlayer().sendPluginMessage(Core.getInstance(), "BungeeCord", b.toByteArray());
    }
}