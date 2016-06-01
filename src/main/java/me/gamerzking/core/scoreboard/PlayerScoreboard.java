package me.gamerzking.core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class PlayerScoreboard {

    private Player player;

    public PlayerScoreboard(Player player) {

        this.player = player;

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.registerNewObjective("scoreboard", "dummy");

        player.setScoreboard(scoreboard);
    }

    public void removeScorebord(Player player) {

        if (player.getScoreboard().getEntryTeam(player.getName()) != null)
            player.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());

        if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null)
            player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();

        if (player.getScoreboard().getObjective(DisplaySlot.BELOW_NAME) != null)
            player.getScoreboard().getObjective(DisplaySlot.BELOW_NAME).unregister();

        if (player.getScoreboard().getObjective(DisplaySlot.PLAYER_LIST) != null)
            player.getScoreboard().getObjective(DisplaySlot.PLAYER_LIST).unregister();
    }
}