package com.github.cyberryan1.netunoapi.helpers;

import com.github.cyberryan1.netunoapi.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ANetunoPunishment extends ANetunoPunishmentData {

    public static final List<PunishmentType> PUNS_NO_LENGTH = List.of( PunishmentType.WARN, PunishmentType.KICK,
            PunishmentType.UNMUTE, PunishmentType.UNBAN, PunishmentType.UNIPMUTE, PunishmentType.UNIPBAN );

    public ANetunoPunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, ArrayList<String> altsPunished ) {
        super( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason, altsPunished );
    }

    public ANetunoPunishment() {}

    public boolean isActive() {
        if ( PUNS_NO_LENGTH.contains( super.punishmentType ) ) { return false; }
        return ( TimeUtils.getCurrentTimestamp() - super.timestamp ) < super.length;
    }

    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer( UUID.fromString( super.playerUuid ) );
    }

    public OfflinePlayer getStaff() {
        return Bukkit.getOfflinePlayer( UUID.fromString( super.staffUuid ) );
    }
}