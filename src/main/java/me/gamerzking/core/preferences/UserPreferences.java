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

    public boolean canSeePlayers() {
        return playerVisibility;
    }

    public void setPlayerVisibility(boolean playerVisibility) {
        this.playerVisibility = playerVisibility;
    }

    public boolean canSeeBlood() {
        return blood;
    }

    public void setBlood(boolean blood) {
        this.blood = blood;
    }

    public boolean canSeeChat() {
        return chatVisibility;
    }

    public void setChatVisibility(boolean chatVisibility) {
        this.chatVisibility = chatVisibility;
    }

    public boolean canReceiveFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(boolean friendRequests) {
        this.friendRequests = friendRequests;
    }

    public boolean canReceivePartyRequests() {
        return partyRequests;
    }

    public void setPartyRequests(boolean partyRequests) {
        this.partyRequests = partyRequests;
    }

    public boolean canReceiveGuildRequests() {
        return guildRequests;
    }

    public void setGuildRequests(boolean guildRequests) {
        this.guildRequests = guildRequests;
    }
}
