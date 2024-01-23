package com.github.cyberryan1.netunoapi.models.alts;

import java.util.Set;
import java.util.UUID;

public interface AltInfoLoader {

    /**
     * Initializes the loader and its cache
     */
    void initialize();

    /**
     * Saves the UUID and IP to the database, if needed
     * @param uuid UUID of the player
     * @param ip IP of the player
     */
    void initEntry( UUID uuid, String ip );

    /**
     * Gets the accounts that have joined on a certain IP
     * @param ip The IP of the player to load
     * @return set of alt accounts that the player has
     */
    Set<UUID> queryAccounts( String ip );
}