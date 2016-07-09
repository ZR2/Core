package me.gamerzking.core.account.repository;

import me.gamerzking.core.database.DatabasePool;
import me.gamerzking.core.database.repository.Repository;
import me.gamerzking.core.rank.Rank;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by GamerzKing on 6/6/2016.
 */
public class AccountRepository extends Repository {

    private static String CREATE_ACCOUNT_TABLE = "CREATE TABLE `accounts` (`id` INT NOT NULL AUTO_INCREMENT,`uuid` VARCHAR(50) NULL DEFAULT NULL,`coins` INT() NULL DEFAULT NULL,`rank` VARCHAR(50) NULL DEFAULT NULL,`firstLogin` TIMESTAMP NULL DEFAULT NULL,`lastLogin` TIMESTAMP NULL DEFAULT NULL,`totalTime` TIMESTAMP NULL DEFAULT NULL, INDEX `id` (`id`));";
    private static String CREATE_ACCOUNT_PROFILE =  "INSERT INTO `accounts` (uuid, name, firstLogin, lastLogin) VALUES (?, ?, now(), now());";

    private static String UPDATE_RANK = "UPDATE `accounts` SET `rank`= ? WHERE `uuid` = ?";

    public AccountRepository(JavaPlugin plugin) {

        super(plugin, DatabasePool.ACCOUNT);
    }

    /**
     * Sets the rank of the player associated with the unique id to the rank specified.
     *
     * @param uuid The unique id of the player you're setting the rank to.
     * @param rank The rank you're applying to the player.
     */

    public void setRank(UUID uuid, Rank rank) {

        try {

            PreparedStatement statement = getConnection().prepareStatement(UPDATE_RANK);

            statement.setObject(1, rank.toString());
            statement.setObject(2, uuid.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}