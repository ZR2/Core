package me.gamerzking.core.server.command;

import me.gamerzking.core.command.AbstractCommand;
import me.gamerzking.core.rank.Rank;
import me.gamerzking.core.server.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 7/17/2016.
 */
public class StatusCommand extends AbstractCommand {

    private ServerManager manager;

    public StatusCommand(ServerManager manager) {

        super(
                Rank.ADMIN,
                "status"
        );

        this.manager = manager;
    }

    @Override
    public void execute(Player sender, String[] args) {

        if(args == null)
            help(sender);

        String address = Bukkit.getServer().getIp().isEmpty() ? "localhost" : manager.getPlugin().getServer().getIp();
        int port = manager.getPlugin().getServer().getPort();

        int ram = (int) ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1048576);
        int maxRam = (int) (Runtime.getRuntime().maxMemory() / 1048576);

        sender.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + ram + "/" + maxRam + " mb (" + (ram / maxRam * 100) + ")");
    }

    @Override
    public void help(Player player) {

    }
}