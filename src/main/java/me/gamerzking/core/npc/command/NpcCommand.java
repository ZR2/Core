package me.gamerzking.core.npc.command;

import me.gamerzking.core.command.AbstractCommand;
import me.gamerzking.core.npc.NpcManager;
import me.gamerzking.core.rank.Rank;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 6/24/2016.
 */
public class NpcCommand extends AbstractCommand {

    private NpcManager manager;

    public NpcCommand(NpcManager manager) {

        super(
                Rank.ADMIN,
                "npc"
        );

        this.manager = manager;
    }

    @Override
    public void execute(Player sender, String[] args) {

        if (args == null) {

            help(sender);
            return;
        }

        if (args[0].equalsIgnoreCase("add"))
            add(sender, args);
    }

    /**
     * Adds the NPC to the database.
     *
     * @param player The command executor.
     * @param args   The command arguments.
     */

    private void add(Player player, String[] args) {

        String name = "";
        String skin = "";
        String tags = "";

        EntityType type = EntityType.PLAYER;

        if (args.length >= 2)
            name = args[1];

        if (args.length >= 3)
            skin = args[2];

        if (args.length >= 4) {

            try {
                type = EntityType.valueOf(args[3].toUpperCase());

            } catch (IllegalArgumentException e) {
                help(player);
            }
        }

        if (args.length >= 5)
            tags = args[4];

        manager.getRepository().addNpc(player, name, skin, tags, type);
    }

    @Override
    public void help(Player player) {

        player.sendMessage("/npc add <name> <skin> <type> <tags>");
    }
}