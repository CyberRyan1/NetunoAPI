package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.reports.NReport;
import org.bukkit.OfflinePlayer;

import java.util.List;

public interface ReportsDatabase {
    
    /**
     * @return The cache of reports.
     */
    List<NReport> getCache();

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
     * @param player The player to search for
     * @return A {@link List} of all reports for the player
     */
    List<NReport> getReports( OfflinePlayer player );

    /**
     * Searches for all reports in the database and in the cache
     * that have the given player UUID has the player. <br>
     * @param playerUuid The player UUID to search for
     * @return A {@link List} of all reports for the player
     */
    List<NReport> getReports( String playerUuid );

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

    /**
     * Deletes all reports that are older than the expiration time
     */
    void deleteOldReports();
}