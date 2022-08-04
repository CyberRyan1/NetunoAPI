package com.github.cyberryan1.netunoapi.models.players;

import com.github.cyberryan1.netunoapi.models.alts.NAltGroup;
import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;
import org.bukkit.OfflinePlayer;

import java.util.List;

public interface NPlayer {

    /**
     * @return The {@link OfflinePlayer} of this player.
     */
    OfflinePlayer getPlayer();

    /**
     * @return All punishments that this player has.
     */
    List<NPunishment> getPunishments();

    /**
     * Updates the punishments list for this player.
     */
    void updatePunishments();

    /**
     * @return The {@link NAltGroup} this player belongs to.
     * Will return null if the player hasn't joined the server
     * before or if their alt group cannot be found.
     */
    NAltGroup getAltGroup();
}