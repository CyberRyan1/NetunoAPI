package com.github.cyberryan1.netunoapi.punishments;

import org.bukkit.OfflinePlayer;

import java.io.Serializable;

public class ANetunoPunishmentData implements Serializable {

    protected int id = -1;
    protected PunishmentType punishmentType = null;
    protected String playerUuid = null;
    protected String staffUuid = null;
    protected long length = -1;
    protected long timestamp = -1;
    protected String reason = null;
    protected boolean guiPun = false;
    protected int referencePunId = -1;

    public ANetunoPunishmentData( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, boolean guiPun, int referencePunId ) {
        this.id = id;
        this.punishmentType = punishmentType;
        this.playerUuid = playerUuid;
        this.staffUuid = staffUuid;
        this.length = length;
        this.timestamp = timestamp;
        this.reason = reason;
        this.guiPun = guiPun;
        this.referencePunId = referencePunId;
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

    public boolean isGuiPun() {
        return guiPun;
    }

    public int getReferencePunId() {
        return referencePunId;
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

    public void setGuiPun( boolean guiPun ) {
        this.guiPun = guiPun;
    }

    public void setReferencePunId( int referencePunId ) {
        this.referencePunId = referencePunId;
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
                ", guiPun=" + guiPun +
                ", referencePunId=" + referencePunId +
                '}';
    }
}