package com.github.cyberryan1.netunoapi.helpers;

public enum PunishmentType {
    WARN ( true ),
    KICK ( true ),
    MUTE ( false ),
    UNMUTE ( true ),
    BAN ( false ),
    UNBAN ( true ),
    IPMUTE ( false ),
    UNIPMUTE ( true ),
    IPBAN ( false ),
    UNIPBAN ( true );

    private boolean hasNoLength;
    PunishmentType( boolean hasNoLength ) {
        this.hasNoLength = hasNoLength;
    }

    public boolean hasNoLength() {
        return hasNoLength;
    }
}