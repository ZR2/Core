package me.gamerzking.core.command;

import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public interface ICommand {

    void execute(Player sender, String[] args);
    void help(Player player);

    Collection<String> getAliases();

    Rank getRequiredRank();
    Rank[] getSpecificRanks();
}
