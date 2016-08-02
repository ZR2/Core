package me.gamerzking.core.collectible;

import com.google.common.collect.Maps;
import me.gamerzking.core.collectible.types.Collectible;
import me.gamerzking.core.collectible.types.CollectibleType;
import me.gamerzking.core.collectible.types.morphs.MorphGolem;
import me.gamerzking.core.collectible.ui.CollectibleMenu;
import me.gamerzking.core.itemstack.ItemBuilder;
import me.gamerzking.core.utils.UtilMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class CollectibleManager implements Listener {

    private JavaPlugin plugin;

    private Map<CollectibleType, List<Collectible>> collectibles;

    private int interfaceSlot = 4;
    private int itemSlot = interfaceSlot + 1;

    public CollectibleManager(JavaPlugin plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        addCollectibles();
    }

    private void addCollectible(Collectible collectible) {

        if(!collectibles.containsKey(collectible.getType()))
            collectibles.put(collectible.getType(), new ArrayList<>());

        collectibles.get(collectible.getType()).add(collectible);
    }

    private void addCollectibles() {

        collectibles = Maps.newHashMap();

        addCollectible(new MorphGolem(this));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(UtilMaterial.isMaterial(player.getInventory().getItem(interfaceSlot), Material.CHEST))
            return;

        player.getInventory().setItem(interfaceSlot, new ItemBuilder(Material.CHEST).setName(ChatColor.GREEN + "Collectibles Menu").build());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if(!UtilMaterial.isMaterial(player.getInventory().getItemInMainHand(), Material.CHEST))
            return;

        CollectibleMenu menu = new CollectibleMenu(plugin);

        menu.buildPage();
        menu.openInventory(player);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}