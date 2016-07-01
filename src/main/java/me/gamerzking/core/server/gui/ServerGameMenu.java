package me.gamerzking.core.server.gui;

import me.gamerzking.core.itemstack.ItemBuilder;
import me.gamerzking.core.menu.MenuPage;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * Created by GamerzKing on 6/24/2016.
 */
public class ServerGameMenu extends MenuPage {

    public ServerGameMenu() {

        super("Game Menu", 5);
    }

    @Override
    public void buildPage() {

        addItem(1, new ItemBuilder(Material.EYE_OF_ENDER)

                .setName(ChatColor.GREEN + "Island Siege")

                .setLore(

                        "",
                        "",
                        ""
                )

                .build());
    }
}