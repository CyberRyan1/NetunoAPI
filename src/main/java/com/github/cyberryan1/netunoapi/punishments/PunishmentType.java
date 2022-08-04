package com.github.cyberryan1.netunoapi.punishments;

public enum PunishmentType {
    WARN ( true, false,false ),
    KICK ( true, false, false ),
    MUTE ( false, false, false ),
    UNMUTE ( true, true, false ),
    BAN ( false, false, false ),
    UNBAN ( true, true, false ),
    IPMUTE ( false, false, true ),
    UNIPMUTE ( true, true, true ),
    IPBAN ( false, false, true ),
    UNIPBAN ( true, true, true );

    private boolean hasNoLength;
    private boolean hasNoReason;
    private boolean ipPunishment;
    PunishmentType( boolean hasNoLength, boolean hasNoReason, boolean ipPunishment ) {
        this.hasNoLength = hasNoLength;
        this.hasNoReason = hasNoReason;
        this.ipPunishment = ipPunishment;
    }

    public boolean hasNoLength() {
        return hasNoLength;
    }

    public boolean hasNoReason() {
        return hasNoReason;
    }

    public boolean isIpPunishment() {
        return ipPunishment;
    }
}