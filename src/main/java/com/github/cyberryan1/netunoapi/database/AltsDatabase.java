package com.github.cyberryan1.netunoapi.database;

import com.github.cyberryan1.netunoapi.database.helpers.AltGroup;
import com.github.cyberryan1.netunoapi.database.helpers.AltSecurityLevel;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class AltsDatabase {

    private final String TABLE_NAME = "alts";
    private final String TYPE_LIST = "(group, item, type)";
    private final String UNKNOWN_LIST = "(?, ?, ?)";

    private final List<AltGroup> cache = new ArrayList<>();
    private AltSecurityLevel securityLevel = AltSecurityLevel.HIGH;

    /**
     * @return The cache of punishments
     */
    public List<AltGroup> getCache() {
        return cache;
    }

    /**
     * @param level The alt security level to set to
     */
    public void setSecurityLevel( AltSecurityLevel level ) {
        this.securityLevel = level;
    }

    /**
     * @return The alt security level
     */
    public AltSecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Initializes the cache
     */
    public void initializeCache() {
        ApiConnection.getPlugin().getLogger().log( Level.INFO, "Initializing the alts cache..." );

        ApiConnection.getPlugin().getLogger().log( Level.INFO, "Getting all IPs and players from the database..." );
        try {
            Statement stmt = ApiConnection.getConn().createStatement();
            stmt.execute( "SELECT * FROM " + TABLE_NAME );

            ResultSet rs = stmt.getResultSet();
            while ( rs.next() ) {
                final int groupId = rs.getInt( "group" );
                final String item = rs.getString( "item" );
                final String type = rs.getString( "type" );

                AltGroup group = cache.stream()
                        .filter( g -> g.getGroupId() == groupId )
                        .findFirst()
                        .orElse( null );
                if ( group == null ) {
                    group = new AltGroup();
                    group.setGroupId( groupId );
                    cache.add( group );
                }

                if ( type.equalsIgnoreCase( "uuid" ) ) { group.addAlt( item ); }
                else if ( type.equalsIgnoreCase( "ip" ) ) { group.addIp( item ); }
            }

            stmt.close();
            rs.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
        ApiConnection.getPlugin().getLogger().log( Level.INFO, "Successfully retrieved all IPs and players from the database" );

        ApiConnection.getPlugin().getLogger().log( Level.INFO, "Alt cache successfully initialized with a current size of " + cache.size() );
    }

    /**
     * Loads a player's uuid and their current IP into the cache
     * @param player The player to load
     */
    public void loadPlayer( Player player ) {
        loadPlayer( player.getUniqueId().toString(), player.getAddress().getAddress().getHostAddress() );
    }

    /**
     * Loads a player uuid and their current IP into the cache
     * @param playerUuid The player uuid
     * @param playerIp The player ip
     */
    public void loadPlayer( String playerUuid, String playerIp ) {
        if ( securityLevel == AltSecurityLevel.LOW ) {
            List<AltGroup> groupList = cache.stream()
                    .filter( g -> g.getIpList().contains( playerIp ) )
                    .collect( Collectors.toList() );

            if ( groupList.isEmpty() == false ) {
                groupList.get( 0 ).addAlt( playerUuid );
            }

            else {
                AltGroup group = new AltGroup();
                group.setGroupId( getNextAvailableId() );
                group.addIp( playerIp );
                group.addAlt( playerUuid );
                cache.add( group );
            }
        }

        else if ( securityLevel == AltSecurityLevel.MEDIUM ) {
            List<AltGroup> playerSearch = cache.stream()
                    .filter( g -> g.getAltUuids().contains( playerUuid ) )
                    .collect( Collectors.toList() );

            AltGroup group;
            if ( playerSearch.isEmpty() == false ) {
                group = merge( playerSearch );
                if ( group.containsIp( playerIp ) == false ) { group.addIp( playerIp ); }

                cache.removeAll( playerSearch );
            }

            else {
                group = new AltGroup();
                group.setGroupId( getNextAvailableId() );
                group.addIp( playerIp );
                group.addAlt( playerUuid );
            }
            cache.add( group );
        }

        else if ( securityLevel == AltSecurityLevel.HIGH ) {
            List<AltGroup> groupsSearched = new ArrayList<>();
            List<String> altsSearched = new ArrayList<>();
            List<String> altsToSearch = new ArrayList<>();
            List<String> ipsSearched = new ArrayList<>();
            List<String> ipsToSearch = new ArrayList<>();

            altsToSearch.add( playerUuid );
            ipsToSearch.add( playerIp );

            while ( altsToSearch.size() > 0 || ipsToSearch.size() > 0 ) {
                String altUuid = altsToSearch.remove( 0 );
                String altIp = ipsToSearch.remove( 0 );
                altsSearched.add( altUuid );
                ipsSearched.add( altIp );

                cache.stream()
                        .filter( g -> g.getAltUuids().contains( altUuid ) || g.getIpList().contains( altIp ) )
                        .distinct()
                        .forEach( group -> {
                            if ( groupsSearched.contains( group ) == false ) { groupsSearched.add( group ); }

                            group.getAltUuids().stream()
                                    .filter( alt -> altsSearched.contains( alt ) == false )
                                    .forEach( altsToSearch::add );
                            group.getIpList().stream()
                                    .filter( ip -> ipsSearched.contains( ip ) == false )
                                    .forEach( ipsToSearch::add );
                        } );
            }

            AltGroup group = new AltGroup();
            if ( groupsSearched.isEmpty() == false ) {
                group.setAltUuids( altsSearched );
                group.setIpList( ipsSearched );
                cache.removeAll( groupsSearched );
            }

            else {
                group.setGroupId( getNextAvailableId() );
                group.addIp( playerIp );
                group.addAlt( playerUuid );
            }
            cache.add( group );
        }
    }

    /**
     * Saves all elements from the cache into the database. Note that
     * first this method deletes all entries from the database and then
     * it inserts all elements from the cache into the database.
     */
    public void saveAll() {
        ApiConnection.getPlugin().getLogger().log( Level.INFO, "Saving all elements in the alt cache to the database..." );
        deleteAll();

        int count = 0;
        for ( AltGroup group : cache ) {
            for ( String ip : group.getIpList() ) { saveIp( group.getGroupId(), ip ); count++; }
            for ( String uuid : group.getAltUuids() ) { saveUuid( group.getGroupId(), uuid ); count++; }
        }

        ApiConnection.getPlugin().getLogger().log( Level.INFO, "Successfully saved " + cache.size()
                + " different alt groups containing " + count + " IPs and UUIDs to the database" );
    }

    /**
     * Saves an ip into the database for a given group id
     * @param groupId The group id
     * @param ipAddress The ip address
     */
    public void saveIp( int groupId, String ipAddress ) {
        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "INSERT INTO " + TABLE_NAME + " (group, item, type) VALUES (?, ?, ?)" );
            ps.setInt( 1, groupId );
            ps.setString( 2, ipAddress );
            ps.setString( 3, "ip" );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Saves an uuid into the database for a given group id
     * @param groupId The group id
     * @param uuid The uuid
     */
    public void saveUuid( int groupId, String uuid ) {
        try {
            PreparedStatement ps = ApiConnection.getConn().prepareStatement( "INSERT INTO " + TABLE_NAME + " (group, item, type) VALUES (?, ?, ?)" );
            ps.setInt( 1, groupId );
            ps.setString( 2, uuid );
            ps.setString( 3, "uuid" );

            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Deletes all entries from the database
     */
    public void deleteAll() {
        try {
            Statement stmt = ApiConnection.getConn().createStatement();
            stmt.execute( "DELETE FROM " + TABLE_NAME + ";" );
            stmt.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Merges a list of {@link AltGroup} into one. The returned
     * alt group's group ID is the lowest group ID of the given
     * list.
     * @param groupList The list of alt groups
     * @return The merged alt group
     */
    private AltGroup merge( List<AltGroup> groupList ) {
        if ( groupList.size() == 1 ) { return groupList.get( 0 ); }
        AltGroup toReturn = new AltGroup();
        int lowestId = Integer.MAX_VALUE;

        for ( AltGroup group : groupList ) {
            toReturn.getAltUuids().addAll( group.getAltUuids() );
            toReturn.getIpList().addAll( group.getIpList() );

            if ( group.getGroupId() < lowestId ) { lowestId = group.getGroupId(); }
        }

        toReturn.setGroupId( lowestId );
        toReturn.setAltUuids( toReturn.getAltUuids().stream().distinct().collect( Collectors.toList() ) );
        toReturn.setIpList( toReturn.getIpList().stream().distinct().collect( Collectors.toList() ) );
        return toReturn;
    }

    /**
     * @return The next available group id
     */
    private int getNextAvailableId() {
        int toReturn = cache.size() + 1;
        boolean continueWhile = true;

        while ( continueWhile ) {
            final int x = toReturn;
            if ( cache.stream().anyMatch( g -> g.getGroupId() == x ) ) { toReturn++; }
            else { continueWhile = false; }
        }

        return toReturn;
    }
}