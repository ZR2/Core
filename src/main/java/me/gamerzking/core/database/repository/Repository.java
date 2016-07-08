package me.gamerzking.core.database.repository;

import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by GamerzKing on 7/7/2016.
 */
public class Repository {

    private JavaPlugin plugin;
    private DataSource pool;

    /**
     * @param plugin The plugin responsible for the database connection.
     * @param pool The database pool you're connecting to.
     */

    public Repository(JavaPlugin plugin, DataSource pool) {

        this.plugin = plugin;
        this.pool = pool;
    }

    /**
     * @return a newly created {@link Connection} object from the pool if a connection can be established; otherwise, null.
     */

    public Connection getConnection() {

        try {
            return pool.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }
}