package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.punishments.NPunishment;
import com.github.cyberryan1.netunoapi.utils.ExpiringCache;
import org.bukkit.OfflinePlayer;

import java.util.List;

public interface PunishmentsDatabase {

    /**
     * @return The cache of punishments.
     */
    ExpiringCache<NPunishment> getCache();

    /**
     * Adds a punishment to the database, but not the cache
     * @param punishment The punishment to add
     */
    void addPunishment( NPunishment punishment );

    /**
     * Searches for a punishment in the database and in the cache
     * by the punishment ID.
     * @param punId The ID of the punishment to search for.
     * @return The punishment with the ID, or null if not found.
     */
    NPunishment getPunishment( int punId );

    /**
     * Searches for all punishments in the database and in the cache
     * that have the given player as the player. <br>
     * <i><b>Note:</b> This will search for all punishments from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetPunishments( OfflinePlayer )} method</i>
     * @param player The {@link OfflinePlayer} to search for.
     * @return A {@link List< NPunishment >} of all punishments for the player.
     */
    List<NPunishment> getPunishments( OfflinePlayer player );

    /**
     * Searches for all punishments in the database and in the cache
     * that have the given player UUID as the player's UUID. <br>
     * <i><b>Note:</b> This will search for all punishments from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetPunishments( String )} method</i>
     * @param playerUuid The player UUID to search for.
     * @return A {@link List< NPunishment >} of all punishments for the player.
     */
    List<NPunishment> getPunishments( String playerUuid );

    /**
     * Searches for all punishments in just the database, not in the
     * cache, that have the given player as the player. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getPunishments( OfflinePlayer )} method</i>
     * @param player The {@link OfflinePlayer} to search for.
     * @return A {@link List< NPunishment >} of all punishments for the player.
     */
    List<NPunishment> forceGetPunishments( OfflinePlayer player );

    /**
     * Searches for all punishments in just the database, not in the
     * cache, that have the given player UUID as the player's UUID. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getPunishments( String )} method</i>
     * @param playerUuid The player UUID to search for.
     * @return A {@link List< NPunishment >} of all punishments for the player.
     */
    List<NPunishment> forceGetPunishments( String playerUuid );

    /**
     * Searches for all punishments in the database and in the cache
     * that have the given reference ID as the reference ID. <br>
     * <i><b>Note:</b> This will search for all punishments from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetPunishmentsFromReference( int )} method</i>
     * @param referenceId The reference ID to search for
     * @return A {@link List< NPunishment >} of all punishments for the reference ID.
     */
    List<NPunishment> getPunishmentsFromReference( int referenceId );

    /**
     * Searches for all punishments in the database, not in the
     * cache, that have the given reference ID as the reference ID. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getPunishmentsFromReference( int )} method</i>
     * @param referenceId The reference ID to search for
     * @return A {@link List< NPunishment >} of all punishments for the reference ID.
     */
    List<NPunishment> forceGetPunishmentsFromReference( int referenceId );

    /**
     * Updates the given punishment in the database and cache
     * @param newData The updated punishment data
     */
    void updatePunishment( NPunishment newData );

    /**
     * Deletes the punishment with the given ID from
     * the database and cache
     * @param punId The ID of the punishment to delete
     */
    void removePunishment( int punId );

    /**
     * Deletes all punishments for the given player
     * from the database and cache
     * @param player The player to delete punishments for
     */
    void removePunishments( OfflinePlayer player );

    /**
     * Deletes all punishments for the given player UUID
     * from the database and cache
     * @param playerUuid The player UUID to delete punishments for
     */
    void removePunishments( String playerUuid );

    /**
     * Deletes all punishments that have the given reference ID
     * @param referenceId The reference ID to delete punishments for
     */
    void removePunishments( int referenceId );

    /**
     * @return The id of the most recently added punishment
     */
    int getRecentlyInsertedId();
}