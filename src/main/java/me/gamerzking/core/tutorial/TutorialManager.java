package me.gamerzking.core.tutorial;

import me.gamerzking.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 6/19/2016.
 */
public class TutorialManager implements Listener {


    private Core plugin;
    private List<Tutorial> tutorials = new ArrayList<>();

    /**
     * @param plugin The instance of the core.
     */

    public TutorialManager(Core plugin) {

        plugin.registerEvents(this);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

    /**
     * @return The plugin instance.
     */

    public Core getPlugin() {
        return plugin;
    }

    /**
     * @return The list of all tutorials.
     */

    public List<Tutorial> getTutorials() {
        return tutorials;
    }
}