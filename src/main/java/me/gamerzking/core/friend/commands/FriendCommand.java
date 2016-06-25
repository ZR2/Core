package me.gamerzking.core.friend.commands;

import me.gamerzking.core.command.AbstractCommand;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class FriendCommand extends AbstractCommand {

    public FriendCommand() {

        super(
                Rank.PLAYER,
                "f", "friend", "friends");
    }

    @Override
    public void execute(Player sender, String[] args) {

    }

    @Override
    public void help(Player player) {

    }
}