package me.gamerzking.core.friend;

import org.bukkit.entity.Player;

/**
 * Created by GamerzKing on 5/19/2016.
 */
public class FriendManager {

    /**
     * Adds a friend to the players friends list.
     *
     * @param player The players friends list you're adding the friend to.
     * @param name The name of the friend you're are adding.
     */

    public void addFriend(Player player, String name) {

        if(player.getName().equals(name))
            player.sendMessage("You cannot add yourself as a friend!");

        //TODO: Instantiate the variable to prevent NullPointerExceptions
        FriendStatus status = null;

        switch(status) {

            case PENDING:
                player.sendRawMessage("Please wait before sending another friend request!");
                break;

            case ACCEPTED:
                player.sendMessage("You are now friends with " + name);
                break;

            case DENIED:
                player.sendMessage("Denied!");
                break;

            case BLOCKED:
                player.sendMessage("Blocked!");
        }
    }
}
