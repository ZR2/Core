package me.gamerzking.core.preferences;

/**
 * Created by GamerzKing on 5/20/2016.
 */
public class UserPreferences {

    private boolean playerVisibility = true;
    private boolean blood = true;
    private boolean chatVisibility = true;
    private boolean friendRequests = true;
    private boolean partyRequests = true;
    private boolean guildRequests = true;

    public boolean isPlayerVisibility() {
        return playerVisibility;
    }

    public void setPlayerVisibility(boolean playerVisibility) {
        this.playerVisibility = playerVisibility;
    }

    public boolean isBlood() {
        return blood;
    }

    public void setBlood(boolean blood) {
        this.blood = blood;
    }

    public boolean isChatVisibility() {
        return chatVisibility;
    }

    public void setChatVisibility(boolean chatVisibility) {
        this.chatVisibility = chatVisibility;
    }

    public boolean isFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(boolean friendRequests) {
        this.friendRequests = friendRequests;
    }

    public boolean isPartyRequests() {
        return partyRequests;
    }

    public void setPartyRequests(boolean partyRequests) {
        this.partyRequests = partyRequests;
    }

    public boolean isGuildRequests() {
        return guildRequests;
    }

    public void setGuildRequests(boolean guildRequests) {
        this.guildRequests = guildRequests;
    }
}
