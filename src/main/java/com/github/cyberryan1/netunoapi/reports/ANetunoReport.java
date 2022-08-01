package com.github.cyberryan1.netunoapi.reports;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ANetunoReport extends ANetunoReportData {

    public ANetunoReport( int id, String playerUuid, String reporterUuid, long timestamp, String reason ) {
        super( id, playerUuid, reporterUuid, timestamp, reason );
    }

    public ANetunoReport() {}

    public void setPlayer( OfflinePlayer player ) {
        setPlayerUuid( player.getUniqueId().toString() );
    }

    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer( UUID.fromString( getPlayerUuid() ) );
    }

    public void setReporter( OfflinePlayer reporter ) {
        setReporterUuid( reporter.getUniqueId().toString() );
    }

    public OfflinePlayer getReporter() {
        return Bukkit.getOfflinePlayer( UUID.fromString( getReporterUuid() ) );
    }

}