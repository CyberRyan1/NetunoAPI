package com.github.cyberryan1.netunoapi.punishments;

public enum PunishmentType {
    WARN ( true, false ),
    KICK ( true, false ),
    MUTE ( false, false ),
    UNMUTE ( true, false ),
    BAN ( false, false ),
    UNBAN ( true, false ),
    IPMUTE ( false, true ),
    UNIPMUTE ( true, true ),
    IPBAN ( false, true ),
    UNIPBAN ( true, true );

    private boolean hasNoLength;
    private boolean ipPunishment;
    PunishmentType( boolean hasNoLength, boolean ipPunishment ) {
        this.hasNoLength = hasNoLength;
        this.ipPunishment = ipPunishment;
    }

    public boolean hasNoLength() {
        return hasNoLength;
    }

    public boolean isIpPunishment() {
        return ipPunishment;
    }
}