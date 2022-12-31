package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.alts.NAltEntry;
import com.github.cyberryan1.netunoapi.models.alts.NAltGroup;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AltsDatabase {

    /**
     * Initializes the database
     */
    void initialize();

    /**
     * Saves variables needed to the database
     */
    void save();

    /**
     * Returns a list of IPs addresses that are linked to the given UUID
     * @param uuid The UUID to search for
     * @return A list of IPs that are linked to the given UUID
     */
    List<String> queryIps( String uuid );

    /**
     * Returns a list of UUIDs that are linked to the given IP address
     * @param ip The IP address to search for
     * @return A list of UUIDs that are linked to the given IP address
     */
    List<UUID> queryUuids( String ip );

    /**
     * Returns an alt group of the given ID, provided it exists
     * @param groupId The ID of the alt group to search for
     * @return An optional containing the alt group if it exists, or an empty optional if it does not
     */
    Optional<NAltGroup> queryGroupById( int groupId );

    /**
     * Returns an alt group of the given UUID, provided it exists
     * @param uuid The UUID to search for
     * @return An optional containing the alt group if it exists, or an empty optional if it does not
     */
    Optional<NAltGroup> queryGroupByUuid( UUID uuid );

    /**
     * Returns an alt group of the given IP, provided it exists
     * @param ip The IP to search for
     * @return An optional containing the alt group if it exists, or an empty optional if it does not
     */
    Optional<NAltGroup> queryGroupByIp( String ip );

    /**
     * @return A list of all entries in the database
     */
    List<NAltEntry> queryAllEntries();

    /**
     * @param entry An <b>new</b> entry to save to the database
     */
    void saveNewEntry( NAltEntry entry );

    /**
     * Updates the given entry's group ID to the given group ID. The given entry must still
     * have the old group ID.
     * @param entry The entry to update
     * @param newGroupId The new group ID to update the entry to
     */
    void updateEntryGroupId( NAltEntry entry, int newGroupId );

    /**
     * @param entry An entry to remove from the database
     */
    void deleteEntry( NAltEntry entry );

    /**
     * @return The next available group ID
     */
    int getNextGroupId();
}