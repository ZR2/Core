package me.gamerzking.core.collectible.types.morphs;

import me.gamerzking.core.collectible.types.Collectible;
import me.gamerzking.core.collectible.CollectibleManager;
import me.gamerzking.core.collectible.types.CollectibleType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class Morph extends Collectible {

    public Morph(CollectibleManager manager, String name, Material material, byte data, int cost, String... description) {
        super(manager, CollectibleType.MORPH, name, material, data, cost, description);
    }

    public void equipMorph(Player player) {

        getActive().put(player, System.currentTimeMillis());
    }
}