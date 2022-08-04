package com.github.cyberryan1.netunoapi.punishments;

import com.github.cyberryan1.netunoapi.utils.TimeUtils;
import com.github.cyberryan1.netunoapi.utils.helpers.ClassIncompleteException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ANetunoPunishment extends ANetunoPunishmentData {

    public ANetunoPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, boolean guiPun, int referencePunId, boolean needsNotifSent ) {
        super( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason, guiPun, referencePunId, needsNotifSent );
    }

    public ANetunoPunishment() {}

    /**
     * @return true if the punishment is still active, false if
     * it is not active or if the punishment has no length by default
     */
    public boolean isActive() {
        if ( super.punishmentType.hasNoLength() ) { return false; }
        return ( TimeUtils.getCurrentTimestamp() - super.timestamp ) < super.length;
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

    public void ensureValid( boolean requireValidId ) {
        if ( requireValidId && this.id <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment ID must be greater than zero" ); }
        if ( requireValidId == false && this.id > 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment ID must be less than or equal to zero" ); }
        if ( this.punishmentType == null ) { throw new ClassIncompleteException( "Punishment incomplete: Punishment type cannot be null" ); }
        if ( this.playerUuid == null ) { throw new ClassIncompleteException( "Punishment incomplete: Player UUID cannot be null" ); }
        if ( this.staffUuid == null ) { throw new ClassIncompleteException( "Punishment incomplete: Staff UUID cannot be null" ); }
        if ( this.length <= 0 && this.punishmentType.hasNoLength() == false ) { throw new ClassIncompleteException( "Punishment incomplete: Length must be greater than zero seconds" ); }
        if ( this.timestamp <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Timestamp must be greater than zero" ); }
        if ( this.reason == null ) { throw new ClassIncompleteException( "Punishment incomplete: Reason cannot be null" ); }
        if ( this.punishmentType.isIpPunishment() && this.referencePunId <= 0 ) { throw new ClassIncompleteException( "Punishment incomplete: Reference Punishment ID must be greater than zero for IP punishments" ); }
    }
}