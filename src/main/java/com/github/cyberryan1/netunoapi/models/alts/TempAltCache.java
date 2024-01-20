package com.github.cyberryan1.netunoapi.models.alts;

import java.util.Optional;
import java.util.UUID;

public interface TempAltCache {

    /**
     * Initializes the loader and its cache
     */
    void initialize();

    /**
     * Loads a player into the cache (should only be ran when the player joins).
     * @param uuid The {@link UUID} of the player to load
     * @param ip The IP of the player to load
     */
    void loadPlayer( UUID uuid, String ip );

    /**
     * Searches for a singular group that contains the given UUID
     * @param uuid The UUID to search for
     * @return An optional containing the group if it exists, or an empty optional if it does not
     */
    Optional<TempAltGroup> searchByUuid( UUID uuid );

    /**
     * Searches for a singular group that contains the given IP address
     * @param ip The IP address to search for
     * @return An optional containing the group if it exists, or an empty optional if it does not
     */
    Optional<TempAltGroup> searchByIp( String ip );
}