package me.gamerzking.core.command;

import me.gamerzking.core.rank.Rank;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public abstract class BaseCommand implements ICommand {

    private Rank requiredRank;
    private Rank[] specificRanks;

    private List<String> aliases;

    /**
     * @param requiredRank The rank required to execute the command.
     * @param aliases The aliases associated with the command.
     */

    public BaseCommand(Rank requiredRank, String... aliases) {

        this(requiredRank, null, aliases);
    }

    /**
     * @param requiredRank The rank required to execute the command.
     * @param specificRanks Other specific ranks that have access to the command.
     * @param aliases The aliases associated with the command.
     */

    public BaseCommand(Rank requiredRank, Rank[] specificRanks, String... aliases) {

        this.requiredRank = requiredRank;
        this.specificRanks = specificRanks;

        this.aliases = Arrays.asList(aliases);
    }

    @Override
    public Collection<String> getAliases() {
        return aliases;
    }

    @Override
    public Rank getRequiredRank() {
        return requiredRank;
    }

    @Override
    public Rank[] getSpecificRanks() {
        return specificRanks;
    }
}