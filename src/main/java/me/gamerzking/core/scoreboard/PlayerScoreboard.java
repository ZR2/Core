package me.gamerzking.core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class PlayerScoreboard {

    private String title;

    private Scoreboard scoreboard;
    private Objective objective;

    private List<String> entries;
    private List<Team> teams;

    private Map<String, Integer> scores;

    /**
     * @param title The title of the scoreboard
     */

    public PlayerScoreboard(String title) {

        this.title = title;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BOLD + title);

        this.entries = new ArrayList<>();
        this.teams = new ArrayList<>();

        this.scores = new HashMap<>();
    }

    /**
     * Adds the specified entry to the scoreboard.
     *
     * @param text The text you're adding to the board. Cannot be more than 48 characters.
     */

    public void addEntry(String text) {

        addEntry(text, null);
    }

    /**
     * Adds the specified entry to the scoreboard.
     *
     * @param text The text you're adding to the board. Cannot be more than 48 characters.
     * @param score The index the score will appear at on the scoreboard.
     */

    public void addEntry(String text, Integer score) {

        scores.put(text, score);
    }

    public String getTitle() {
        return title;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getObjective() {
        return objective;
    }

    public List<String> getEntries() {
        return entries;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }
}