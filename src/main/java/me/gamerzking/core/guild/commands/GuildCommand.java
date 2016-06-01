package me.gamerzking.core.guild.commands;

import me.gamerzking.core.command.CommandBase;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class GuildCommand extends CommandBase {

    public GuildCommand() {

        super(
                Rank.PLAYER,
                "g", "guild");
    }

    @Override
    public void execute(Player player, String[] args) {

        player.sendMessage("Aye!");
    }
}