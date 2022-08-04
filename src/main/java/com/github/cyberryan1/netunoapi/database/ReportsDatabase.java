package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.models.ANetunoReport;
import com.github.cyberryan1.netunoapi.models.ANetunoReportData;
import com.github.cyberryan1.netunoapi.utils.helpers.ClassIncompleteException;
import com.github.cyberryan1.netunoapi.utils.helpers.ExpiringCache;
import org.bukkit.OfflinePlayer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsDatabase {

    private final String TABLE_NAME = "reports";
    private final String TYPE_LIST = "(id, player, data)";
    private final String UNKNOWN_LIST = "(?, ?, ?)";

    private final ExpiringCache<ANetunoReport> cache = new ExpiringCache<>();

    /**
     * @return The cache of reports.
     */
    public ExpiringCache<ANetunoReport> getCache() {
        return cache;
    }

    /**
     * Adds a report to the database, but not the cache
     * @param report The report to add
     */
    public void addReport( ANetunoReport report ) {
        checkReport( report, false );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "INSERT INTO " + TABLE_NAME + "(player, data) VALUES(?, ?);" );
            ps.setString( 1, report.getPlayerUuid() );
            ps.setObject( 2, ( ANetunoReportData ) report );

            ps.addBatch();
            ps.executeBatch();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Searches for a report in the database and in the cache
     * by the report ID
     * @param id The ID of the report to search for
     * @return The report if found, null otherwise
     */
    public ANetunoReport getReport( int id ) {
        ANetunoReportData data = cache.searchForOne( r -> r.getId() == id );
        if ( data != null ) { return ( ANetunoReport ) data; }

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;" );
            ps.setInt( 1, id );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                byte bytes[] = ( byte[] ) rs.getObject( "data" );
                ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
                ObjectInputStream ois = new ObjectInputStream( bais );
                data = ( ANetunoReportData ) ois.readObject();
                data.setId( rs.getInt( "id" ) );
                cache.add( ( ANetunoReport ) data );
            }

            ps.close();
            rs.close();
        } catch ( SQLException | IOException | ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }

        return ( data == null ) ? ( null ) : ( ( ANetunoReport ) data );
    }

    /**
     * Searches for all reports in the database and in the cache
     * that have the given player has the player. <br>
     * <i><b>Note:</b> This will search for all reports from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetReports( OfflinePlayer )} method</i>
     * @param player The player to search for
     * @return A {@link List<ANetunoReport>} of all reports for the player
     */
    public List<ANetunoReport> getReports( OfflinePlayer player ) {
        return getReports( player.getUniqueId().toString() );
    }

    /**
     * Searches for all reports in the database and in the cache
     * that have the given player UUID has the player. <br>
     * <i><b>Note:</b> This will search for all reports from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetReports( String )} method</i>
     * @param playerUuid The player UUID to search for
     * @return A {@link List<ANetunoReport>} of all reports for the player
     */
    public List<ANetunoReport> getReports( String playerUuid ) {
        List<ANetunoReport> toReturn = cache.searchForMany( r -> r.getPlayerUuid().equals( playerUuid ) );
        if ( toReturn.size() == 0 ) { toReturn = forceGetReports( playerUuid ); }
        return toReturn;
    }

    /**
     * Searches for all reports in just the database, not in the
     * cache, that have the given player as the player. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getReports( String )} method</i>
     * @param player The {@link OfflinePlayer} to search for
     * @return A {@link List<ANetunoReport>} of all reports for the player
     */
    public List<ANetunoReport> forceGetReports( OfflinePlayer player ) {
        return forceGetReports( player.getUniqueId().toString() );
    }

    /**
     * Searches for all reports in just the database, not in the
     * cache, that have the given player UUID as the player's UUID. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getReports( String )} method</i>
     * @param playerUuid The player UUID to search for
     * @return A {@link List<ANetunoReport>} of all reports for the player
     */
    public List<ANetunoReport> forceGetReports( String playerUuid ) {
        List<ANetunoReport> toReturn = new ArrayList<>();
        cache.removeAllWhere( r -> r.getPlayerUuid().equals( playerUuid ) );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "SELECT * FROM " + TABLE_NAME + " WHERE player = ?;" );
            ps.setString( 1, playerUuid );

            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                byte bytes[] = ( byte[] ) rs.getObject( "data" );
                ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
                ObjectInputStream ois = new ObjectInputStream( bais );
                ANetunoReportData data = ( ANetunoReportData ) ois.readObject();
                data.setId( rs.getInt( "id" ) );
                toReturn.add( ( ANetunoReport ) data );
                cache.add( ( ANetunoReport ) data );
            }

            ps.close();
            rs.close();
        } catch ( SQLException | IOException | ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }

        return toReturn;
    }

    /**
     * Deletes a report with the given id.
     * @param id The id of the report to delete.
     */
    public void deleteReport( int id ) {
        cache.removeAllWhere( r -> r.getId() == id );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "DELETE FROM " + TABLE_NAME + " WHERE id = ?;" );
            ps.setInt( 1, id );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Deletes all reports of a player
     * @param player The player to delete reports for
     */
    public void deleteReports( OfflinePlayer player ) {
        deleteReports( player.getUniqueId().toString() );
    }

    /**
     * Deletes all reports of a player
     * @param playerUuid The UUID of the player to delete reports for
     */
    public void deleteReports( String playerUuid ) {
        cache.removeAllWhere( r -> r.getPlayerUuid().equals( playerUuid ) );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "DELETE FROM " + TABLE_NAME + " WHERE player = ?;" );
            ps.setString( 1, playerUuid );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Checks if a report is completely filled with the correct data
     * @param report The report to check
     * @param shouldBeValidId If the punishment ID should be above 0 (true) or less than or equal to 0 (false)
     * @throws ClassIncompleteException If the report is incomplete
     */
    private void checkReport( ANetunoReport report, boolean shouldBeValidId ) {
        if ( shouldBeValidId && report.getId() <= 0 ) { throw new ClassIncompleteException( "Report incomplete: report ID must be greater than zero" ); }
        if ( shouldBeValidId == false && report.getId() > 0 ) { throw new ClassIncompleteException( "Report incomplete: report ID must be less than or equal to zero" ); }
        if ( report.getPlayerUuid() == null ) { throw new ClassIncompleteException( "Report incomplete: player UUID cannot be null" ); }
        if ( report.getReporterUuid() == null ) { throw new ClassIncompleteException( "Report incomplete: reporter UUID cannot be null" ); }
        if ( report.getTimestamp() <= 0 ) { throw new ClassIncompleteException( "Report incomplete: timestamp must be greater than zero" ); }
        if ( report.getReason() == null ) { throw new ClassIncompleteException( "Report incomplete: reason cannot be null" ); }
    }
}