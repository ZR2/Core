package me.gamerzking.core.player;

import me.gamerzking.core.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
}