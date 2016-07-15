package me.gamerzking.core.server;

import me.gamerzking.core.itemstack.ItemBuilder;
import me.gamerzking.core.server.gui.ServerGameMenu;
import me.gamerzking.core.utils.UtilMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by GamerzKing on 6/24/2016.
 */
public class ServerManager implements Listener {

    private ServerGameMenu gameMenu;

    public ServerManager(JavaPlugin plugin) {

        gameMenu = new ServerGameMenu(plugin);

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        player.getInventory().addItem(new ItemBuilder(Material.COMPASS).setName(ChatColor.GREEN + "Game Menu").build());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (UtilMaterial.isMaterial(player.getInventory().getItemInMainHand(), Material.COMPASS))
            gameMenu.openInventory(player);
    }
}