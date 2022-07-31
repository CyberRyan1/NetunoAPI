package com.github.cyberryan1.netunoapi.punishments;

import com.github.cyberryan1.netunoapi.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class ANetunoPunishment extends ANetunoPunishmentData {

    public ANetunoPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, ArrayList<String> altsPunished ) {
        super( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason, altsPunished );
    }

    public ANetunoPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason ) {
        super( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason );
    }

    public ANetunoPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long timestamp, String reason ) {
        super( id, punishmentType, playerUuid, staffUuid, timestamp, reason );
    }

    public ANetunoPunishment( int id, PunishmentType punishmentType, OfflinePlayer player, OfflinePlayer staff, long length, long timestamp, String reason, ArrayList<String> altsPunished ) {
        super( id, punishmentType, player, staff, length, timestamp, reason, altsPunished );
    }

    public ANetunoPunishment( int id, PunishmentType punishmentType, OfflinePlayer player, OfflinePlayer staff, long length, long timestamp, String reason ) {
        super( id, punishmentType, player, staff, length, timestamp, reason );
    }
    public ANetunoPunishment( int id, PunishmentType punishmentType, OfflinePlayer player, OfflinePlayer staff, long timestamp, String reason ) {
        super( id, punishmentType, player, staff, timestamp, reason );
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