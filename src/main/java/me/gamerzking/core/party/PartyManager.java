package me.gamerzking.core.party;

import me.gamerzking.core.Core;
import me.gamerzking.core.updater.UpdateType;
import me.gamerzking.core.updater.event.UpdateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 6/1/2016.
 */
public class PartyManager implements Listener {

    private List<Party> parties;

    /**
     * Instantiates the list of parties and registers events.
     */

    public PartyManager(Core plugin) {

        parties = new ArrayList<>();
        plugin.registerEvents(this);
    }

    /**
     * Creates a new party, with the designated leader.
     *
     * @param player The leader of the party.
     * @return The newly created party.
     */

    public Party createParty(Player player) {

        if (inParty(player))
            getParty(player).leaveParty(player);

        Party party = new Party();
        parties.add(party);

        return party;
    }

    /**
     * Gets the party the player is in.
     *
     * @param player The player you're getting the party from.
     * @return The party they are in, if applicable; otherwise, null.
     */

    public Party getParty(Player player) {

        for (Party party : parties)
            if (party.getMembers().containsKey(player.getName()))
                return party;

        return null;
    }

    /**
     * Determines whether the specific player is in a party.
     *
     * @param player The player you're getting the information from.
     * @return True if the player is in a party; otherwise, false.
     */

    public boolean inParty(Player player) {
        return getParty(player) != null;
    }

    /**
     * Disbands the party specified.
     *
     * @param party The party you're disbanding.
     */

    public void disbandParty(Party party) {

        party.broadcastMessage("Your party has been disbanded!");
        party.getMembers().clear();

        parties.remove(party);
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) {

        if(event.getType() != UpdateType.SECOND)
            return;

        parties.forEach(Party::updateInvites);
    }

    /**
     * @return The list of parties.
     */

    public List<Party> getParties() {
        return parties;
    }
}