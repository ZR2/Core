package me.gamerzking.core.punishment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 6/2/2016.
 */
public enum PunishmentCategory {

    WARNING(1, "Warning"),
    MUTE(2, "Mute"),
    BAN(3, "Ban");

    private int id;
    private String name;

    private static Map<Integer, PunishmentCategory> values = new HashMap<>();

    /**
     * @param id The id of the punishment.
     * @param name The name of the punishment.
     */

    PunishmentCategory(int id, String name) {

        this.id = id;
        this.name = name;
    }

    static {

        for(PunishmentCategory category : values()) {
            values.put(category.getId(), category);
        }
    }

    /**
     * Gets the category associated with the index specified.
     *
     * @param i The index of the category.
     * @return The category, if applicable; otherwise, null.
     */

    public static PunishmentCategory valueOf(int i) {
        return values.get(i);
    }

    /**
     * @return The id of the punishment.
     */

    public int getId() {
        return id;
    }

    /**
     * @return The name of the punishment.
     */

    public String getName() {
        return name;
    }
}