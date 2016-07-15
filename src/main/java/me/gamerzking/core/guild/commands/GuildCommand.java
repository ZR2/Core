package me.gamerzking.core.guild.commands;

import me.gamerzking.core.command.AbstractCommand;
import me.gamerzking.core.guild.GuildManager;
import me.gamerzking.core.guild.ui.GuildShop;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 5/31/2016.
 */
public class GuildCommand extends AbstractCommand {

    private GuildShop shop;

    public GuildCommand(GuildManager manager) {

        super(
                Rank.PLAYER,
                "g", "guild"
        );

        shop = new GuildShop(manager.getPlugin());
    }

    @Override
    public void execute(Player sender, String[] args) {

        if(args == null)
            return;

        if(args[0].equalsIgnoreCase("shop"))
            shop.openInventory(sender);
    }

    @Override
    public void help(Player player) {

    }
}