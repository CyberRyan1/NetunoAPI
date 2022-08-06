package com.github.cyberryan1.netunoapi.models.punishments;

public enum PunishmentType {
    WARN ( 0, true, false,false ),
    KICK ( 1, true, false, false ),
    MUTE ( 2, false, false, false ),
    UNMUTE ( 3, true, true, false ),
    BAN ( 4, false, false, false ),
    UNBAN ( 5, true, true, false ),
    IPMUTE ( 6, false, false, true ),
    UNIPMUTE ( 7, true, true, true ),
    IPBAN ( 8, false, false, true ),
    UNIPBAN ( 9, true, true, true );

    private final int index;
    private final boolean hasNoLength;
    private final boolean hasNoReason;
    private final boolean ipPunishment;
    PunishmentType( int index, boolean hasNoLength, boolean hasNoReason, boolean ipPunishment ) {
        this.index = index;
        this.hasNoLength = hasNoLength;
        this.hasNoReason = hasNoReason;
        this.ipPunishment = ipPunishment;
    }

    public int getIndex() { return index; }

    public boolean hasNoLength() {
        return hasNoLength;
    }

    public boolean hasNoReason() {
        return hasNoReason;
    }

    public boolean isIpPunishment() {
        return ipPunishment;
    }

    //
    // Static Methods
    //

    public static PunishmentType fromString( String str ) {
        for ( PunishmentType type : values() ) {
            if ( type.name().equalsIgnoreCase( str ) ) {
                return type;
            }
        }
        return null;
    }

    public static PunishmentType fromIndex( int index ) {
        for ( PunishmentType type : values() ) {
            if ( type.getIndex() == index ) {
                return type;
            }
        }
        return null;
    }
}