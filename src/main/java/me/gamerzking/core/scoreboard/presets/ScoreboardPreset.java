package me.gamerzking.core.scoreboard.presets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public abstract class ScoreboardPreset {

    /**
     * Contains all of the scoreboard elements.
     */

    private List<String> scores = new ArrayList<>();

    public ScoreboardPreset() {}

    /**
     * Updates the current scoreboard elements.
     */

    public abstract void updateScores();

    /**
     * Gets the score at the specified index.
     *
     * @param index The index you're getting the score from.
     * @return The score, if applicable; otherwise, null.
     */

    public String getScore(int index) {
        return scores.get(index);
    }

    public List<String> getScores() {
        return scores;
    }

    public void setScores(List<String> scores) {
        this.scores = scores;
    }
}