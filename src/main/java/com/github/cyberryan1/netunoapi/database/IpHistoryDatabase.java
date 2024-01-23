package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.alts.AltInfoLoader;
import com.github.cyberryan1.netunoapi.models.alts.UuidIpRecord;

import java.util.Set;
import java.util.UUID;

public interface IpHistoryDatabase {

    /**
     * Initializes the database
     */
    void initialize();

    /**
     * Shuts down the database and the cache used by
     * {@link AltInfoLoader}
     */
    void shutdown();

    /**
     * Saves a certain {@link UuidIpRecord}
     * group to the database
     */
    void save( UuidIpRecord entry );

    /**
     * Queries for a Set of {@link UuidIpRecord} that contains the given
     * UUID. Returns an empty set if none found.
     * @param uuid The UUID to query by
     * @return A set containing the alt entries if they exist, empty otherwise
     */
    Set<UuidIpRecord> queryByUuid( UUID uuid );

    /**
     * Queries for a {@link UuidIpRecord} that contains the given
     * IP. Returns an empty set if none found.
     * @param ip The IP to query by
     * @return A set containing the alt entries if they exist, empty otherwise
     */
    Set<UuidIpRecord> queryByIp( String ip );

    /**
     * Queries for a {@link UuidIpRecord} that contains the given
     * IPs. Returns an empty set if none found.
     * @param ip The IPs to query by
     * @return A set containing the alt entries if they exist, empty otherwise
     */
    Set<UuidIpRecord> queryByMultipleIps( Set<String> ip );

    /**
     * @param entry an entry to remove from the database
     */
    void deleteEntry( UuidIpRecord entry );

}