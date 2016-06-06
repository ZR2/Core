package me.gamerzking.core.rank.commands;

import me.gamerzking.core.command.BaseCommand;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class RankCommand extends BaseCommand {

    public RankCommand() {

        super(
                Rank.PLAYER,
                "rank"
        );
    }

    @Override
    public void execute(Player sender, String[] args) {

    }
}