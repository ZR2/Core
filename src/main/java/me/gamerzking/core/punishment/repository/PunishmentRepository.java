package me.gamerzking.core.punishment.repository;

import me.gamerzking.core.database.Database;
import me.gamerzking.core.punishment.PunishmentCategory;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by GamerzKing on 6/2/2016.
 */
public class PunishmentRepository {

    private Database database;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS punishments (uuid VARCHAR(100), category VARCHAR(100), PRIMARY KEY (uuid));";
    private static final String GET_PUNISHMENTS = "SELECT * FROM punishments WHERE uuid = ?";

    /**
     * Initializes the database and creates the punishment table, if non-existing.
     *
     * @param database The database the table will be created to.
     */

    public PunishmentRepository(Database database) {

        database.executeUpdate(CREATE_TABLE);
        this.database = database;
    }

    public PunishmentCategory getPunishment(Player player) {

        if(!isPunished(player))
            return null;

        try {

            PreparedStatement statement = database.getConnection().prepareStatement(GET_PUNISHMENTS);
            statement.setObject(1, player.getUniqueId());

            ResultSet set = statement.executeQuery();
            return PunishmentCategory.valueOf(set.getInt("category"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isPunished(Player player) {

        try {

            PreparedStatement statement = database.getConnection().prepareStatement(GET_PUNISHMENTS);
            statement.setObject(1, player.getUniqueId());

            ResultSet set = statement.executeQuery();
            return set.first();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Database getDatabase() {
        return database;
    }
}