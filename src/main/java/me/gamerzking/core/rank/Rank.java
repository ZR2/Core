package me.gamerzking.core.rank;

import me.gamerzking.core.Core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public enum Rank {

    PLAYER(0, ""),
    ADMIN(1, "&cAdmin"),
    OWNER(1, "&cOwner");

    private Core plugin;

    private int id;
    private String name;

    Rank(int id, String name) {

        this.id = id;
        this.name = name;

        this.plugin = Core.getInstance();
    }

    private static Map<Integer, Rank> ranks = new HashMap<>();

    static {

        for(Rank rank : values()) {
            ranks.put(rank.getId(), rank);
        }
    }

    public String getName(boolean withColor, boolean withBrackets) {

        String out = name;

        if(withColor)
            out = plugin.format(name);

        if(withBrackets)
            out = "[" + name + "]";

        return out;
    }

    public int getId() {
        return id;
    }

    public String getPrefix() {
        return name;
    }

    public static Rank valueOf(int id) {
        return ranks.get(id);
    }
}
