 package me.gamerzking.core;

import me.gamerzking.core.account.AccountManager;
import me.gamerzking.core.blood.Blood;
import me.gamerzking.core.command.CommandManager;
import me.gamerzking.core.friend.commands.FriendCommand;
import me.gamerzking.core.guild.commands.GuildCommand;
import me.gamerzking.core.npc.NpcManager;
import me.gamerzking.core.npc.command.NpcCommand;
import me.gamerzking.core.party.PartyManager;
import me.gamerzking.core.party.commands.PartyCommand;
import me.gamerzking.core.portal.Portal;
import me.gamerzking.core.punishment.commands.PunishCommand;
import me.gamerzking.core.rank.commands.RankCommand;
import me.gamerzking.core.updater.Updater;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public class Core extends JavaPlugin {

    private static Core instance;

    private Blood blood;
    private Portal portal;
    private Updater updater;

    private AccountManager accountManager;
    private CommandManager commandManager;
    private NpcManager npcManager;
    private PartyManager partyManager;

    @Override
    public void onEnable() {

        instance = this;

        //blood = new Blood(this);
        portal = new Portal(this);
        updater = new Updater(this);

        accountManager = new AccountManager(this);
        commandManager = new CommandManager();
        npcManager = new NpcManager(this);
        partyManager = new PartyManager(this);

        getCommandManager().addCommand(new FriendCommand());
        getCommandManager().addCommand(new GuildCommand());
        getCommandManager().addCommand(new NpcCommand(npcManager));
        getCommandManager().addCommand(new PartyCommand(partyManager));
        getCommandManager().addCommand(new PunishCommand());
        getCommandManager().addCommand(new RankCommand(accountManager));

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", getPortal());
    }

    @Override
    public void onDisable() {

        instance = null;
    }

    /**
     * Formats the given {@link String} into a colored {@link String}.
     *
     * @param text The text you're formatting.
     */

    public String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Un-formats the given {@link String} into a {@link String} without colors.
     *
     * @param text The text you're un-formatting.
     */

    public String unformat(String text) {
        return ChatColor.stripColor(text);
    }

    /**
     * Registers the events in the specified listeners.
     *
     * @param listeners The listeners you're listening for.
     */

    public void registerEvents(Listener... listeners) {

        Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, getInstance()));
    }

    /**
     * Unregisters the events in the specified listeners.
     *
     * @param listeners The listener you're no longer listening for.
     */

    public void unregisterEvents(Listener... listeners) {

        Arrays.stream(listeners).forEach(HandlerList::unregisterAll);
    }

    public Blood getBlood() {
        return blood;
    }

    public Portal getPortal() {
        return portal;
    }

    public Updater getUpdater() {
        return updater;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    private CommandManager getCommandManager() {
        return commandManager;
    }

   public NpcManager getNpcManager() {
        return npcManager;
   }

    private PartyManager getPartyManager() {
        return partyManager;
    }

    public static Core getInstance() {
        return instance;
    }
}