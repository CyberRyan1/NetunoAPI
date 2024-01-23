package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.alts.TempUuidIpEntry;

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
     * Saves a certain {@link TempUuidIpEntry}
     * group to the database
     */
    void save( TempUuidIpEntry entry );

    /**
     * Queries for a {@link TempUuidIpEntry} that contains the given
     * UUID. Returns an empty optional if none found.
     * @param uuid The UUID to query by
     * @return An optional containing the alt entries if they exist, empty otherwise
     */
    Optional<TempUuidIpEntry> queryByUuid( UUID uuid );

    /**
     * Queries for a {@link TempUuidIpEntry} that contains the given
     * IP. Returns an empty optional if none found.
     * @param ip The IP to query by
     * @return An optional containing the alt entries if they exist, empty otherwise
     */
    Optional<TempUuidIpEntry> queryByIp( String ip );

    /**
     * @param entry an entry to remove from the database
     */
    void deleteEntry( TempUuidIpEntry entry );

}