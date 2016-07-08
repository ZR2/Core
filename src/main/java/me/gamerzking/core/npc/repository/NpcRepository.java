package me.gamerzking.core.npc.repository;

import me.gamerzking.core.database.DatabasePool;
import me.gamerzking.core.database.repository.Repository;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by GamerzKing on 7/7/2016.
 */
public class NpcRepository extends Repository {

    private static final String CREATE_NPC = "INSERT INTO `npcs` (`name`, `skin`, `tags`, `type`, `helmet`, `chestplate`, `leggings`, `boots`, `inHand`, `x`, `y`, `z`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public NpcRepository(JavaPlugin plugin) {

        super(plugin, DatabasePool.ACCOUNT);
    }

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

        try {

            Connection connection = getConnection();

            if (connection != null) {

                PreparedStatement statement = connection.prepareStatement(CREATE_NPC);

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