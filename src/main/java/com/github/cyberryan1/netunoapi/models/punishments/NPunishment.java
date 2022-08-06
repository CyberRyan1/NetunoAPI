package com.github.cyberryan1.netunoapi.models.punishments;

import com.github.cyberryan1.netunoapi.exceptions.ClassIncompleteException;
import com.github.cyberryan1.netunoapi.models.time.NDuration;
import com.github.cyberryan1.netunoapi.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class NPunishment extends NPunishmentData {

    public NPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid,
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

    public NPunishment() {}

    /**
     * @return The amount of seconds remaining in the punishment. Returns
     * 0 if the punishment has no length or if the punishment is not active.
     * Returns -1 if the punishment length is permanent.
     */
    public long getSecondsRemaining() {
        if ( super.punishmentType.hasNoLength() || dataIsActive() == false ) { return 0; }
        if ( super.length == -1 ) { return -1; }
        long remain = super.length - ( TimeUtils.getCurrentTimestamp() - super.timestamp );
        if ( remain < 0 ) { return 0; }
        return remain;
    }

    /**
     * @return False if the punishment has been designated as inactive in
     * the database, if the punishment has no length, or if the punishment
     * has expired. True otherwise.
     */
    public boolean isActive() {
        long secondsRemain = getSecondsRemaining();
        return secondsRemain != 0;
    }

    /**
     * @return The amount of seconds remaining in the punishment as a
     * {@link NDuration} object.
     */
    public NDuration getLengthRemaining() {
        long remain = getSecondsRemaining();
        if ( remain == -1 ) { return new NDuration( true ); }
        return new NDuration( remain );
    }

    /**
     * @return The length of the punishment as a {@link NDuration} object.
     */
    public NDuration getTimeLength() {
        if ( super.length == -1 ) { return new NDuration( true ); }
        return new NDuration( super.length );
    }

    /**
     * @return The player as an {@link OfflinePlayer}
     */
    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer( UUID.fromString( super.playerUuid ) );
    }

    /**
     * @return The staff as an {@link OfflinePlayer}
     */
    public OfflinePlayer getStaff() {
        return Bukkit.getOfflinePlayer( UUID.fromString( super.staffUuid ) );
    }

    /**
     * Checks if the punishment is completely filled with the correct information
     * @param requireValidId If the punishment id should be above 0 (true) or less than or equal to 0 (false)
     * @throws ClassIncompleteException If the punishment is incomplete
     */
    public void ensureValid( boolean requireValidId ) {
        if ( requireValidId && this.id <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment ID must be greater than zero" ); }
        if ( requireValidId == false && this.id > 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment ID must be less than or equal to zero" ); }
        if ( this.punishmentType == null ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment type cannot be null" ); }
        if ( this.playerUuid == null ) { throw new ClassIncompleteException( "Punishment incomplete: Player UUID cannot be null" ); }
        if ( this.staffUuid == null ) { throw new ClassIncompleteException( "Punishment incomplete: Staff UUID cannot be null" ); }
        if ( this.length <= 0 && this.punishmentType.hasNoLength() == false && this.length != -1 ) { throw new ClassIncompleteException( "Punishment incomplete: Length must be greater than zero seconds" ); }
        if ( this.timestamp <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Timestamp must be greater than zero" ); }
        if ( this.reason == null ) { throw new ClassIncompleteException( "Punishment incomplete: Reason cannot be null" ); }
        if ( this.punishmentType.isIpPunishment() && this.referencePunId < -1 ) { throw new ClassIncompleteException( "Punishment incomplete: Reference Punishment ID must be greater than zero for IP punishments" ); }
    }

    /**
     * @return A copy of this punishment
     */
    public NPunishment copy() {
        NPunishment clone = new NPunishment();
        clone.setId( this.id );
        clone.setPunishmentType( this.punishmentType );
        clone.setPlayerUuid( this.playerUuid );
        clone.setStaffUuid( this.staffUuid );
        clone.setLength( this.length );
        clone.setTimestamp( this.timestamp );
        clone.setReason( this.reason );
        clone.setReferencePunId( this.referencePunId );
        clone.setGuiPun( this.guiPun );
        clone.setActive( this.active );
        clone.setNeedsNotifSent( this.needsNotifSent );
        return clone;
    }
}