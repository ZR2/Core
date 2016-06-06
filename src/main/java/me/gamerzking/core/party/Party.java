package me.gamerzking.core.party;

import me.gamerzking.core.utils.UtilTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class Party {

    private Map<String, PartyRank> members = new HashMap<>();
    private Map<String, Long> invites = new HashMap<>();

    /**
     * Adds the player to the party.
     *
     * @param player The player you're adding to the party.
     */

    public void joinParty(Player player) {

        if(members.isEmpty()) {

            player.sendMessage("New party created!");
            members.put(player.getName(), PartyRank.LEADER);

        } else {

            members.put(player.getName(), PartyRank.MEMBER);
            broadcastMessage(player.getName() + " joined the party!");

            getInvites().remove(player.getName());
        }
    }

    /**
     * Removes the player from the party.
     *
     * @param player The player you're removing from the party.
     */

    public void leaveParty(Player player) {

        broadcastMessage(player.getName() + " left the party!");
        members.remove(player.getName());
    }

    /**
     * Kicks the player from the party.
     *
     * @param player The player you're kicking from the party.
     */

    public void kickPlayer(Player player) {

        broadcastMessage(player.getName() + " was kicked from the party!");
        members.remove(player.getName());
    }

    /**
     * Broadcasts a message to all online party members.
     *
     * @param message The message you're broadcasting.
     */

    public void broadcastMessage(String... message) {

        for(String name : members.keySet()) {

            Player player = Bukkit.getPlayer(name);

            if (player != null && player.isOnline()) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * Updates the invites to verify they hasn't expired.
     */

    public void updateInvites() {

        Iterator<String> inviteIterator = invites.keySet().iterator();

        while (inviteIterator.hasNext()) {

            String name = inviteIterator.next();

            if (UtilTime.elapsed(invites.get(name), 60000) && getInvites().containsKey(name)) {

                broadcastMessage("The invite to " + name + " has expired!");
                inviteIterator.remove();
            }
        }
    }

    /**
     * Invites the specific player to the party.
     *
     * @param player The player you're inviting.
     */

    public void invitePlayer(Player player) {

        invites.put(player.getName(), System.currentTimeMillis());
        broadcastMessage(player.getName() + " was invited to the party!");
    }

    /**
     * Determines whether the name specified is invited to the party.
     *
     * @param name The name of the player you're determining.
     * @return True if the name provided was invited and hasn't expired; otherwise, false.
     */

    public boolean isInvited(String name) {

        if(!getInvites().containsKey(name))
            return false;

        if(System.currentTimeMillis() > getInvites().get(name) + 60000)
            return false;

        return true;
    }

    /**
     * Gets the rank of the name provided.
     *
     * @param name The name you're getting the rank from.
     * @return The rank of the player, if applicable; otherwise, null.
     */

    public PartyRank getRank(String name) {

        if(!getMembers().containsKey(name))
            return null;

        return members.get(name);
    }

    /**
     * Sets the player to the specified rank.
     *
     * @param player The player you're setting the rank for.
     * @param rank The rank you're setting the player to.
     */

    public void setRank(Player player, PartyRank rank) {

        members.put(player.getName(), rank);
    }

    /**
     * @return The leader of the party.
     */

    public String getLeader() {

        for (String name : members.keySet()) {
            if (getRank(name) == PartyRank.LEADER) {

                return name;
            }
        }
        return "Unknown";
    }

    /**
     * @return The {@link Map} of all players and ranks in the party.
     */

    public Map<String, PartyRank> getMembers() {
        return members;
    }

    /**
     * @return The {@link Map} of all invites and time remaining in the party.
     */

    public Map<String, Long> getInvites() {
        return invites;
    }
}