package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.reports.NReport;
import com.github.cyberryan1.netunoapi.utils.ExpiringCache;
import org.bukkit.OfflinePlayer;

import java.util.List;

public interface ReportsDatabase {
    
    /**
     * @return The cache of reports.
     */
    ExpiringCache<NReport> getCache();

    /**
     * Adds a report to the database, but not the cache
     * @param report The report to add
     */
    void addReport( NReport report );

    /**
     * Searches for a report in the database and in the cache
     * by the report ID
     * @param id The ID of the report to search for
     * @return The report if found, null otherwise
     */
    NReport getReport( int id );

    /**
     * Searches for all reports in the database and in the cache
     * that have the given player has the player. <br>
     * <i><b>Note:</b> This will search for all reports from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetReports( OfflinePlayer )} method</i>
     * @param player The player to search for
     * @return A {@link List< NReport >} of all reports for the player
     */
    List<NReport> getReports( OfflinePlayer player );

    /**
     * Searches for all reports in the database and in the cache
     * that have the given player UUID has the player. <br>
     * <i><b>Note:</b> This will search for all reports from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetReports( String )} method</i>
     * @param playerUuid The player UUID to search for
     * @return A {@link List< NReport >} of all reports for the player
     */
    List<NReport> getReports( String playerUuid );

    /**
     * Searches for all reports in just the database, not in the
     * cache, that have the given player as the player. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getReports( String )} method</i>
     * @param player The {@link OfflinePlayer} to search for
     * @return A {@link List< NReport >} of all reports for the player
     */
    List<NReport> forceGetReports( OfflinePlayer player );

    /**
     * Searches for all reports in just the database, not in the
     * cache, that have the given player UUID as the player's UUID. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getReports( String )} method</i>
     * @param playerUuid The player UUID to search for
     * @return A {@link List< NReport >} of all reports for the player
     */
    List<NReport> forceGetReports( String playerUuid );

    /**
     * Deletes a report with the given id.
     * @param id The id of the report to delete.
     */
    void deleteReport( int id );

    /**
     * Deletes all reports of a player
     * @param player The player to delete reports for
     */
    void deleteReports( OfflinePlayer player );

    /**
     * Deletes all reports of a player
     * @param playerUuid The UUID of the player to delete reports for
     */
    void deleteReports( String playerUuid );
}