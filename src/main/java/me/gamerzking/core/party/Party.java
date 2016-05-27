package me.gamerzking.core.party;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by GamerzKing on 5/18/2016.
 */
public class Party {

    private String creator;
    private String previousServer;

    private List<String> members = new ArrayList<>();
    private Map<String, Long> invites = new HashMap<>();

    private long inviteExpiration = 60000;

    public void invitePlayer(Player player) {

        invites.put(player.getName(), System.currentTimeMillis());

        if(members.contains(player.getName())) {

            partyBroadcast(

                    "-------------------",
                    player.getName() + "is already in the party!",
                    "--------------------");
        }

        partyBroadcast(

                "----------------",
                player.getName() + "was invited to the party!",
                "------------------");
    }

    public void joinParty(Player player) {

        if(members.isEmpty()) {

            members.add(player.getName());
            creator = player.getName();

        } else {

            members.add(player.getName());

            partyBroadcast(

                    "---------------------------------------------",
                    player.getName() + "joined the party!",
                    "---------------------------------------------");
        }
    }

    public void leaveParty(Player player) {

        members.remove(player.getName());

        partyBroadcast(

                "--------------------",
                player.getName() + "left the party!",
                "----------------------");

        if(player.getName().equals(creator))
            disbandParty();
    }

    public void kickPlayer(Player player) {

        members.remove(player.getName());

        partyBroadcast(

                "-------------------",
                player.getName() + "was kicked!",
                "---------------------------");
    }

    public void disbandParty() {

        partyBroadcast(

                "---------------------",
                "Party has been disbanded!",
                "----------------------");

        members.clear();
        invites.clear();

        creator = null;
    }

    public void inviteExpire() {

        Iterator<String> iterator = invites.keySet().iterator();

        while(iterator.hasNext()) {

            String name = iterator.next();

            if(System.currentTimeMillis() >= invites.get(name) + inviteExpiration) {

                partyBroadcast(

                        "------------------",
                        name + "didn't accept in time",
                        "------------------------");
            }
        }
    }

    public void partyBroadcast(String... message) {

        for(String name : members) {

            Player player = Bukkit.getPlayer(name);

            if(player != null) {

                player.sendMessage(message);
            }
        }
    }
}
