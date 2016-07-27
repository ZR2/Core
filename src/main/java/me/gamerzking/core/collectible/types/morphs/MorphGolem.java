package me.gamerzking.core.collectible.types.morphs;

import me.gamerzking.core.collectible.CollectibleManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class MorphGolem extends Morph {

    public MorphGolem(CollectibleManager manager) {

        super(manager,

                "Golem Morph",

                Material.IRON_INGOT,
                (byte) 0,

                50,

                ChatColor.GRAY + "Right-Click other players to throw them!",
                ChatColor.GRAY + "Shift to slam to the ground, launching all",
                ChatColor.GRAY + "players and blocks nearby!"
        );
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();

        if(!player.isSneaking())
            return;

        player.playSound(player.getLocation(), Sound.ENTITY_MINECART_RIDING, 0.2F, 0.2F);
    }
}