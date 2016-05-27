package me.gamerzking.core.scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import me.gamerzking.core.utils.UtilRandom;
import me.gamerzking.core.utils.UtilServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 4/30/2016.
 */
public class PlayerScoreboard {

    private Scoreboard scoreboard;
    private Objective objective;
    private String title;

    private List<String> elements = new ArrayList<>();

    /**
     * @param title The title of the scoreboard.
     */

    public PlayerScoreboard(String title) {

        this.title = title;
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        objective = scoreboard.registerNewObjective("Obj" + UtilRandom.getRandomInt(999999999), "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(title);
    }

    /**
     * Adds the line to the scoreboard.
     *
     * @param string The text that will be displayed.
     */

    public void addElement(String string) {
        elements.add(abbreviate(string));
    }

    /**
     * Writes a blank line.
     */

    public void blankLine() {
        addElement("");
    }

    /**
     * Shortens the string provided to fit on the scoreboard.
     *
     * @param string The string you are abbreviating.
     * @return The string, no longer than 16 characters.
     */

    private String abbreviate(String string) {
        return string.length() > 16 ? string.substring(0, 16) : string;
    }

    /**
     * Resets the socre at the line specified.
     *
     * @param line The line you are resetting.
     */

    public void resetScore(String line) {
        scoreboard.resetScores(line);
    }

    /**
     * Resets the scoreboard by clearing all of the elements.
     */

    public void resetScoreboard() {
        elements.clear();
    }

    /**
     * Builds the scoreboard by removing duplicate entries, reversing the lines, and setting the scores.
     */

    public void build() {

        List<String> lines = new ArrayList<>();

        for(String line : elements) {
            for(String otherLine : lines) {
                if(line.equals(otherLine)) {

                    line += ChatColor.RESET;
                }
            }
            lines.add(line);
        }

        List<String> reversedLines = Lists.reverse(lines);

        for(int i = 0; i < lines.size(); i++) {

            String line = abbreviate(reversedLines.get(i));
            getObjective().getScore(line).setScore(i + 1);
        }
    }

    /**
     * Sends the scoreboard to the players specified.
     *
     * @param players The players you are sending the scoreboard to.
     */

    public void send(Player... players) {

        for(Player player : UtilServer.getPlayers()) {
            player.setScoreboard(getScoreboard());
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getObjective() {
        return objective;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getElements() {
        return elements;
    }
}