package com.github.cyberryan1.netunoapi.punishments;

import com.github.cyberryan1.netunoapi.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ANetunoPunishment extends ANetunoPunishmentData {

    public ANetunoPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, boolean guiPun, int referencePunId ) {
        super( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason, guiPun, referencePunId );
    }

    public ANetunoPunishment() {}

    public boolean isActive() {
        if ( super.punishmentType.hasNoLength() ) { return false; }
        return ( TimeUtils.getCurrentTimestamp() - super.timestamp ) < super.length;
    }

    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer( UUID.fromString( super.playerUuid ) );
    }

    public OfflinePlayer getStaff() {
        return Bukkit.getOfflinePlayer( UUID.fromString( super.staffUuid ) );
    }
}