package me.gamerzking.core.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by GamerzKing on 7/7/2016.
 */
public class DatabasePool {

    public static final HikariDataSource ACCOUNT = getSource("user7536", "user7536", "ee2f85b765", "5.135.145.49");

    /**
     * Creates a new {@link HikariDataSource} from the information specified.
     *
     * @param name     The name of the database you're connecting to.
     * @param username The username of the account you're connecting to the database with.
     * @param password The password of the account you're connecting to the database with.
     * @param address  The address of the database you're connecting to.
     */

    private static HikariDataSource getSource(String name, String username, String password, String address) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + address + ":" + 3306 + "/" + name);
        config.setDriverClassName("com.mysql.jdbc.Driver");

        config.setUsername(username);
        config.setPassword(password);

        config.setMaximumPoolSize(10);

        config.addDataSourceProperty("autoReconnect", "true");
        config.addDataSourceProperty("allowMultiQueries", "true");

        return new HikariDataSource(config);
    }
}
