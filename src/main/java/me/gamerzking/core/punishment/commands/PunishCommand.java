package me.gamerzking.core.punishment.commands;

import me.gamerzking.core.command.AbstractCommand;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class PunishCommand extends AbstractCommand {

    //private PunishmentRepository repository;

    public PunishCommand(/*PunishmentRepository repository*/) {

        super(
                Rank.PLAYER,
                "punish"
        );

        //this.repository = repository;
    }

    @Override
    public void execute(Player sender, String[] args) {

        if(args == null)
            return;

        //sender.sendMessage(String.valueOf(repository.isPunished(sender)));
    }

    @Override
    public void help(Player player) {

    }
}