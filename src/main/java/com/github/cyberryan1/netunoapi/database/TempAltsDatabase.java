package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.alts.TempAltGroup;

import java.util.Optional;
import java.util.UUID;

public interface TempAltsDatabase {

    /**
     * Initializes the database
     */
    void initialize();

    /**
     * Shuts down the database and the cache used by
     * {@link com.github.cyberryan1.netunoapi.models.alts.TempAltCache}
     */
    void shutdown();

    /**
     * Saves all edited info the database
     */
    void save();

    /**
     * Saves a certain {@link TempAltGroup}
     * group to the database
     */
    void save( TempAltGroup group );

    /**
     * Queries for a {@link TempAltGroup} that contains the given
     * UUID. Returns an empty optional if not found.
     * @param uuid The UUID to query by
     * @return An optional containing the alt group if it exists, empty otherwise
     */
    Optional<TempAltGroup> queryByUuid( UUID uuid );

    /**
     * Queries for a {@link TempAltGroup} that contains the given
     * IP. Returns an empty optional if not found.
     * @param ip The IP to query by
     * @return An optional containing the alt group if it exists, empty otherwise
     */
    Optional<TempAltGroup> queryByIp( String ip );

    /**
     * @param group a group to remove from the database
     */
    void deleteGroup( TempAltGroup group );

    /**
     * @return The next available group ID
     */
    int getNextGroupId();

}