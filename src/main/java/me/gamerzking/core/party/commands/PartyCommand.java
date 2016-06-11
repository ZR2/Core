package me.gamerzking.core.party.commands;

import me.gamerzking.core.Core;
import me.gamerzking.core.command.BaseCommand;
import me.gamerzking.core.party.Party;
import me.gamerzking.core.party.PartyManager;
import me.gamerzking.core.party.PartyRank;
import me.gamerzking.core.rank.Rank;
import me.gamerzking.core.utils.UtilText;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class PartyCommand extends BaseCommand {

    private PartyManager manager;

    public PartyCommand(PartyManager manager) {

        super(
                Rank.PLAYER,
                "p", "party"
        );

        this.manager = manager;
    }

    @Override
    public void execute(Player sender, String[] args) {

        if (args == null || args.length < 0) {

            // Send available arguments to the sender
            help(sender);

            Party party = manager.createParty(sender);
            party.joinParty(sender);

            return;
        }

        if (args[0].equalsIgnoreCase("help"))
            help(sender);

        else if (args[0].equalsIgnoreCase("invite"))
            invite(sender, args);

        else if (args[0].equalsIgnoreCase("leave"))
            leave(sender, args);

        else if (args[0].equalsIgnoreCase("list"))
            list(sender, args);

        else if (args[0].equalsIgnoreCase("promote"))
            promote(sender, args);

        else if (args[0].equalsIgnoreCase("remove"))
            remove(sender, args);

        else if (args[0].equalsIgnoreCase("accept"))
            accept(sender, args);

        else if (args[0].equalsIgnoreCase("disband"))
            disband(sender, args);

        else if (args[0].equalsIgnoreCase("warp"))
            warp(sender, args);

        else if (args[0].equalsIgnoreCase("settings"))
            return;
    }

    /**
     * Invites the specified player to the party.
     *
     * @param player The sender of the command.
     * @param args   The arguments of the command.
     */

    private void invite(Player player, String[] args) {

        Party party = manager.getParty(player);

        if (party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        if (party.getRank(player.getName()) != PartyRank.LEADER) {

            player.sendMessage("Only party leaders can do this!");
            return;
        }

        if (args.length < 2) {

            player.sendMessage("You must provide a valid username!");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null)
            return;

        if (target.getName().equals(player.getName())) {

            player.sendMessage("You cannot invite yourself!");
            return;
        }

        party.invitePlayer(target);

        target.sendMessage(player.getName() + " invited you to their party!");
        new FancyMessage("Click here ").color(ChatColor.GREEN).command("party accept " + player.getName()).then("to join the party!").color(ChatColor.GRAY).send(target);
    }

    /**
     * Forces the specified player to leave the party.
     *
     * @param player The player that is leaving.
     * @param args   The arguments of the command.
     */

    private void leave(Player player, String[] args) {

        Party party = manager.getParty(player);

        if (party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        if (party.getMembers().size() > 1) {

            party.broadcastMessage(player.getName() + " left the party!");
            party.leaveParty(player);

        } else {

            disband(player, args);
        }
    }

    /**
     * Lists all of the online party members to the command executor.
     *
     * @param player The players party you're listing the members to.
     * @param args   The arguments of the command.
     */

    private void list(Player player, String[] args) {

        List<String> members = new ArrayList<>();

        Party party = manager.getParty(player);

        if (party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        for (String name : party.getMembers().keySet()) {

            Player member = Bukkit.getPlayer(name);

            ChatColor color = member.isOnline() ? ChatColor.GREEN : ChatColor.RED;
            members.add(color + member.getName());
        }

        player.sendMessage(UtilText.convertList(members, true));
    }

    /**
     * Promotes the player to the party leader.
     *
     * @param player The command sender.
     * @param args   The arguments of the command.
     */

    private void promote(Player player, String[] args) {

        Party party = manager.getParty(player);

        if (party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        if (party.getRank(player.getName()) != PartyRank.LEADER) {

            player.sendMessage("Only party leaders can do this!");
            return;
        }

        if (args.length < 2) {

            player.sendMessage("You must provide a valid username!");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null)
            return;

        if (target.getName().equals(player.getName())) {

            player.sendMessage("You are already a party leader!");
            return;
        }

        if (!party.equals(manager.getParty(target))) {
            player.sendMessage(target.getName() + "is not in the party! You must invite them first!");
        }

        party.setRank(target, PartyRank.LEADER);
        party.setRank(player, PartyRank.MEMBER);
    }

    /**
     * Removes the specified player from the party.
     *
     * @param player The command sender.
     * @param args The arguments of the command.
     */

    public void remove(Player player, String[] args) {

        Party party = manager.getParty(player);

        if (party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        if (party.getRank(player.getName()) != PartyRank.LEADER) {

            player.sendMessage("Only party leaders can do this!");
            return;
        }

        if (args.length < 2) {

            player.sendMessage("You must provide a valid username!");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null)
            return;

        if (!party.equals(manager.getParty(target))) {
            player.sendMessage(target.getName() + "is not in the party! You must invite them first!");
        }

        party.kickPlayer(player);
    }

    /**
     * Accepts a party invite from the player.
     *
     * @param player The command sender.
     * @param args The arguments of the command.
     */

    private void accept(Player player, String[] args) {

        if (args.length < 2) {

            player.sendMessage("You must enter a valid username!");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null)
            return;

        Party party = manager.getParty(target);

        if (party == null) {

            player.sendMessage("Not in a party! Not invited!");
            return;
        }

        if (!party.isInvited(player.getName()))
            player.sendMessage("You weren't invited!");

        else
            party.joinParty(player);
    }

    /**
     * Warps all of the party members to the leaders lobby.
     *
     * @param player The leader of the party. All party members will be sent to their lobby.
     * @param args The arguments of the command.
     */

    private void warp(Player player, String[] args) {

        Party party = manager.getParty(player);

        if (party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        for(String name : party.getMembers().keySet()) {

            if (name.equals(party.getLeader()))
                return;

            Player member = Bukkit.getPlayer(name);
            Player leader = Bukkit.getPlayer(party.getLeader());

            if(member == null || leader == null)
                return;

            Core.getInstance().getPortal().sendToServer(member, leader.getServer().getServerName());
        }
    }

    /**
     * Disbands the party specified.
     *
     * @param player The leader of the party you're disbanding.
     * @param args The arguments of the command.
     */

    private void disband(Player player, String[] args) {

        Party party = manager.getParty(player);

        if(party == null) {

            player.sendMessage("You are not in a party!");
            return;
        }

        if(party.getRank(player.getName()) != PartyRank.LEADER) {

            player.sendMessage("Only leaders can disband the party!");
            return;
        }

        manager.disbandParty(party);
    }

    /**
     * Sends the available arguments to the player.
     *
     * @param player The player you're sending the help to.
     */

    private void help(Player player) {

        player.sendMessage("This will eventually");
        player.sendMessage("become the help message.");
    }
}