package me.gamerzking.core.guild.commands;

import me.gamerzking.core.command.BaseCommand;
import me.gamerzking.core.guild.ui.GuildShop;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class GuildCommand extends BaseCommand {

    private GuildShop shop = new GuildShop();

    public GuildCommand() {

        super(
                Rank.PLAYER,
                "g", "guild");
    }

    @Override
    public void execute(Player sender, String[] args) {

        if(args == null)
            return;

        if(args[0].equalsIgnoreCase("shop"))
            shop.openInventory(sender);
    }
}