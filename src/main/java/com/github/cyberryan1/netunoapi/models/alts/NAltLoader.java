package com.github.cyberryan1.netunoapi.models.alts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NAltLoader {

    /**
     * Initializes the cache and loads all entries from the database
     */
    void initialize();

    /**
     * Loads a player into the cache (should only be ran when the player joins).
     * @param uuid The {@link UUID} of the player to load
     * @param ip The IP of the player to load
     */
    void loadPlayer( UUID uuid, String ip );

    /**
     * Searches for a group with the given ID
     * @param groupId The ID of the group to search for
     * @return An optional containing the group if it exists, or an empty optional if it does not
     */
    Optional<NAltGroup> search( int groupId );

    /**
     * Searches for a singular group that contains the given UUID
     * @param uuid The UUID to search for
     * @return An optional containing the group if it exists, or an empty optional if it does not
     */
    Optional<NAltGroup> search( UUID uuid );

    /**
     * Searches for all groups that contain the given UUID
     * @param uuid the UUID to search for
     * @return A list of all groups that contain the given UUID
     */
    List<NAltGroup> searchForMany( UUID uuid );

    /**
     * Searches for a singular group that contains the given IP address
     * @param ip The IP address to search for
     * @return An optional containing the group if it exists, or an empty optional if it does not
     */
    Optional<NAltGroup> search( String ip );

    /**
     * Searches for all groups that contain the given IP address
     * @param ip The IP address to search for
     * @return A list of all groups that contain the given IP address
     */
    List<NAltGroup> searchForMany( String ip );
}