package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.punishments.ANetunoPunishment;
import com.github.cyberryan1.netunoapi.punishments.ANetunoPunishmentData;
import com.github.cyberryan1.netunoapi.utils.helpers.ClassIncompleteException;
import com.github.cyberryan1.netunoapi.utils.helpers.ExpiringCache;
import org.bukkit.OfflinePlayer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PunishmentsDatabase {

    private final String TABLE_NAME = "punishments";
    private final String TYPE_LIST = "(id, player, data, guipun, reference)";
    private final String UNKNOWN_LIST = "(?, ?, ?, ?, ?)";

    private final ExpiringCache<ANetunoPunishment> cache = new ExpiringCache<>();

    /**
     * @return The cache of punishments.
     */
    public ExpiringCache<ANetunoPunishment> getCache() {
        return cache;
    }

    /**
     * Adds a punishment to the database, but not the cache
     * @param punishment The punishment to add
     */
    public void addPunishment( ANetunoPunishment punishment ) {
        checkPunishment( punishment, false );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "INSERT INTO " + TABLE_NAME + "(player, data, guipun, reference) VALUES(?, ?, ?, ?);" );
            ps.setString( 1, punishment.getPlayerUuid() );
            ps.setObject( 2, ( ANetunoPunishmentData ) punishment );
            ps.setString( 3, punishment.isGuiPun() + "" );
            ps.setInt( 4, punishment.getReferencePunId() );

            ps.addBatch();
            ps.executeBatch();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Searches for a punishment in the database and in the cache
     * by the punishment ID.
     * @param punId The ID of the punishment to search for.
     * @return The punishment with the ID, or null if not found.
     */
    public ANetunoPunishment getPunishment( int punId ) {
        ANetunoPunishmentData data = cache.searchForOne( p -> p.getId() == punId );
        if ( data != null ) { return ( ANetunoPunishment ) data; }

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;" );
            ps.setInt( 1, punId );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                byte bytes[] = ( byte[] ) rs.getObject( "data" );
                ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
                ObjectInputStream ois = new ObjectInputStream( bais );
                data = ( ANetunoPunishmentData ) ois.readObject();
                data.setId( rs.getInt( "id" ) );
                cache.add( ( ANetunoPunishment ) data );
            }

            rs.close();
            ps.close();
        } catch ( SQLException | IOException | ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }

        return ( data == null ) ? ( null ) : ( ( ANetunoPunishment ) data );
    }

    /**
     * Searches for all punishments in the database and in the cache
     * that have the given player as the player. <br>
     * <i><b>Note:</b> This will search for all punishments from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetPunishments( OfflinePlayer )} method</i>
     * @param player The {@link OfflinePlayer} to search for.
     * @return A {@link List<ANetunoPunishment>} of all punishments for the player.
     */
    public List<ANetunoPunishment> getPunishments( OfflinePlayer player ) {
        return getPunishments( player.getUniqueId().toString() );
    }

    /**
     * Searches for all punishments in the database and in the cache
     * that have the given player UUID as the player's UUID. <br>
     * <i><b>Note:</b> This will search for all punishments from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetPunishments( String )} method</i>
     * @param playerUuid The player UUID to search for.
     * @return A {@link List<ANetunoPunishment>} of all punishments for the player.
     */
    public List<ANetunoPunishment> getPunishments( String playerUuid ) {
        List<ANetunoPunishment> toReturn = cache.searchForMany( p -> p.getPlayerUuid().equals( playerUuid ) );
        if ( toReturn.size() == 0 ) { toReturn = forceGetPunishments( playerUuid ); }
        return toReturn;
    }

    /**
     * Searches for all punishments in just the database, not in the
     * cache, that have the given player as the player. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getPunishments( OfflinePlayer )} method</i>
     * @param player The {@link OfflinePlayer} to search for.
     * @return A {@link List<ANetunoPunishment>} of all punishments for the player.
     */
    public List<ANetunoPunishment> forceGetPunishments( OfflinePlayer player ) {
        return forceGetPunishments( player.getUniqueId().toString() );
    }

    /**
     * Searches for all punishments in just the database, not in the
     * cache, that have the given player UUID as the player's UUID. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getPunishments( String )} method</i>
     * @param playerUuid The player UUID to search for.
     * @return A {@link List<ANetunoPunishment>} of all punishments for the player.
     */
    public List<ANetunoPunishment> forceGetPunishments( String playerUuid ) {
        List<ANetunoPunishment> toReturn = new ArrayList<>();
        cache.removeAllWhere( p -> p.getPlayerUuid().equals( playerUuid ) );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "SELECT * FROM " + TABLE_NAME + " WHERE player = ?;" );
            ps.setString( 1, playerUuid );

            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                byte bytes[] = ( byte[] ) rs.getObject( "data" );
                ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
                ObjectInputStream ois = new ObjectInputStream( bais );
                ANetunoPunishmentData data = ( ANetunoPunishmentData ) ois.readObject();
                data.setId( rs.getInt( "id" ) );
                cache.add( ( ANetunoPunishment ) data );
                toReturn.add( ( ANetunoPunishment ) data );
            }

            ps.close();
            rs.close();
        } catch ( SQLException | IOException | ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }

        return toReturn;
    }

    /**
     * Searches for all punishments in the database and in the cache
     * that have the given reference ID as the reference ID. <br>
     * <i><b>Note:</b> This will search for all punishments from the
     * cache first, and if none are found, then the database will
     * be searched. If you want to search just the database, then
     * use the {@link #forceGetPunishmentsFromReference( int )} method</i>
     * @param referenceId The reference ID to search for
     * @return A {@link List<ANetunoPunishment>} of all punishments for the reference ID.
     */
    public List<ANetunoPunishment> getPunishmentsFromReference( int referenceId ) {
        List<ANetunoPunishment> toReturn = cache.searchForMany( pun -> pun.getReferencePunId() == referenceId );
        if ( toReturn.size() == 0 ) { toReturn = forceGetPunishmentsFromReference( referenceId ); }
        return toReturn;
    }

    /**
     * Searches for all punishments in the database, not in the
     * cache, that have the given reference ID as the reference ID. <br>
     * <i>If you want to search the cache first then the database,
     * use the {@link #getPunishmentsFromReference( int )} method</i>
     * @param referenceId The reference ID to search for
     * @return A {@link List<ANetunoPunishment>} of all punishments for the reference ID.
     */
    public List<ANetunoPunishment> forceGetPunishmentsFromReference( int referenceId ) {
        List<ANetunoPunishment> toReturn = new ArrayList<>();
        cache.removeAllWhere( pun -> pun.getReferencePunId() == referenceId );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "SELECT * FROM " + TABLE_NAME + " WHERE reference = ?;" );
            ps.setInt( 1, referenceId );

            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                byte bytes[] = ( byte[] ) rs.getObject( "data" );
                ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
                ObjectInputStream ois = new ObjectInputStream( bais );
                ANetunoPunishmentData data = ( ANetunoPunishmentData ) ois.readObject();
                data.setId( rs.getInt( "id" ) );
                cache.add( ( ANetunoPunishment ) data );
                toReturn.add( ( ANetunoPunishment ) data );
            }

            ps.close();
            rs.close();
        } catch ( SQLException | IOException | ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }

        return toReturn;
    }

    /**
     * Updates the given punishment in the database and cache
     * @param newData The updated punishment data
     */
    public void updatePunishment( ANetunoPunishment newData ) {
        checkPunishment( newData, true );
        cache.removeAllWhere( p -> p.getId() == newData.getId() );
        cache.add( newData );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "UPDATE " + TABLE_NAME +
                    " SET player = ?, data = ?, guipun = ?, reference = ? WHERE id = ?" );
            ps.setString( 1, newData.getPlayerUuid() );
            ps.setObject( 2, ( ANetunoPunishmentData ) newData );
            ps.setString( 3, newData.isGuiPun() + "" );
            ps.setInt( 4, newData.getReferencePunId() );
            ps.setInt( 5, newData.getId() );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Deletes the punishment with the given ID from
     * the database and cache
     * @param punId The ID of the punishment to delete
     */
    public void removePunishment( int punId ) {
        cache.removeAllWhere( p -> p.getId() == punId );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "DELETE FROM " + TABLE_NAME + " WHERE id = ?" );
            ps.setInt( 1, punId );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Deletes all punishments for the given player
     * from the database and cache
     * @param player The player to delete punishments for
     */
    public void removePunishments( OfflinePlayer player ) {
        removePunishments( player.getUniqueId().toString() );
    }

    /**
     * Deletes all punishments for the given player UUID
     * from the database and cache
     * @param playerUuid The player UUID to delete punishments for
     */
    public void removePunishments( String playerUuid ) {
        cache.removeAllWhere( p -> p.getPlayerUuid().equals( playerUuid ) );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "DELETE FROM " + TABLE_NAME + " WHERE player = ?" );
            ps.setString( 1, playerUuid );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Deletes all punishments that have the given reference ID
     * @param referenceId The reference ID to delete punishments for
     */
    public void removePunishments( int referenceId ) {
        cache.removeAllWhere( p -> p.getReferencePunId() == referenceId );

        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "DELETE FROM " + TABLE_NAME + " WHERE reference = ?" );
            ps.setInt( 1, referenceId );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * @return The id of the most recently added punishment
     */
    private int getRecentlyInsertedId() {
        int toReturn = -1;
        try {
            Statement stmt = ApiConnection.getConn().createStatement();

            if ( ApiConnection.isSql() ) { stmt.execute( "SELECT ID AS lastId FROM " + TABLE_NAME + " WHERE ID = @@Identity;" ); }
            else { stmt.execute( "SELECT last_insert_rowid() AS lastId FROM " + TABLE_NAME + ";" ); }

            ResultSet rs = stmt.getResultSet();
            rs.next();
            if ( rs.isAfterLast() == false ) { toReturn = rs.getInt( "lastId" ); }
            stmt.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }

        return toReturn;
    }

    /**
     * Checks if a punishment is completely filled with the correct information
     * @param pun The punishment to check
     * @param shouldBeValidId If the punishment id should be above 0 (true) or less than or equal to 0 (false)
     * @throws ClassIncompleteException If the punishment is incomplete
     */
    public void checkPunishment( ANetunoPunishment pun, boolean shouldBeValidId ) {
        if ( shouldBeValidId && pun.getId() <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment ID must be greater than zero" ); }
        if ( shouldBeValidId == false && pun.getId() > 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment ID must be less than or equal to zero" ); }
        if ( pun.getPunishmentType() == null ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment type cannot be null" ); }
        if ( pun.getPlayerUuid() == null ) { throw new ClassIncompleteException( "Punishment incomplete: Player UUID cannot be null" ); }
        if ( pun.getStaffUuid() == null ) { throw new ClassIncompleteException( "Punishment incomplete: Staff UUID cannot be null" ); }
        if ( pun.getLength() <= 0 && pun.getPunishmentType().hasNoLength() == false ) { throw new ClassIncompleteException( "Punishment incomplete: Length must be greater than zero seconds" ); }
        if ( pun.getTimestamp() <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Timestamp must be greater than zero" ); }
        if ( pun.getReason() == null ) { throw new ClassIncompleteException( "Punishment incomplete: Reason cannot be null" ); }
        if ( pun.getPunishmentType().isIpPunishment() && pun.getReferencePunId() <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Reference Punishment ID must be greater than zero for IP punishments" ); }
    }
}