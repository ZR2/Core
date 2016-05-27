package me.gamerzking.core.guild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GamerzKing on 5/18/2016.
 */
public class Guild {

    private int id = -1;
    private String name, description = "";
    private int coins = 1000;

    private Timestamp dateCreated;

    private Map<String, GuildRank> members = new HashMap<>();
    private List<String> onlineMembers = new ArrayList<>();

    private long inviteExpire = 5;
    private Map<String, Long> invited = new HashMap<>();

    public Guild() {}

    public void broadcast(String message) {

        for(String name : getMembers().keySet()) {

            Player player = Bukkit.getPlayer(name);

            if(player == null)
                continue;

            player.sendMessage(message);
        }
    }

    public boolean isInvited(Player player) {

        if(!getInvited().containsKey(player.getName()))
            return false;

        if(System.currentTimeMillis() > getInvited().get(player.getName()) + (getInviteExpire() * 60000))
            return false;

        return true;
    }

    public boolean isMember(Player player) {
        return members.containsKey(player.getName());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public Map<String, GuildRank> getMembers() {
        return members;
    }

    public long getInviteExpire() {
        return inviteExpire;
    }

    public Map<String, Long> getInvited() {
        return invited;
    }
}
