package me.gamerzking.core.npc;

import me.gamerzking.core.Core;
import me.gamerzking.core.database.Database;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by GamerzKing on 6/24/2016.
 */
public class NpcManager {

    private Map<UUID, NPC> npcMap;
    private Set<UUID> removing = new HashSet<>();

    /**
     * Adds and creates the NPC.
     *
     * @param player The player creating the NPC.
     * @param name The name of the NPC.
     * @param skin The skin of the NPC.
     * @param tags Additional information the entity will be created with (villager profession, slime size, etc.)
     * @param type The type of entity the NPC will spawn as.
     */

    public void addNpc(Player player, String name, String skin, String tags, EntityType type) {

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("------------------NPC MANAGER--------------------");
        System.out.println("Player: " + player.getName());
        System.out.println("Name: " + name);
        System.out.println("Skin: " + skin);
        System.out.println("Tags: " + tags);
        System.out.println("Type: " + type.toString());
        System.out.println("-------------------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        Database database = Core.getInstance().getDatabaseManager().getDatabase("user7536");

        if (database == null) {

            System.out.println("The database is null!");
            return;
        }

        try {

            Connection connection = database.getDataSource().getConnection();

            if (connection != null) {

                PreparedStatement statement = connection.prepareStatement("INSERT INTO `user7536`.`npcs` (`name`, `skin`, `tags`, `type`, `helmet`, `chestplate`, `leggings`, `boots`, `inHand`, `x`, `y`, `z`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

                String inHand = fromItemStack(player.getInventory().getItemInMainHand());
                String helmet = fromItemStack(player.getInventory().getHelmet());
                String chestplate = fromItemStack(player.getInventory().getChestplate());
                String leggings = fromItemStack(player.getInventory().getLeggings());
                String boots = fromItemStack(player.getInventory().getBoots());

                statement.setString(1, name);
                statement.setString(2, skin);
                statement.setString(3, tags);
                statement.setString(4, type.toString());
                statement.setString(5, helmet);
                statement.setString(6, chestplate);
                statement.setString(7, leggings);
                statement.setString(8, boots);
                statement.setString(9, inHand);
                statement.setInt(10, player.getLocation().getBlockX());
                statement.setInt(11, player.getLocation().getBlockY());
                statement.setInt(12, player.getLocation().getBlockZ());

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the name of the ItemStack specified.
     *
     * @param item The item you're getting the name from.
     * @return The enum value of the items type, if applicable. If the item is null, returns "AIR"
     */

    private String fromItemStack(ItemStack item) {

        if(item == null)
            return "AIR";

        return item.getType().toString();
    }
}