package me.gamerzking.core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class PlayerScoreboard {

    private Scoreboard scoreboard;
    private Objective objective;

    private List<String> lines = new ArrayList<>();

    /**
     * @param title The title of the scoreboard
     */

    public PlayerScoreboard(String title) {

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        objective = scoreboard.registerNewObjective(title, "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BOLD + title);
    }

    /**
     * Adds the specified text to the scoreboard.
     *
     * @param text The text being added to the board.
     */

    public void addLine(String text) {

        text = abbreviate(text); /* Make sure it can't be more than 16 characters */
        lines.add(text);
    }

    public void addBlankLine() {

        addLine(" ");
    }

    /**
     * Abbreviates the string specified.
     *
     * @param line The line you're abbreviating.
     * @return The string with a maximum length of 16 characters.
     */

    private String abbreviate(String line) {

        if (line.length() > 16)
            line = line.substring(0, 16);

        return line;
    }

    /**
     * Resets the current scoreboard by removing all of its elements.
     */

    public void resetScoreboard() {

        lines.clear();
    }

    /**
     * Builds the current scoreboard.
     */

    public void build() {

        List<String> currentLines = new ArrayList<>();

        for(String line : lines) {

            while(true) {

                boolean duplicate = false;

                for(String text : currentLines) {

                    if(line.equals(text) /* There is a duplicate. */) {

                        line += ChatColor.RESET;
                        duplicate = true;
                    }
                }

                if(!duplicate)
                    break;
            }

            currentLines.add(line);
        }

        for(int i = 0; i < 15; i++) {

            if(i >= currentLines.size() /* There are more than 15 lines */) {

                if(getLines().get(i) != null) {
                    getScoreboard().resetScores(getLines().get(i));
                }

                continue;
            }

            if(getLines().get(i) == null || !getLines().get(i).equals(currentLines.get(i)) /* A line was updated or has been removed */) {
                lines.add(currentLines.get(i));
            }
        }

        for(int i = 0; i < lines.size(); i++) {

            String line = lines.get(i);
            getObjective().getScore(line).setScore(15 - i);
        }
    }

    /**
     * @return The scoreboard created, from the class constructor.
     */

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     * @return The scoreboard objective, as defined in the class constructor.
     */

    public Objective getObjective() {
        return objective;
    }

    /**
     * @return The {@link List<String>} of all scoreboard lines.
     */

    public List<String> getLines() {
        return lines;
    }
}