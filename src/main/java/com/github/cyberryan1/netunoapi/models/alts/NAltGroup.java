package com.github.cyberryan1.netunoapi.models.alts;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NAltGroup {

    private int groupId = -1;
    private List<String> altUuids = new ArrayList<>();
    private List<String> ipList = new ArrayList<>();
    private int ipPunishmentId = -1;

    public NAltGroup( int groupId, List<String> altUuids, List<String> ipList ) {
        this.groupId = groupId;
        this.altUuids = altUuids;
        this.ipList = ipList;
    }

    public NAltGroup() {}

    //
    // Main Methods
    //

    public void addAlt( OfflinePlayer player ) {
        altUuids.add( player.getUniqueId().toString() );
    }

    public void addAlt( String playerUuid ) {
        altUuids.add( playerUuid );
    }

    public boolean containsAlt( OfflinePlayer player ) {
        return altUuids.contains( player.getUniqueId().toString() );
    }

    public boolean containsAlt( String playerUuid ) {
        return altUuids.contains( playerUuid );
    }

    public void removeAlt( OfflinePlayer player ) {
        altUuids.remove( player.getUniqueId().toString() );
    }

    public void removeAlt( String playerUuid ) {
        altUuids.remove( playerUuid );
    }

    public List<OfflinePlayer> getAlts() {
        return altUuids.stream()
                .map( uuid -> Bukkit.getOfflinePlayer( UUID.fromString( uuid ) ) )
                .collect( ArrayList::new, ArrayList::add, ArrayList::addAll );
    }

    public void addIp( String ip ) {
        ipList.add( ip );
    }

    public boolean containsIp( String ip ) {
        return ipList.contains( ip );
    }

    public void removeIp( String ip ) {
        ipList.remove( ip );
    }

    //
    // Getters & Setters
    //

    public int getGroupId() {
        return groupId;
    }

    public List<String> getAltUuids() {
        return altUuids;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public int getIpPunishmentId() {
        return ipPunishmentId;
    }

    public void setGroupId( int groupId ) {
        this.groupId = groupId;
    }

    public void setAltUuids( List<String> altUuids ) {
        this.altUuids = altUuids;
    }

    public void setIpList( List<String> ipList ) {
        this.ipList = ipList;
    }

    public void setIpPunishmentId( int ipPunishmentId ) {
        this.ipPunishmentId = ipPunishmentId;
    }
}