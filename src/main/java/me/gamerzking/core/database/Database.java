package me.gamerzking.core.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.gamerzking.core.Core;

import java.sql.*;
import java.util.logging.Level;

/**
 * Created by GamerzKing on 6/2/2016.
 */
public class Database {

    private HikariDataSource dataSource;

    private String name, username, password, address;
    private int port;

    /**
     * @param name     The name of the database you're connecting to.
     * @param username The username of the account you're connecting to the database with.
     * @param password The password of the account you're connecting to the database with.
     * @param address  The address of the database you're connecting to.
     * @param port     The port of the database you're connecting to.
     */

    public Database(String name, String username, String password, String address, int port) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.port = port;
    }

    /**
     * Connects to the database.
     */

    public void connect() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + address + ":" + port + "/" + name);
        config.setDriverClassName("com.mysql.jdbc.Driver");

        config.setUsername(username);
        config.setPassword(password);

        dataSource = new HikariDataSource(config);
    }

    /**
     * Disconnects from the database.
     */

    public void disconnect() {

        if(!getDataSource().isClosed())
            return;

        getDataSource().close();
    }


    /**
     * @return Whether the data source is currently connected; true, if not null.
     */

    public boolean isConnected() {
        return getDataSource() != null;
    }

    /**
     * @return The name of the database you're connecting to.
     */

    public String getName() {
        return name;
    }

    /**
     * @return The data source associated with the connection pool.
     */

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}