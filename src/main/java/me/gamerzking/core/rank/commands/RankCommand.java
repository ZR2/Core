package me.gamerzking.core.rank.commands;

import me.gamerzking.core.Core;
import me.gamerzking.core.command.AbstractCommand;
import me.gamerzking.core.database.Database;
import me.gamerzking.core.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class RankCommand extends AbstractCommand {

    public RankCommand() {

        super(
                Rank.ADMIN,
                "rank"
        );
    }

    @Override
    public void execute(Player sender, String[] args) {

        sender.sendMessage(sender.getUniqueId().toString());

        if(args == null) {

            // Send available arguments to player
            help(sender);

            return;
        }

        if(args[0].equalsIgnoreCase("get"))
            getRank(sender, args);

        else if(args[0].equalsIgnoreCase("set"))
            setRank(sender, args);
    }

    /**
     * Gets the rank of the user specified.
     *
     * @param player The command executor.
     * @param args The command arguments.
     */

    private void getRank(Player player, String[] args) {

        if(args.length < 2) {

            // Missing the player
            help(player);

            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null) {

            // Notify command executor
            player.sendMessage("That player isn't online!");

            return;
        }

        try {

            Database database = Core.getInstance().getDatabaseManager().getDatabase("user7536");

            if (database == null)
                return;

            Connection connection = database.getDataSource().getConnection();
            ResultSet set = connection.createStatement().executeQuery("SELECT `rank` FROM `accounts` WHERE uuid = '" + target.getUniqueId() + "' LIMIT 1;");

            Bukkit.broadcastMessage(player.getUniqueId().toString());
            Bukkit.broadcastMessage(player.getUniqueId() + "");

            if (set.next()) {

                Rank rank = Rank.valueOf(set.getString(1));
                player.sendMessage(rank.getName(true, false));

            } else {

                player.sendMessage("Invalid name!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the rank of the user specified to the rank specified.
     *
     * @param player The command executor.
     * @param args The command arguments.
     */

    private void setRank(Player player, String[] args) {

    }

    @Override
    public void help(Player player) {

        player.sendMessage("/rank <get/set> <player>");
    }
}