package me.gamerzking.core.command;

import me.gamerzking.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class CommandManager implements Listener {

    private Map<String, ICommand> commands;

    /**
     * Instantiates the commands and registers the events.
     */

    public CommandManager() {

        commands = new HashMap<>();
        Core.getInstance().registerEvents(this);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        String commandName = event.getMessage().substring(1);
        String[] args = event.getMessage().split(" ");

        ICommand command = commands.get(commandName.toLowerCase());

        // TODO: 5/31/2016 Check if the player has the specific/required rank to execute the command.

        if(command != null) {

            event.setCancelled(true);

            command.execute(event.getPlayer(), args);
        }
    }

    /**
     * Adds and registers the command.
     *
     * @param command The command you're registering.
     */

    public void addCommand(ICommand command) {

        for(String alias : command.getAliases()) {

            commands.put(alias, command);
            System.out.println("Command registered: " + command.getAliases());
        }
    }

    /**
     * Removes and unregisters the command.
     *
     * @param command The command you're removing.
     */

    public void removeCommand(ICommand command) {

        for (String commandRoot : command.getAliases()) {
            commands.remove(commandRoot);
        }
    }

    /**
     * Gets the command by its name.
     *
     * @param name The name of the command.
     * @return The command, if applicable; otherwise, null.
     */

    public ICommand getCommand(String name) {
        return commands.get(name);
    }
}