package com.github.cyberryan1.netunoapi.punishments;

import org.bukkit.OfflinePlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class ANetunoPunishmentData implements Serializable {

    protected int id;
    protected PunishmentType punishmentType;
    protected String playerUuid;
    protected String staffUuid;
    protected long length;
    protected long timestamp;
    protected String reason;
    protected ArrayList<String> altsPunished;

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, ArrayList<String> altsPunished ) {
        this.id = id;
        this.punishmentType = punishmentType;
        this.playerUuid = playerUuid;
        this.staffUuid = staffUuid;
        this.length = length;
        this.timestamp = timestamp;
        this.reason = reason;
        this.altsPunished = altsPunished;
    }

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason ) {
        this( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason, null );
    }

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long timestamp, String reason ) {
        this( id, punishmentType, playerUuid, staffUuid, -1, timestamp, reason, null );
    }

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, OfflinePlayer player, OfflinePlayer staff, long length, long timestamp, String reason, ArrayList<String> altsPunished ) {
        this( id, punishmentType, player.getUniqueId().toString(), staff.getUniqueId().toString(), length, timestamp, reason, altsPunished );
    }

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, OfflinePlayer player, OfflinePlayer staff, long length, long timestamp, String reason ) {
        this( id, punishmentType, player.getUniqueId().toString(), staff.getUniqueId().toString(), length, timestamp, reason, null );
    }

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, OfflinePlayer player, OfflinePlayer staff, long timestamp, String reason ) {
        this( id, punishmentType, player.getUniqueId().toString(), staff.getUniqueId().toString(), -1, timestamp, reason, null );
    }

    public ANetunoPunishmentData() {}

    public int getId() {
        return id;
    }

    public PunishmentType getPunishmentType() {
        return punishmentType;
    }

    public String getPlayerUuid() {
        return playerUuid;
    }

    public String getStaffUuid() {
        return staffUuid;
    }

    public long getLength() {
        return length;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getReason() {
        return reason;
    }

    public ArrayList<String> getAltsPunished() {
        return altsPunished;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setPunishmentType( PunishmentType punishmentType ) {
        this.punishmentType = punishmentType;
    }

    public void setPlayerUuid( String playerUuid ) {
        this.playerUuid = playerUuid;
    }

    public void setPlayer( OfflinePlayer player ) {
        this.playerUuid = player.getUniqueId().toString();
    }

    public void setStaffUuid( String staffUuid ) {
        this.staffUuid = staffUuid;
    }

    public void setStaff( OfflinePlayer staff ) {
        this.staffUuid = staff.getUniqueId().toString();
    }

    public void setLength( long length ) {
        this.length = length;
    }

    public void setTimestamp( long timestamp ) {
        this.timestamp = timestamp;
    }

    public void setReason( String reason ) {
        this.reason = reason;
    }

    public void setAltsPunished( ArrayList<String> altsPunished ) {
        this.altsPunished = altsPunished;
    }

    @Override
    public String toString() {
        return "NetunoPunishmentData{" + "id=" + id +
                ", punishmentType=" + punishmentType +
                ", playerUuid=" + playerUuid +
                ", staffUuid=" + staffUuid +
                ", length=" + length +
                ", timestamp=" + timestamp +
                ", reason=" + reason +
                ", altsString=" + altsPunished +
                '}';
    }
}