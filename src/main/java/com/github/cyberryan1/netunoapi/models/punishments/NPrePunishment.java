package com.github.cyberryan1.netunoapi.models.punishments;

public class NPrePunishment extends NPunishment {

    public NPrePunishment( int id, PunishmentType punishmentType, String playerUuid, String staffUuid, long length, long timestamp, String reason, boolean guiPun, int referencePunId, boolean needsNotifSent ) {
        super( id, punishmentType, playerUuid, staffUuid, length, timestamp, reason, guiPun, referencePunId, needsNotifSent );
    }

    public NPrePunishment() {}

    public void execute() {
        super.ensureValid( false );

        final boolean isConsole = staffUuid.equalsIgnoreCase( "console" );
        final boolean isSilent = ( super.getPunishmentType().hasNoReason() ) ? ( false ) : ( reason.contains( "-s" ) );
        final boolean isIpPun = super.getPunishmentType().isIpPunishment();


    }

    private void doBroadcast() {
        final boolean isConsole = staffUuid.equalsIgnoreCase( "console" );
        final boolean isSilent = ( super.getPunishmentType().hasNoReason() ) ? ( false ) : ( reason.contains( "-s" ) );


    }
}