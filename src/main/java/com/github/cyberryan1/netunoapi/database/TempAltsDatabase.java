package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.alts.TempAltGroup;
import com.github.cyberryan1.netunoapi.models.alts.TempIpEntry;

import java.util.Set;
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
     * Saves a certain {@link TempIpEntry}
     * group to the database
     */
    void save( TempIpEntry group );

    /**
     * Queries for a Set of {@link TempIpEntry} that contains the given
     * UUID. Returns an empty set if none found.
     * @param uuid The UUID to query by
     * @return A set containing the alt entries if they exist, empty otherwise
     */
    Set<TempIpEntry> queryByUuid( UUID uuid );

    /**
     * Queries for a Set of {@link TempIpEntry} that contains the given
     * IP. Returns an empty set if none found.
     * @param ip The IP to query by
     * @return A set containing the alt entries if they exist, empty otherwise
     */
    Set<TempAltGroup> queryByIp( String ip );

    /**
     * @param entry an entry to remove from the database
     */
    void deleteGroup( TempIpEntry entry );

}