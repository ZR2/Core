package me.gamerzking.core.rank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public enum Rank {

    PLAYER(0, "");

    private int id;
    private String name;

    private Rank(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, Rank> ranks = new HashMap<>();

    static {

        for(Rank rank : values()) {
            ranks.put(rank.getId(), rank);
        }
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
