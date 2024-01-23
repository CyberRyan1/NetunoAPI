package com.github.cyberryan1.netunoapi.models.players;

import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
     * @return All alt accounts of this player
     */
    Set<UUID> getAltAccounts();
}