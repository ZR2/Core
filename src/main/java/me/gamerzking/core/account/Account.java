package me.gamerzking.core.account;

import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 7/7/2016.
 */
public class Account {

    /**
     * Every account is assigned an id. This is used to identify players across the network.
     */

    private int accountId;

    /**
     * This is the owner of the account.
     */

    private Player player;

    /**
     * This is the rank associated with the account. Returns player by default.
     */

    private Rank rank = Rank.PLAYER;

    /**
     * @param player The player/owner associated with the account.
     */

    public Account(Player player) {

        this.player = player;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}