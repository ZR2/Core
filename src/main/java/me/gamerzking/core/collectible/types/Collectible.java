package me.gamerzking.core.collectible.types;

import me.gamerzking.core.collectible.CollectibleManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public abstract class Collectible implements Listener {

    private CollectibleManager manager;
    private CollectibleType type;

    private String name;
    private String[] description;

    private Material material;
    private short data;

    private int cost;

    private Map<Player, Long> active = new HashMap<>();

    public Collectible(CollectibleManager manager, CollectibleType type, String name, Material material, short data, int cost, String... description) {

        this.manager = manager;
        this.type = type;

        this.name = name;
        this.description = description;

        this.material = material;
        this.data = data;

        this.cost = cost;

        manager.getPlugin().getServer().getPluginManager().registerEvents(this, manager.getPlugin());
    }

    public CollectibleManager getManager() {
        return manager;
    }

    public CollectibleType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public Material getMaterial() {
        return material;
    }

    public short getData() {
        return data;
    }

    public int getCost() {
        return cost;
    }

    public Map<Player, Long> getActive() {
        return active;
    }
}