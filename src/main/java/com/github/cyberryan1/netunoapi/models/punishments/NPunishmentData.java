package com.github.cyberryan1.netunoapi.models.punishments;

import org.bukkit.OfflinePlayer;

import java.io.Serializable;

public class NPunishmentData implements Serializable {

    protected int id = -1;
    protected PunishmentType punishmentType = null;
    protected String playerUuid = null;
    protected String staffUuid = null;
    protected long length = 0;
    protected long timestamp = -1;
    protected String reason = null;
    protected boolean active = false;
    protected boolean guiPun = false;
    protected int referencePunId = -1;
    protected boolean needsNotifSent = false;

    public NPunishmentData( int id, PunishmentType punishmentType, String playerUuid, String staffUuid,
                            long length, long timestamp, String reason, boolean active,
                            boolean guiPun, int referencePunId, boolean needsNotifSent ) {
        this.id = id;
        this.punishmentType = punishmentType;
        this.playerUuid = playerUuid;
        this.staffUuid = staffUuid;
        this.length = length;
        this.timestamp = timestamp;
        this.reason = reason;
        this.active = active;
        this.guiPun = guiPun;
        this.referencePunId = referencePunId;
        this.needsNotifSent = needsNotifSent;
    }

    public NPunishmentData() {}

    /**
     * @return The ID of the punishment.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The type of this punishment.
     */
    public PunishmentType getPunishmentType() {
        return punishmentType;
    }

    /**
     * @return The UUID of the player who was punished.
     */
    public String getPlayerUuid() {
        return playerUuid;
    }

    /**
     * @return The UUID of the staff member who punished the player.
     * May be "CONSOLE" to represent console punishments.
     */
    public String getStaffUuid() {
        return staffUuid;
    }

    /**
     * @return The length of the punishment in seconds. May be -1 to
     * represent permanent punishments or punishments with no length,
     * such as warns, kicks, unmutes, etc.
     */
    public long getLength() {
        return length;
    }

    /**
     * @return The timestamp of the punishment.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return The reason for the punishment.
     */
    public String getReason() {
        return reason;
    }

    /**
     * @return Whether the punishment is active (true) or not (false)
     */
    public boolean dataIsActive() {
        return active;
    }

    /**
     * @return Whether the punishment was executed via the punishment
     * GUI (true) or not (false)
     */
    public boolean isGuiPun() {
        return guiPun;
    }

    /**
     * @return The ID of the punishment that this punishment is a
     * reference to. Should only be set if the punishment is an
     * IP punishment, like an IP ban, IP mute, etc. Otherwise, it
     * should be -1.
     */
    public int getReferencePunId() {
        return referencePunId;
    }

    /**
     * @return Whether the notification has been sent for this
     * punishment (true) or not (false). Should only be set to true
     * if the punishment type is a warning.
     */
    public boolean needsNotifSent() {
        return needsNotifSent;
    }

    /**
     * @param id The ID of the punishment. Must be greater than 0.
     */
    public void setId( int id ) {
        this.id = id;
    }

    /**
     * @param punishmentType The type of punishment.
     */
    public void setPunishmentType( PunishmentType punishmentType ) {
        this.punishmentType = punishmentType;
    }

    /**
     * @param playerUuid The UUID of the player who was punished.
     */
    public void setPlayerUuid( String playerUuid ) {
        this.playerUuid = playerUuid;
    }

    /**
     * @param player The player who was punished.
     */
    public void setPlayer( OfflinePlayer player ) {
        this.playerUuid = player.getUniqueId().toString();
    }

    /**
     * @param staffUuid The UUID of the staff member who punished
     *                  the player. May be "CONSOLE" to represent
     *                  console punishments.
     */
    public void setStaffUuid( String staffUuid ) {
        this.staffUuid = staffUuid;
    }

    /**
     * @param staff The staff member who punished the player.
     */
    public void setStaff( OfflinePlayer staff ) {
        this.staffUuid = staff.getUniqueId().toString();
    }

    /**
     * @param length The length of the punishment in seconds.
     *               May be -1 to represent permanent punishments
     *               or punishments with no length, such as warns,
     *               kicks, unmutes, etc.
     */
    public void setLength( long length ) {
        this.length = length;
    }

    /**
     * @param timestamp The timestamp of the punishment.
     */
    public void setTimestamp( long timestamp ) {
        this.timestamp = timestamp;
    }

    /**
     * @param reason The reason for the punishment.
     */
    public void setReason( String reason ) {
        this.reason = reason;
    }

    /**
     * @param active Whether the punishment is active (true) or
     *               not (false).
     */
    public void setActive( boolean active ) {
        this.active = active;
    }

    /**
     * @param guiPun Whether the punishment was executed via the
     *              punishment GUI (true) or not (false)
     */
    public void setGuiPun( boolean guiPun ) {
        this.guiPun = guiPun;
    }

    /**
     * @param referencePunId The ID of the punishment that this
     *                        punishment is a reference to. Should
     *                        only be set if the punishment is an
     *                        IP punishment, like an IP ban, IP
     *                        mute, etc. Otherwise, it should be -1.
     */
    public void setReferencePunId( int referencePunId ) {
        this.referencePunId = referencePunId;
    }

    /**
     * @param needsNotifSent Whether the notification has been sent
     *                       for this punishment (true) or not
     *                       (false). Should only be set to true if
     *                       the punishment type is a warning.
     */
    public void setNeedsNotifSent( boolean needsNotifSent ) {
        this.needsNotifSent = needsNotifSent;
    }

    /**
     * I hope this is obvious.
     */
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
                ", needsNotifSent=" + needsNotifSent +
                '}';
    }
}