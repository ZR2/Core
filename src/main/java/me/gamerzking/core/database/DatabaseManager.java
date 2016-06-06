package me.gamerzking.core.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 6/2/2016.
 */
public class DatabaseManager {

    private Map<String, Database> databases = new HashMap<>();

    /**
     * Adds the specific database to the list of databases.
     *
     * @param database The database you're adding.
     */

    public void addDatabase(Database database) {

        databases.put(database.getName(), database);
    }

    /**
     * Removes the database with the specified name.
     *
     * @param name The name of the database you're removing.
     */

    public void removeDatabase(String name) {

        if (!databases.containsKey(name))
            return;

        databases.get(name).disconnect();
        databases.remove(name);
    }

    /**
     * Gets the database with the specified name.
     *
     * @param name The name of the database you're getting.
     * @return The database, if it exists; otherwise, null.
     */

    public Database getDatabase(String name) {

        return databases.get(name);
    }
}