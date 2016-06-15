package me.gamerzking.core.scoreboard;

import me.gamerzking.core.scoreboard.presets.ScoreboardPreset;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class PlayerScoreboard {

    private Player player;

    private ScoreboardPreset preset;
    private boolean presetChanged = false;

    private List<String> previousScores;

    /**
     * @param player The owner of the scoreboard. Mainly used for per player statistics and other data.
     */

    public PlayerScoreboard(Player player, ScoreboardPreset preset) {

        this.player = player;

        // Create a new scoreboard
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        // Register objectives
        scoreboard.registerNewObjective("suffix", "dummy").setDisplaySlot(DisplaySlot.PLAYER_LIST);
        scoreboard.registerNewObjective("sidebar", "dummy").setDisplaySlot(DisplaySlot.SIDEBAR);

        this.preset = preset;
        updateScoreboard();
    }

    /**
     * Removes the current scoreboard from the player by un-registering all of the display slots from their scoreboard.
     *
     * @param player The owner of the scoreboard.
     */

    public void removeScorebord(Player player) {

        for (DisplaySlot slot : DisplaySlot.values()) {

            if (slot == null) /* If any of the display slots are null, do nothing with them */
                return;

            // Unregister the slot.
            player.getScoreboard().getObjective(slot).unregister();
        }

        player.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
    }

    /**
     * Updates the current scoreboard.
     */

    public void updateScoreboard() {

        Objective objective = getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR);

        if(objective == null /* If it was un-registered, removed, or any other event */)
            return;

        objective.setDisplayName(preset.getScore(0));

        if (!presetChanged) {

            // Update the preset
            updatePreset();

        } else {

            if (previousScores == null)
                return;

            // Reset previous scores
            for (String previousScore : previousScores) objective.getScoreboard().resetScores(previousScore);

            for (int i = 1; i < preset.getScores().size(); i++) {

                String line = preset.getScore(i);

                if (line.length() > 40)
                    line = line.substring(0, 39); /* The line can't be more than 40 characters */

                // Set scores
                objective.getScore(line).setScore(16 - i);
            }
        }
    }

    /**
     * Updates the current preset by recording the previous entries, and updating to new entries.
     */

    public void updatePreset() {

        previousScores = new ArrayList<>(preset.getScores());
        preset.updateScores();
    }

    /**
     * @return The owner of the scoreboard.
     */

    public Player getPlayer() {
        return player;
    }

    /**
     * @return The current scoreboard preset.
     */

    public ScoreboardPreset getPreset() {
        return preset;
    }

    /**
     * @param preset The preset you're setting/updating
     */

    public void setPreset(ScoreboardPreset preset) {

        // Updates the preset
        this.preset = preset;

        // Save previous scores
        previousScores = new ArrayList<>(getPreset().getScores());

        // Change status
        presetChanged = true;

        // Update scoreboard
    }
}