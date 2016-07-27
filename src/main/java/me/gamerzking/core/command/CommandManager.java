package me.gamerzking.core.command;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class CommandManager implements Listener {

    private Map<String, ICommand> commands;

    /**
     * Instantiates the Map of commands and registers the events.
     */

    public CommandManager(JavaPlugin plugin) {

        commands = new HashMap<>();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        String commandName = event.getMessage().substring(1);
        String[] args = null;

        if (commandName.contains(" ")) {

            commandName = commandName.split(" ")[0];
            args = event.getMessage().substring(event.getMessage().indexOf(' ') + 1).split(" ");
        }

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

        for (String alias : command.getAliases())
            commands.put(alias, command);

        System.out.println("Registered commands: " + command.getAliases() + " from " + command.getClass().getSimpleName());
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