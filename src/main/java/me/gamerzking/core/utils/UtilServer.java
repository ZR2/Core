package me.gamerzking.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public class UtilServer {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilServer() {}

    /**
     * @return The online players in an array.
     */

    public static Player[] getPlayers() {
        return Bukkit.getOnlinePlayers().toArray(new Player[0]);
    }
}
